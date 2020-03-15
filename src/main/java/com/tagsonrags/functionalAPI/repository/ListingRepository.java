package com.tagsonrags.functionalAPI.repository;

import org.springframework.data.repository.CrudRepository;
import com.tagsonrags.functionalAPI.entity.Listing;



public interface ListingRepository extends CrudRepository<Listing, Long>{

}
