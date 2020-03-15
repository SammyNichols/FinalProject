package com.tagsonrags.functionalAPI.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tagsonrags.functionalAPI.entity.Article;
import com.tagsonrags.functionalAPI.entity.Listing;
import com.tagsonrags.functionalAPI.entity.Order;
import com.tagsonrags.functionalAPI.entity.User;
import com.tagsonrags.functionalAPI.repository.ArticleRepository;
import com.tagsonrags.functionalAPI.repository.ListingRepository;
import com.tagsonrags.functionalAPI.repository.OrderRepository;
import com.tagsonrags.functionalAPI.repository.UserRepository;
import com.tagsonrags.functionalAPI.util.ListingStatus;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired 
	private ArticleRepository articleRepo;
	
	@Autowired
	private ListingRepository listingRepo;
	
	@Autowired
	private ListingService listingService;
	
	
	
	public Order getOrderById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Iterable<Order> getOrders() {
		return repo.findAll();
	}

	public Order createNewOrder(Set<Long> articleIds, Long buyerId) throws Exception {
		try {
			User buyer = userRepo.findOne(buyerId);
			Order newOrder = generateNewOrder(articleIds, buyer);
			updateArticleListings(newOrder.getArticles());
			return repo.save(newOrder);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void deleteOrder(Long id) throws Exception {
		try {
			Order order = repo.findOne(id);
			updateArticleListingOpen(order.getArticles());
			repo.delete(id);
		} catch (Exception e) {
			throw new Exception("Exeption occured while trying to delete order ID: " + id);
		}
	}
	

	private void updateArticleListingOpen(Set<Article> articles) {
		for (Article article : articles) {
			Listing listing = listingRepo.findOne(article.getListing().getId());
			listing.setStatus(ListingStatus.OPEN);
		}
		
	}

	private void updateArticleListings(Set<Article> articles) throws Exception {
		for (Article article : articles) {
			Listing listing = listingRepo.findOne(article.getListing().getId());
			listingService.pendingDelivery(listing.getId());
		}
		
	}

	private Order generateNewOrder(Set<Long> articleIds, User buyer) throws Exception {
		try {
			Order newOrder = new Order();
			newOrder.setArticles(convertToArticleSet(articleRepo.findAll(articleIds)));
			newOrder.setShippingAddress(buyer.getAddress());
			newOrder.setBuyer(buyer);
			newOrder.setOrderedOn(LocalDate.now());
			newOrder.setTotal(calculateOrderTotal(newOrder.getArticles()));
			return repo.save(newOrder);
			
		} catch (Exception e) {
			throw new Exception("Exception Occured while trying to generate new order");
		}
		
	}

	private Set<Article> convertToArticleSet(Iterable<Article> findAll) {
		Set<Article> set = new HashSet<Article>();
		for (Article article : findAll) {
			set.add(article);
		}
		return set;
	}
	
	private double calculateOrderTotal(Set<Article> articles) {
		double total = 0;
		for (Article article : articles) {
			total += (article.getPrice() *article.getArticleAge().getNewPrice());
		}
		return total;
	}
}
