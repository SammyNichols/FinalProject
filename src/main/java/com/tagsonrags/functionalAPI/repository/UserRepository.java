package com.tagsonrags.functionalAPI.repository;

import org.springframework.data.repository.CrudRepository;
import com.tagsonrags.functionalAPI.entity.User;



public interface UserRepository extends CrudRepository<User, Long>{

 
}
