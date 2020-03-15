package com.tagsonrags.functionalAPI.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tagsonrags.functionalAPI.entity.Article;
import com.tagsonrags.functionalAPI.entity.Listing;
import com.tagsonrags.functionalAPI.entity.User;
import com.tagsonrags.functionalAPI.repository.ArticleRepository;
import com.tagsonrags.functionalAPI.repository.ListingRepository;
import com.tagsonrags.functionalAPI.repository.UserRepository;
import com.tagsonrags.functionalAPI.util.ListingStatus;

@Service
public class ListingService {
	

	@Autowired
	private ListingRepository repo;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Listing findOneListing(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Iterable<Listing> findAllListings () {
		return repo.findAll();
	}
	
	public Listing createNewListing(Listing listingData, Long userId) throws Exception {
		try {
			Listing listing = new Listing();
			Article article = listingData.getArticle();
			listing.setArticle(article);
			listing.setStatus(listingData.getStatus());
			listing.setUser(userRepo.findOne(userId));
			addListingToUserSet(listing, userId);
			return repo.save(listing);
		} catch (Exception e) {
			throw new Exception("Exception occurred while trying to create new Listing");
		}
	}
	

	private void addListingToUserSet(Listing listing, Long userId) {
		User user =  userRepo.findOne(userId);
		Set<Listing> set = user.getListing();
		set.add(listing);
		userRepo.save(user);
	}

	public Listing cancelListing(Long id) throws Exception {
		try {
			Listing listing = repo.findOne(id);
			listing.setStatus(ListingStatus.CLOSED);
			return repo.save(listing);
		} catch (Exception e) {
			throw new Exception("Unable to update listing.");
		}
	}
	
	public Listing completedOrderDeliver(Long id) throws Exception {
		try {
			Listing listing = repo.findOne(id);
			listing.setStatus(ListingStatus.DELIVERED);
			return repo.save(listing);
		} catch (Exception e) {
			throw new Exception("Unable to update Listing.");
		}
	}
	
	public void deleteListing(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			throw new Exception("Unable to delete Listing with ID: " + id);
		}
		
		
	}

	public Listing pendingDelivery(Long id) throws Exception {
		try { 
			Listing listing = repo.findOne(id);
			listing.setStatus(ListingStatus.PENDING);
			return repo.save(listing);
		} catch (Exception e) {
			throw new Exception("Unable to update listing staus");
		}
	}
	
	public Article updateArticle (Article article) throws Exception {
		try {
			Article updatedArticle = articleRepo.findOne(article.getId());
			updatedArticle.setBrand(article.getBrand());
			updatedArticle.setArticleType(article.getArticleType());
			updatedArticle.setArticleAge(article.getArticleAge());
			updatedArticle.setPrice(article.getPrice());
			updatedArticle.setDescription(article.getDescription());
			updatedArticle.setArticlePictureUrl(article.getArticlePictureUrl());
			return articleRepo.save(updatedArticle);
		} catch (Exception e) {
			throw new Exception("Unable to update Article with ID: " + article.getId());
		}
	}
	
}
