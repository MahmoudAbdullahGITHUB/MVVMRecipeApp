package com.example.mvvmrecipeapp.domain.model

data class Recipe(
    val id: Int = 0,
    val title: String = "title",
    val publisher: String? = "publisher",
    val featuredImage: String = "featuredImage",
    val rating: Int = 0,
    val sourceUrl: String = "sourceUrl",
    val ingredients: List<String> = listOf(),
    val dateAdded: String = "dateAdded",
    val dateUpdated: String? = "dateUpdated",
    val description: String? = "description",
)