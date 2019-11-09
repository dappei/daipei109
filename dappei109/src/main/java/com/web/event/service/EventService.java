package com.web.event.service;

import java.util.List;

import com.web.event.model.EventBean;

public interface EventService {
	List<EventBean> getPageEvents();
	List<EventBean> getCloseEvents();
	public EventBean  getEventById(int eventId);
	void setPageNo(int pageNo);
	int getPageNo();
	long getRecordCounts();
	int getTotalPages();
	int getRecordsPerPage();
	void setRecordsPerPage(int recordsPerPage);
	void saveEvent(EventBean event);
	void updateEvent(EventBean bean);
	void openEvent(int eventId);
	void closeEvent(int eventId);
}
