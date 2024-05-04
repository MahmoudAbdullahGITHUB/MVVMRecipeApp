package com.example.mvvmrecipeapp.persention.ui.recipe_list


/**
 * RestoreStateEvent
 * for saving the state of the app when it's killed by the system
 * and this happen when the device recourse is occupied by other process
 */
sealed class RecipeListEvent {
    object NewSearchEvent : RecipeListEvent()
    object NextPageEvent : RecipeListEvent()


    object RestoreStateEvent : RecipeListEvent()
}