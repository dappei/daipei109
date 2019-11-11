package com.web.blog.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.web.blog.model.ArticleBean;
import com.web.blog.model.CompanyBean;
import com.web.blog.service.ProductService;
import com.web.blog.util.JSONFileUpload;

@Controller
public class ArticleController {
	ProductService service;
	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	@Autowired
	public void setService(ProductService service) {
		this.service = service;
	}

	// 顯示單一部落格文章內容
	@RequestMapping(value = "/getSingleBlog")
	public String blog(@RequestParam(value = "articleId") Integer articleId, Model model) {
		ArticleBean bb = service.getProductById(articleId);
		model.addAttribute("product", bb);
		return "store/blog1";
	}

	// 顯示新增文章頁面及分類
	@RequestMapping(value = "/products/add")
	public String getAddNewProductForm(Model model) {
		System.out.println("getAddNewProductForm");
		List<String> categoryList = service.getAllCategories();
		model.addAttribute("categoryList", categoryList);

		return "store/addProduct";
	}

	// 新增部落格文章
	@RequestMapping(value = "/products/addArticle", method = RequestMethod.POST)
	public String processAddNewProductForm(HttpServletRequest request,
									       @RequestParam String title,
									       @RequestParam Integer categoryId,
									       @RequestParam String author,
									       @RequestParam String articlecontent,
										   @RequestParam MultipartFile coverImage) {

		ArticleBean articleBean = new ArticleBean();
		String originalFilename = coverImage.getOriginalFilename();
		articleBean.setTitle(title);

		// 作弊自己 insert categoryId
		// articleBean.setCategoryId(categoryId);

		// Hibernate 實作一對多
		CompanyBean companyBean = service.findByCategoryId(categoryId);
		articleBean.setCompanyBean(companyBean);
		articleBean.setAuthor(author);
		articleBean.setArticlecontent(articlecontent);
		articleBean.setFileName(originalFilename);
		
		System.out.println("文章內容:" + articlecontent);
		System.out.println("articleBean 文章內容:" + articleBean.getArticlecontent());
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (coverImage != null && !coverImage.isEmpty()) {
			try {
				byte[] b = coverImage.getBytes();
				Blob blob = new SerialBlob(b);
				articleBean.setCoverImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}

		service.addProduct(articleBean);
		return "redirect:/store/blog";
	}

	@RequestMapping(value = "/getPicture/{articleId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer articleId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		ArticleBean bean = service.getProductById(articleId);
		if (bean != null) {
			Blob blob = bean.getCoverImage();
			filename = bean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("ArticleController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType =" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	private byte[] toByteArray(String filepath) {
		byte[] b = null;
		String realPath = context.getRealPath(filepath);
//		System.out.println("realPath: " + realPath);
//		System.out.println("filepath: " + filepath);
		try {
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int) size];
			System.out.println("b: " + b);
			System.out.println("size: " + size);
			InputStream fis = context.getResourceAsStream(filepath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	// 頁面產生分類可供 user 點選
	@RequestMapping(value = "/category" )
	public String getCategoryList(Model model) {
		List<String>  list = service.getAllCategories();
		System.out.println(list);
		model.addAttribute("categoryList", list);

		return "types/category";
	}
	
	// 限制文字內容字數
	@RequestMapping("/blog")
	public String list(Model model) {
		List<ArticleBean> list = service.getAllProducts();
		for (ArticleBean bb : list) {
			int contentLength = bb.getArticlecontent().length();
			String articleShortContent;
			if (contentLength < 30) {
				articleShortContent = bb.getArticlecontent();
			} else {
				articleShortContent = bb.getArticlecontent().substring(0, 30);
			}
			bb.setArticleShortContent(articleShortContent);
		}

		model.addAttribute("products", list);
		return "store/blog";
	}

	// CKEditor Insert BlogContent
	@RequestMapping("/blogInsert")
	 public void blogInsert(@RequestParam("upload") MultipartFile upload,
	        HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
	  String ext = context.getMimeType(upload.getOriginalFilename());
	  ext = ext.substring(6);
	  Date date = new Date();
	  String filename = String.valueOf(date.getTime() + "." + ext);
	        
	        InputStream in = upload.getInputStream();
	        String basePath = "C:\\Users\\User\\Desktop\\imagesCK\\";
	        System.out.println("basePath:" + basePath);
	        File outputFilePath = new File(basePath + filename);
	        OutputStream output = new FileOutputStream(outputFilePath);
	        byte[] buff = new byte[1024];
	        int length;
	        while ((length = in.read(buff)) != -1) {
	            output.write(buff, 0, length);
	        }
	        output.close();
	        in.close();
	        
	        Gson gson = new Gson();
	        PrintWriter out = response.getWriter();
	        out.print(gson.toJson(new JSONFileUpload(basePath + filename)));
	        out.flush();
	        out.close();
	 }

	// CKEditor Browse BlogPicture
	@RequestMapping("/blogBrowse")
	 public String blogBrowse(HttpServletRequest request, Model model) {
	  String basePath = "C:\\Users\\User\\Desktop\\imagesCK\\";
	        File folder = new File(basePath);
	        model.addAttribute("files", folder.listFiles());
	        model.addAttribute("CKEditorFuncNum", request.getParameter("CKEditorFuncNum"));

	  return "store/browsefile";
	 }

	
	
	
	
	
	
	
	//	@RequestMapping("/update/stock")
	//	public String updateStock(Model model) {
	//		service.updateAllStocks();
	//		return "redirect:/products";
	//	}
	
	//	@RequestMapping(value = "/searchcategory")
	//	public String name(@RequestParam(value = "FK_CompanyBean_Id") Integer FK_CompanyBean_Id, Model model) {
	//
	//		CompanyBean cb = service.getCompanyById(FK_CompanyBean_Id);
	//		model.addAttribute("getCompanyById", cb);
	//
	//		return "blog";
	//
	//	}
}
