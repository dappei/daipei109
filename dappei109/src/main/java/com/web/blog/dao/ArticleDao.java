package com.web.blog.dao;

import java.util.List;

import com.web.blog.model.ArticleBean;
import com.web.blog.model.CompanyBean;

public interface ArticleDao {
	List<ArticleBean> getAllProducts();

//	void updateStock(int productId, int newQuantity);

	

	public ArticleBean getProductById(Integer id);

	void addProduct(ArticleBean product);

	CompanyBean getCompanyById(int companyId);

	List<CompanyBean> getCompanyList();
	
	List<String> getAllCategories();
	
	CompanyBean findByCategoryId(int id);
}
