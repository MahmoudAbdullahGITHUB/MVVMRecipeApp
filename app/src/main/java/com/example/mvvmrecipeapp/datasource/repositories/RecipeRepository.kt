package com.example.mvvmrecipeapp.datasource.repositories

import com.example.mvvmrecipeapp.datasource.network.model.RecipeDto
import com.example.mvvmrecipeapp.datasource.network.response.RecipeBasicResponse
import com.example.mvvmrecipeapp.domain.model.Recipe
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeRepository {

    suspend fun search(token: String, page: Int, query: String): List<Recipe>


    suspend fun get(token: String, id: Int): Recipe


}