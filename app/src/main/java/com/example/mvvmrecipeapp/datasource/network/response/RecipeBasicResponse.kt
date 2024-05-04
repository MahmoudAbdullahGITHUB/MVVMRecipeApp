package com.example.mvvmrecipeapp.datasource.network.response

import com.example.mvvmrecipeapp.datasource.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeBasicResponse(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("previous")
    val previous: String,

    @SerializedName("results")
    val results: List<RecipeDto>
)