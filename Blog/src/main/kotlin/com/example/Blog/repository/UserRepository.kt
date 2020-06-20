package com.example.Blog.repository

import org.springframework.data.repository.CrudRepository
import com.example.Blog.entity.User

interface UserRepository: CrudRepository<User, Long> {
	fun findByLogin(login: String): User?
}