package com.tagsonrags.functionalAPI.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Address {
	
	private Long id;


	private String street;
	private String city;
	private String state;
	private String zip;
	
	@JsonIgnore
	private User user;
	
	@JsonIgnore
	private Set<Order> orderShipping;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "shippingAddress")
	public Set<Order> getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(Set<Order> orderShipping) {
		this.orderShipping = orderShipping;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@OneToOne(mappedBy = "address")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
