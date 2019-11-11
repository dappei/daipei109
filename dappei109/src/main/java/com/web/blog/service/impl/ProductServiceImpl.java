package com.web.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.dao.ArticleDao;
import com.web.blog.model.ArticleBean;
import com.web.blog.model.CompanyBean;
import com.web.blog.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	ArticleDao dao;

	@Autowired
	public void setDao(ArticleDao dao) {
		this.dao = dao;
	}

	@Transactional
	@Override
	public List<ArticleBean> getAllProducts() {
		return dao.getAllProducts();
	}

	@Transactional
	@Override
	public void updateAllStocks() {
		List<ArticleBean> allProducts = dao.getAllProducts();

	}


	@Transactional
	@Override
	public ArticleBean getProductById(int id) {
		return dao.getProductById(id);
	}

	@Transactional
	@Override
	public void addProduct(ArticleBean product) {
		dao.addProduct(product);
	}

	@Transactional
	@Override
	public CompanyBean getCompanyById(int companyId) {
		return dao.getCompanyById(companyId);
	}

	@Transactional
	@Override
	public List<CompanyBean> getCompanyList() {
		return dao.getCompanyList();
	}
	@Transactional
	@Override
	public List<String> getAllCategories() {
		
		return dao.getAllCategories();
	}

	@Transactional
	@Override
	public CompanyBean findByCategoryId(int id) {
		return dao.findByCategoryId(id);
	}

	
}