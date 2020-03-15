package com.tagsonrags.functionalAPI.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class User {
	

	private Long id;
	private String firstName;
	private String lastName;
	private Address address;
	private Long phoneNumber;
	private Set<Listing> listing;
	private Set<Order> orders;

	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<Listing> getListing() {
		return listing;
	}

	public void setListing(Set<Listing> listing) {
		this.listing = listing;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@OneToMany(mappedBy = "buyer")
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}



}
