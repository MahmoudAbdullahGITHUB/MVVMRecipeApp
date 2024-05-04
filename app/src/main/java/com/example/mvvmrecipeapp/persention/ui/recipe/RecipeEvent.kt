package com.example.mvvmrecipeapp.persention.ui.recipe

sealed class RecipeEvent {

    data class GetRecipeEvent(val id: Int) : RecipeEvent()

}