package com.web.maintain.event.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.web.event.model.EventBean;
import com.web.event.service.EventService;

@Controller
public class EventMaintainController {
	EventService service;	
	@Autowired
	public void setService(EventService service) {
		this.service = service;
	}
	
	ServletContext context;
	@Autowired
	public void setContext(ServletContext context) {
		this.context = context;
	}
	//新增活動,送出空白表單給使用者輸入資料
	@RequestMapping(value = "/events/add", method = RequestMethod.GET)
	public String getAddNewEventForm(Model model) {
		EventBean eb = new EventBean();
		model.addAttribute("eventBean", eb);
		return "maintain/addEvent";
	}
	//使用者輸入完資料後，由此方法存進去
	@RequestMapping(value = "/events/add", method = RequestMethod.POST)
	public String processAddNewEventForm(@ModelAttribute("eventBean") EventBean eb) {
		MultipartFile eventImage =  eb.getEventImage();
		String originalFilename = eventImage.getOriginalFilename();
		eb.setFileName(originalFilename);
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (eventImage != null && !eventImage.isEmpty()) {
			try {
				byte[] b = eventImage.getBytes();
				Blob blob = new SerialBlob(b);
				eb.setEventPic(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		service.saveEvent(eb);
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = context.getRealPath("/");
		try {
			File imageFolder = new File(rootDirectory, "images");
			if (!imageFolder.exists())
				imageFolder.mkdirs();
			File file = new File(imageFolder, eb.getEventId() + ext);
			eventImage.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
		}
		return "redirect:/events";
	}
	//取出進行中活動進行維護
	@RequestMapping("/events/maintain")
	public String getMaintainEventlist(Model model,HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int pageNo = 1;
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "login/login";
		}
		
		String pageNoStr = request.getParameter("pageNo");
		// 如果讀不到，直接點選主功能表的『購物』就不會送 pageNo給後端伺服器
		if (pageNoStr == null) {  
			pageNo = 1;
			} else { try { pageNo = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) { pageNo = 1;
			}
		}
		request.setAttribute("baBean", service);
		service.setPageNo(pageNo);
		service.setRecordsPerPage(10);
		Collection<EventBean> coll=service.getPageEvents();
		session.setAttribute("pageNo", String.valueOf(pageNo));
		model.addAttribute("totalPages", service.getTotalPages());
		model.addAttribute("events", coll);

		return "maintain/eventsMaintainList";
	}
	//取出結束活動進行維護
	@RequestMapping("/events/pastevents")
	public String getMaintainpastEventlist(Model model,HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int pageNo = 1;
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "login/login";
		}
		
		String pageNoStr = request.getParameter("pageNo");
		// 如果讀不到，直接點選主功能表的『購物』就不會送 pageNo給後端伺服器
		if (pageNoStr == null) {  
			pageNo = 1;
			} else { try { pageNo = Integer.parseInt(pageNoStr.trim());
			} catch (NumberFormatException e) { pageNo = 1;
			}
		}
		request.setAttribute("baBean", service);
		service.setPageNo(pageNo);
		service.setRecordsPerPage(10);
		Collection<EventBean> coll=service.getCloseEvents();
		session.setAttribute("pageNo", String.valueOf(pageNo));
		model.addAttribute("totalPages", service.getTotalPages());
		model.addAttribute("events", coll);

		return "maintain/pastEventsMaintainList";
	}
	//修正活動,用此方法送回含有活動資料的表單，讓使用者進行修改
	@RequestMapping(value="/events/update/{id}", method=RequestMethod.GET)
	public String editEventForm(Model model, @PathVariable Integer id) {
		EventBean eb = service.getEventById(id);
		model.addAttribute("eventBean", eb);
		return "maintain/addEvent";
	}
	//接收修改過的活動資料寫入資料庫
	@RequestMapping(value="/events/update/{id}", method=RequestMethod.POST)
	public String edit(@ModelAttribute("eventBean") EventBean eb, @PathVariable Integer id, HttpServletRequest request) {		
		MultipartFile eventImage =  eb.getEventImage();
		
		if (eventImage.getSize() == 0) {
			// 表示使用者並未挑選圖片
			EventBean original = service.getEventById(id);
			eb.setEventPic(original.getEventPic());
		} else {
			String originalFilename = eventImage.getOriginalFilename();
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				eb.setFileName(originalFilename);
			}
		
			// 建立Blob物件，交由 Hibernate 寫入資料庫
			if (eventImage != null && !eventImage.isEmpty()) {
				try {
					byte[] b = eventImage.getBytes();
					Blob blob = new SerialBlob(b);
					eb.setEventPic(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
		}
		service.updateEvent(eb);
		return "redirect:/events/maintain";
	}
	//開啟活動
	@RequestMapping(value="/events/pastevents/open/{id}",method=RequestMethod.GET)
	public String getEventOpen(@PathVariable Integer id) {
		service.openEvent(id);
		return "redirect:/events/maintain";
	}
	//關閉活動
	@RequestMapping(value="/events/close/{id}",method=RequestMethod.GET)
	public String getEventClose(@PathVariable Integer id) {
		System.out.println(id);
		service.closeEvent(id);
		return "redirect:/events/maintain";
	}
	
}


