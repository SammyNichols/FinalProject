package com.tagsonrags.functionalAPI.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tagsonrags.functionalAPI.util.ListingStatus;

@Entity
public class Listing {
	
	private Long id;
	@JsonIgnore
	private User user;
	private Article article;
	private ListingStatus status;


	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	

	public ListingStatus getStatus() {
		return status;
	}
	public void setStatus(ListingStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


}
