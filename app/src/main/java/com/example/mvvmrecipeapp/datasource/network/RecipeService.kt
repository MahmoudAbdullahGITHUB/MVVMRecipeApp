package com.example.mvvmrecipeapp.datasource.network

import com.example.mvvmrecipeapp.datasource.network.model.RecipeDto
import com.example.mvvmrecipeapp.datasource.network.response.RecipeBasicResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecipeService {

    @GET("search")
    suspend fun search(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeBasicResponse


    @GET("get")
    suspend fun get(
        @Header("Authorization") token: String,
        @Query("id") id: Int,
    ): RecipeDto

}