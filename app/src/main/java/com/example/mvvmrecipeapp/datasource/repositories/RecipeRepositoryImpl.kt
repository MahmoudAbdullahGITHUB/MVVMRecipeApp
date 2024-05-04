package com.example.mvvmrecipeapp.datasource.repositories

import com.example.mvvmrecipeapp.datasource.network.RecipeService
import com.example.mvvmrecipeapp.datasource.network.model.RecipeDtoMapper
import com.example.mvvmrecipeapp.domain.model.Recipe

class RecipeRepositoryImpl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper,
) : RecipeRepository {

    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {

        return mapper.mapToDomainList(recipeService.search(token, page, query).results)

    }

    override suspend fun get(token: String, id: Int): Recipe {

        return mapper.mapToDomainModel(recipeService.get(token, id))

    }


}