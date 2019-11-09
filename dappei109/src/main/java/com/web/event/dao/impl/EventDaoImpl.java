package com.web.event.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.event.dao.EventDao;
import com.web.event.model.EventBean;
@Repository
public class EventDaoImpl implements Serializable,EventDao {

	private static final long serialVersionUID = 1L;
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = 6; // 預設值：每頁三筆
	private int totalPages = -1;
	
	SessionFactory factory;
	@Autowired	
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public int getTotalPages() {
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventBean> getPageEvents() {
		List<EventBean> list=new ArrayList<EventBean>();
		String hql="From EventBean where exist=1";
		Session session=factory.getCurrentSession();
		
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		list = session.createQuery(hql)
                .setFirstResult(startRecordNo)
                .setMaxResults(recordsPerPage)
                .list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EventBean> getCloseEvents() {
		List<EventBean> list=new ArrayList<EventBean>();
		String hql="From EventBean where exist=0";
		Session session=factory.getCurrentSession();
		
		int startRecordNo = (pageNo - 1) * recordsPerPage;
		
		list = session.createQuery(hql)
                .setFirstResult(startRecordNo)
                .setMaxResults(recordsPerPage)
                .list();
		return list;
	}
	
	@Override
	public EventBean getEventById(int eventId) {
		Session session=factory.getCurrentSession();
		EventBean eb=session.get(EventBean.class, eventId);
		return eb;
	}

	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	@Override
	public int getPageNo() {
		return pageNo;
	}
	
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;		
	}
	
	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	@Override
	public void saveEvent(EventBean event) {
		Session session=factory.getCurrentSession();
		session.save(event);
	}
	
	@Override
	public void updateEvent(EventBean bean) {
		if (bean != null && bean.getEventId() != null) 	{
			Session session = factory.getCurrentSession();;
			session.update(bean);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
        String hql = "SELECT count(*) FROM EventBean";
        Session session = factory.getCurrentSession();
        List<Long> list = session.createQuery(hql).list();
        if (list.size() > 0) {
            count = list.get(0);
        }
        return count;
	}

	@Override
	public void openEvent(int eventId) {
		String hql="Update EventBean SET exist= 1 where eventId=:id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql)
               .setParameter("id", eventId)
               .executeUpdate();
	}

	@Override
	public void closeEvent(int eventId) {
		String hql="Update EventBean SET exist= 0 where eventId=:id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql)
               .setParameter("id", eventId)
               .executeUpdate();
	}

}
