package com.example.Blog.entity

import java.time.LocalDateTime
import javax.persistence.*
import com.example.Blog.extension.toSlug

@Entity
class Article (
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne var author: User,
    var slug: String = title.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue var id: Long? = null)
