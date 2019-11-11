package com.web.blog.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Atriclecategory")
public class CompanyBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	private String categoryName;

	//	一對多的屬性描述
	//	一個分類有多個文章
	@OneToMany(mappedBy = "companyBean", cascade = CascadeType.ALL)
	private Set<ArticleBean> articles = new LinkedHashSet<>();

	public Set<ArticleBean> getArticles() {
		return articles;
	}

	public void setArticles(Set<ArticleBean> articles) {
		this.articles = articles;
	}

	public CompanyBean() {
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

//	public Set<ArticleBean> getArticles() {
//		return Articles;
//	}
//	public void setArticles(Set<ArticleBean> articles) {
//		this.Articles=articles;
//	}

}