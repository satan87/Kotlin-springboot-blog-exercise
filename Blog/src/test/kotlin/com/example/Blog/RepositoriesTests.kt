package com.example.Blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import com.example.Blog.repository.UserRepository
import com.example.Blog.repository.ArticleRepository
import com.example.Blog.entity.User
import com.example.Blog.entity.Article

@DataJpaTest
class RepositoriesTests @Autowired constructor(
		val entityManager: TestEntityManager,
		val userRepository: UserRepository,
		val articleRepository: ArticleRepository) {

	@Test
	fun `When findByIdOrNull then return Article`() {
		val juergen = User("springjuergen", "Juergen", "Hoeller")
		entityManager.persist(juergen)
		val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", juergen)
		entityManager.persist(article)
		entityManager.flush()
		val found = articleRepository.findByIdOrNull(article.id!!)
		assertThat(found).isEqualTo(article)
	}

	@Test
	fun `When findAll then return List of Articles`() {
		val nicolas = User("ns", "nico", "las")
		entityManager.persist(nicolas)
		val article1 = Article("my First Title","hd", "desc", nicolas)
		entityManager.persist(article1)
		val article2 = Article("my Second Title","hd", "desc", nicolas)
		entityManager.persist(article2)
		val article3 = Article("not third","hd", "desc", nicolas)
		entityManager.persist(article3)
		
		entityManager.flush()
		
		val found = articleRepository.findAll()
		assertThat(found).size().isEqualTo(3)
		
	}


	@Test
	fun `When findByLogin then return User`() {
		val juergen = User("springjuergen", "Juergen", "Hoeller")
		entityManager.persist(juergen)
		entityManager.flush()
		val user = userRepository.findByLogin(juergen.login)
		assertThat(user).isEqualTo(juergen)
	}
}