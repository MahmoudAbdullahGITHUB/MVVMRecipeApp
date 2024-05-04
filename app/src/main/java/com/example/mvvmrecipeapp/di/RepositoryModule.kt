package com.example.mvvmrecipeapp.di

import com.example.mvvmrecipeapp.datasource.network.RecipeService
import com.example.mvvmrecipeapp.datasource.network.model.RecipeDtoMapper
import com.example.mvvmrecipeapp.datasource.repositories.RecipeRepository
import com.example.mvvmrecipeapp.datasource.repositories.RecipeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        mapper: RecipeDtoMapper,
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeService, mapper)
    }

}