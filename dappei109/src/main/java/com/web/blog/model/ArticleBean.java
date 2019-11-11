package com.web.blog.model;

import java.io.Serializable;
import java.sql.Blob;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// 本類別封裝單筆書籍資料
@Entity
@Table(name = "Article")
public class ArticleBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleid;			   //文章ID
	private String title;				   //標題ID
	private String articlecontent;         //文章內容ID

	@Transient
	private String articleShortContent;	   //對文字的長短內容作限制
	private String author;				   //暱稱
	private Blob coverImage;  			   //照片
	private String fileName;			   //照片
	
	@ManyToOne
	@JoinColumn(name = "CATEGORYID")
	private CompanyBean companyBean;
	private Integer memberId;              //會員ID

	public ArticleBean(Integer memberID, Integer articleidID, String title, String author, String articlecontent,
			String fileName, Blob coverImage) {
		this.memberId = memberID;
		this.articleid = articleidID;
		this.articlecontent = articlecontent;
		this.title = title;
		this.author = author;
		this.fileName = fileName;
		this.coverImage = coverImage;
	}

	@Transient
	private MultipartFile productImage;  	
	
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public String getArticlecontent() {
		return articlecontent;
	}

	public void setArticlecontent(String articlecontent) {
		this.articlecontent = articlecontent;
	}

	public ArticleBean() {
	}

	public Integer getArticleid() {
		return articleid;
	}

	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}
	
	public String getArticleShortContent() {
		return articleShortContent;
	}

	public void setArticleShortContent(String articleShortContent) {
		this.articleShortContent = articleShortContent;
	}

	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}
	


}
