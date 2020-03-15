package com.tagsonrags.functionalAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.tagsonrags.functionalAPI.entity.Address;


public interface AddressRepository extends CrudRepository<Address, Long> {

}
