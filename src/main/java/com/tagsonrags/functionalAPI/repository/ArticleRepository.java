package com.tagsonrags.functionalAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.tagsonrags.functionalAPI.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
