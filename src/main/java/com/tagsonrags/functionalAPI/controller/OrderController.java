package com.tagsonrags.functionalAPI.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tagsonrags.functionalAPI.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getOneOrder(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getOrderById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllOrders() {
		return new ResponseEntity<Object>(service.getOrders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteOrderId(@PathVariable Long id) {
		try {
			service.deleteOrder(id);
			return new ResponseEntity<Object>("Deleted order ID: " + id, HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	@RequestMapping(value="/{buyerId}", method=RequestMethod.POST)
	public ResponseEntity<Object> createANewOrder(@RequestBody Set<Long> articleIds, @PathVariable Long buyerId) {
		try {
			return new ResponseEntity<Object>(service.createNewOrder(articleIds, buyerId), HttpStatus.OK);
		} catch (Exception e) {
		return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

		

}
