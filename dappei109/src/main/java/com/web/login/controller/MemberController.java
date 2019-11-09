package com.web.login.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.web.login.model.MemberBean;
import com.web.login.service.MemberService;

@Controller
@SessionAttributes({ "currentUser", "account" })
public class MemberController {
	MemberService service;

	@Autowired
	public void setService(MemberService service) {
		this.service = service;
	}

	ServletContext context;

	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}

	// 會員登入
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin(Model model) {
		MemberBean mb = new MemberBean();
		model.addAttribute("memberBean2", mb);
		return "login/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @ModelAttribute("memberBean2") MemberBean mb) {
		// member會自己註入session中
		// 將account放入session作用域中，這樣轉發頁面也可以取到這個數據。
		MemberBean checkId = service.checkIDPassword(mb.getAccount(), mb.getPassword());
		if (checkId != null) {
			model.addAttribute("currentUser", checkId);
			return "redirect:/";
		} else {
			model.addAttribute("error", "登入失敗");
			return "login/login";
		}
	}

	// 取得會員大頭貼
	@RequestMapping(value = { "/getPicture/{memberId}", "/getPicture/" }, method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp,
			@PathVariable(name = "memberId", required = false) Integer memberId) {
		String filePath = "/resources/images/NoImage.jpg";
		byte[] media = null;
		String filename = "";
		HttpHeaders headers = new HttpHeaders();
		int len = 0;
		if (memberId == null) {
			media = toByteArray(filePath);
			filename = filePath;
		} else {
			MemberBean bean = service.getMemberById(memberId);
			if (bean != null) {
				Blob blob = bean.getFacepic();
				if (blob != null) {
					try {
						len = (int) blob.length();
						media = blob.getBytes(1, len);
					} catch (SQLException e) {
						throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
					}
				} else {
					media = toByteArray(filePath);
					filename = filePath;
				}
			} else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	private byte[] toByteArray(String filepath) {
		byte[] b = null;
		String realPath = context.getRealPath(filepath);
		try {
			File file = new File(realPath);
			long size = file.length();
			System.out.println(size);
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filepath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	// 會員註冊
	@RequestMapping(value = "/members/add", method = RequestMethod.GET)
	public String getAddNewMemberForm(Model model) {
		MemberBean mb = new MemberBean();
		model.addAttribute("memberBean", mb);
		return "login/addMember";
	}

	@RequestMapping(value = "/members/add", method = RequestMethod.POST)
	public String processAddNewMemberForm(@ModelAttribute("memberBean") MemberBean mb) {
		service.saveMember(mb);
		return "redirect:/";
	}

	@RequestMapping("/members")
	public String list(Model model) {
		List<MemberBean> list = service.getAllMembers();
		model.addAttribute("members", list);
		return "login/members";
	}
	@RequestMapping("personalPg")
	public String personalPg() {
		return "logion/personalPg";
	}
//	@RequestMapping("/")
//	public String index() {
//		return "index";
//	}

	@RequestMapping("logout")
	public String outLogin(HttpSession session, SessionStatus sessionStatus) {
		session.removeAttribute("currentUser");// 我這裡是先取出httpsession中的user屬性
		session.invalidate(); // 然後是讓httpsession失效
		sessionStatus.setComplete();// 最後是呼叫sessionStatus方法
		return "redirect:/";
	}
}
