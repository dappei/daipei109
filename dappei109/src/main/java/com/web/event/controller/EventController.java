package com.web.event.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.event.model.EventBean;
import com.web.event.service.EventService;

@Controller
public class EventController {
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
	@RequestMapping("/")
	public String index1() {		
		return "index";
	}
	
	@RequestMapping("/index")
	public String index2() {		
		return "index";
	}
	
	//取出正在進行活動資料
	@RequestMapping("/events")
	public String getEventlist(Model model,HttpServletRequest request, HttpServletResponse response) 
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
		//request.setAttribute("baBean", service);
		service.setPageNo(pageNo);
		Collection<EventBean> coll=service.getPageEvents();
		session.setAttribute("pageNo", String.valueOf(pageNo));
		model.addAttribute("totalPages", service.getTotalPages());
		model.addAttribute("events", coll);

		return "event/events";
	}
	//取出單筆活動資料
	@RequestMapping("/event")
	public String getEventsById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("event",service.getEventById(id));
		return "event/event";
	}
	//取出資料庫Blob物件
	@RequestMapping(value="/getEventPicture/{eventId}",method=RequestMethod.GET)
	public ResponseEntity<byte[]> getEventPicture(HttpServletResponse resp,@PathVariable Integer eventId){
		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		EventBean bean = service.getEventById(eventId);
		if (bean != null) {
			Blob blob = bean.getEventPic();
			filename = bean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生SQLException:" + e.getMessage());
				}
			}
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType=" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
}


