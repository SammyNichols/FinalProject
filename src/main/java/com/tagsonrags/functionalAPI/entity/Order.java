package com.tagsonrags.functionalAPI.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Order {
	
	private Long id;
	private LocalDate orderedOn;
	private Address shippingAddress;
	private Set<Article> articles;
	private User buyer;
	private double total;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "ShippingTo")
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	@OneToMany(mappedBy = "order")
	public Set<Article> getArticles() {
		return articles;
	}
	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
	
	@ManyToOne
	@JoinColumn(name = "buyerId")
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public LocalDate getOrderedOn() {
		return orderedOn;
	}
	public void setOrderedOn(LocalDate orderedOn) {
		this.orderedOn = orderedOn;
	}
	

}
