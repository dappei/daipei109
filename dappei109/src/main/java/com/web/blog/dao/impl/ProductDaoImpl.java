package com.web.blog.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dao.ArticleDao;
import com.web.blog.model.ArticleBean;
import com.web.blog.model.CompanyBean;

@Repository
public class ProductDaoImpl implements ArticleDao {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ArticleBean> getAllProducts() {
		String hql = "FROM ArticleBean";
		Session session = null;
		List<ArticleBean> list = new ArrayList<>();
		session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void addProduct(ArticleBean articleBean) {
		Session session = factory.getCurrentSession();
//		System.out.println(product.getCompanyId());
//		CompanyBean cb = getCompanyById(product.getCompanyId());
//		product.setCompanyBean(cb);
		session.save(articleBean);
	}

	@Override
	public CompanyBean getCompanyById(int companyId) {
		CompanyBean cb = null;
		Session session = factory.getCurrentSession();
		cb = session.get(CompanyBean.class, companyId);
		return cb;
	}

	@Override
	public List<CompanyBean> getCompanyList() {
		String hql = "FROM CompanyBean";
		Session session = factory.getCurrentSession();
		List<CompanyBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	

	@Override
	public ArticleBean getProductById(Integer id) {
		Session session = factory.getCurrentSession();
		ArticleBean bb = session.get(ArticleBean.class, id);
		return bb;
	}

//	@SuppressWarnings("unused")
//	@Override
//	public void updateStock(int productId, int newQuantity) {
//		String hql = "UPDATE ArtcileBean b SET b.stock = :stock WHERE bookId = :id";
//		Session session = factory.getCurrentSession();
//
//		int n = session.createQuery(hql).setParameter("stock", newQuantity).setParameter("id", productId)
//				.executeUpdate();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCategories() {
		String hql = "FROM CompanyBean";
		Session session = factory.getCurrentSession();
		List<String> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public CompanyBean findByCategoryId(int id) {
		Session session = factory.getCurrentSession();
		CompanyBean companyBean = session.get(CompanyBean.class, id);
		return companyBean;
	}

	
}
