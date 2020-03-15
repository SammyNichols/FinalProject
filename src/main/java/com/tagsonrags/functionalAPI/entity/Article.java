package com.tagsonrags.functionalAPI.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tagsonrags.functionalAPI.util.ArticleAge;
import com.tagsonrags.functionalAPI.util.ArticleType;



@Entity
public class Article {
	
	private Long id;
	private String brand;
	private ArticleType articleType;
	private ArticleAge articleAge;
	private double price;
	private String description;
	private String articlePictureUrl;
	
	@JsonIgnore
	private Listing listing;
	
	@JsonIgnore
	private Order order;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public ArticleType getArticleType() {
		return articleType;
	}
	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}
	public ArticleAge getArticleAge() {
		return articleAge;
	}
	public void setArticleAge(ArticleAge articleAge) {
		this.articleAge = articleAge;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getArticlePictureUrl() {
		return articlePictureUrl;
	}
	public void setArticlePictureUrl(String articlePictureUrl) {
		this.articlePictureUrl = articlePictureUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@OneToOne(mappedBy = "article")
	@JoinColumn(name = "listingID", referencedColumnName = "id")
	public Listing getListing() {
		return listing;
	}
	public void setListing(Listing listing) {
		this.listing = listing;
	}
	
	@ManyToOne
	@JoinColumn(name = "OrderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}


}
