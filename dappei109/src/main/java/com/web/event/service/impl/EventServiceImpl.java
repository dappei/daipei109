package com.web.event.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.event.dao.EventDao;
import com.web.event.model.EventBean;
import com.web.event.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	EventDao dao;
	@Autowired
	public void setDao(EventDao dao) {
		this.dao = dao;
	}
	@Transactional
	@Override
	public List<EventBean> getPageEvents() {
		List<EventBean> list=null;
		list=dao.getPageEvents();
		return list;
	}
	@Transactional
	@Override
	public List<EventBean> getCloseEvents() {
		List<EventBean> list=null;
		list=dao.getCloseEvents();
		return list;
	}
	@Transactional
	@Override
	public EventBean getEventById(int eventId) {
		EventBean bean=null;
		bean=dao.getEventById(eventId);
		return bean;
	}
	@Transactional
	@Override
	public int getTotalPages() {
		int n = 0;
		n=dao.getTotalPages();
		return n;
	}
	@Override
	public void setPageNo(int pageNo) {
		dao.setPageNo(pageNo);
	}
	@Override
	public int getPageNo() {
		return dao.getPageNo();
	}
	@Override
	public long getRecordCounts() {
		return dao.getRecordCounts();
	}
	@Override
	public int getRecordsPerPage() {
		 return dao.getRecordsPerPage();
	}
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		dao.setRecordsPerPage(recordsPerPage);	
	}
	@Transactional
	@Override
	public void saveEvent(EventBean event) {
		dao.saveEvent(event);
	}
	@Transactional
	@Override
	public void updateEvent(EventBean bean) {
		dao.updateEvent(bean);
	}
	@Transactional
	@Override
	public void openEvent(int eventId) {
		dao.openEvent(eventId);
	}
	@Transactional
	@Override
	public void closeEvent(int eventId) {
		dao.closeEvent(eventId);
	}
}
