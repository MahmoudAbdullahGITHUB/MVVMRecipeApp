package com.example.mvvmrecipeapp.di

import com.example.mvvmrecipeapp.datasource.network.RecipeService
import com.example.mvvmrecipeapp.datasource.network.model.RecipeDtoMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        return client
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl("https://food2fork.ca/api/recipe/")
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }


    @Singleton
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

}