package com.example.mvvmrecipeapp.persention.ui.recipe

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.datasource.repositories.RecipeRepository
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.persention.ui.recipe.RecipeEvent.GetRecipeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {

    var loading: MutableState<Boolean> = mutableStateOf(false)

    private val _recipe: MutableState<Recipe?> = mutableStateOf(null)
    val recipe: MutableState<Recipe?> = _recipe


    fun onTriggerEvent(event: RecipeEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is GetRecipeEvent -> {
                        if (_recipe.value == null) {
                            getRecipe(event.id)
                        }
                    }
                }


            } catch (e: Exception) {
                Log.d("TAG", "onTriggerEvent: Exception $e ${e.cause}")
            }
        }
    }

    private suspend fun getRecipe(id: Int) {
        loading.value = true
        delay(1000)

        val result = repository.get(token = token, id = id)
        _recipe.value = result

        loading.value = false
    }


}