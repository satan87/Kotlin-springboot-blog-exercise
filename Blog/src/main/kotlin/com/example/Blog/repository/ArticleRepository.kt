package com.example.Blog.repository

import org.springframework.data.repository.CrudRepository
import com.example.Blog.entity.Article

interface ArticleRepository: CrudRepository<Article, Long> {
	fun findBySlug(slug: String): Article?
	fun findAllByOrderByAddedAtDesc(): Iterable<Article>
	
}