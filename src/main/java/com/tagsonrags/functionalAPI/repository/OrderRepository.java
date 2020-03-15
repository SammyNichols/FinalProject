package com.tagsonrags.functionalAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.tagsonrags.functionalAPI.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
