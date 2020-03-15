package com.tagsonrags.functionalAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tagsonrags.functionalAPI.entity.Article;
import com.tagsonrags.functionalAPI.entity.Listing;
import com.tagsonrags.functionalAPI.service.ListingService;

@RestController
@RequestMapping("/listing")
public class ListingController {
	
	@Autowired
	private ListingService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getOneListing(@PathVariable Long id) throws Exception {
		try {
			return new ResponseEntity<Object>(service.findOneListing(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getAllListings() {
		return new ResponseEntity<Object>(service.findAllListings(), HttpStatus.OK);
	}

	@RequestMapping(value="/{userId}", method=RequestMethod.POST)
	public ResponseEntity<Object> addNewListing(@RequestBody Listing listing, @PathVariable Long userId) throws Exception {
		try {
			return new ResponseEntity<Object>(service.createNewListing(listing, userId), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}/closed", method=RequestMethod.PUT)
	public ResponseEntity<Object> closeListing(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.cancelListing(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value="/{id}/completed", method=RequestMethod.PUT)
	public ResponseEntity<Object> completeListing(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.completedOrderDeliver(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value="/{id}/pending", method=RequestMethod.PUT)
	public ResponseEntity<Object> updatePendingListing(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.pendingDelivery(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> removeListing(@PathVariable Long id) {
		try {
			service.deleteListing(id);
			return new ResponseEntity<Object>("Successfully Removed Listing", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value="/article", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateArticle(@RequestBody Article article) {
		try {
			return new ResponseEntity<Object>(service.updateArticle(article), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	
}
