package com.web.blog.service;

import java.util.List;

import com.web.blog.model.ArticleBean;
import com.web.blog.model.CompanyBean;

public interface ProductService {
	List<ArticleBean> getAllProducts();

	void updateAllStocks();

	public ArticleBean getProductById(int id);

	void addProduct(ArticleBean product);

	CompanyBean getCompanyById(int companyId);

	List<CompanyBean> getCompanyList();


	List<String> getAllCategories();

	CompanyBean findByCategoryId(int id);
}
