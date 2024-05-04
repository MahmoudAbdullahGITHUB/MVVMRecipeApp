package com.example.mvvmrecipeapp.persention.ui.recipe_list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.datasource.repositories.RecipeRepository
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.persention.ui.recipe_list.RecipeListEvent.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


const val PAGE_SIZE = 30
const val STATE_KEY_PAGE = "recipe.state.page.key"
const val STATE_KEY_QUERY = "recipe.state.query.key"
const val STATE_KEY_LIST_POSITION = "recipe.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "recipe.state.query.selected_category"

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String,
//    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _recipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    val recipes: LiveData<List<Recipe>> get() = _recipes

    val query = mutableStateOf("Chicken")

    var selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var loading = mutableStateOf(false)

    var page = mutableStateOf(1)

    private var recipeListScrollPosition = 0

    var navigatedBackFromRecipeScreen = false


    init {
        /*savedStateHandle.GetRecipeEvent<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.GetRecipeEvent<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.GetRecipeEvent<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
        savedStateHandle.GetRecipeEvent<FoodCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { c ->
            setSelectedCategory(c)
        }*/

        if (recipeListScrollPosition != 0) {
            onTriggerEvent(RestoreStateEvent)
        } else {
            onTriggerEvent(NewSearchEvent)
        }

    }


    fun onTriggerEvent(event: RecipeListEvent) {
        try {
            viewModelScope.launch {
                when (event) {
                    is NewSearchEvent -> {
                        newSearch()
                    }

                    is NextPageEvent -> {
                        nextPage()
                    }

                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            }

        } catch (e: Exception) {
            Log.d(
                TAG, "onTriggerEvent: Exception: $e," +
                        "${e.cause}"
            )
        }
    }


    private suspend fun restoreState() {
        loading.value = true
        val results: MutableList<Recipe> = mutableListOf()
        for (p in 1..page.value) {
            val result = repository.search(
                token = token,
                page = p,
                query = query.value
            )
            results.addAll(result)
            if (p == page.value) {
                _recipes.value = results
                loading.value = false
            }
        }
    }


    private suspend fun newSearch() {

        if (query.value.isNotEmpty()) {

            loading.value = true
            resetSearchState()

            delay(2000)
            val result = repository.search(
                token, page = 1,
                query.value
            )

            _recipes.value = result
            loading.value = false
        }

    }


    // pagination
    private suspend fun nextPage() {
        if ((recipeListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            loading.value = true
            incrementPage()
            Log.d(TAG, "nextPage: triggered: ${page.value}")
            delay(1000)

            if (page.value > 1) {
                val result = repository.search(
                    token, page = page.value,
                    query.value
                )
                Log.d(TAG, "nextPage: triggered: ${page.value}")
                appendRecipes(result)
            }
            loading.value = false

        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val current = ArrayList(this._recipes.value)
        current.addAll(recipes)
        this._recipes.value = current
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    fun onChangeRecipeScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }


    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    var tabIndex: Int = 0
    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        setSelectedCategory(newCategory)
        tabIndex = newCategory?.tabIndex ?: 0
        onQueryChanged(category)
    }

    private fun resetSearchState() {
        _recipes.value = listOf()
        if (selectedCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        setSelectedCategory(null)
        selectedCategory.value = null
    }


    private fun setListScrollPosition(position: Int) {
        recipeListScrollPosition = position
//        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page.value = page
//        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: FoodCategory?) {
        selectedCategory.value = category
//        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun setQuery(query: String) {
        this.query.value = query
//        savedStateHandle.set(STATE_KEY_QUERY, query)
    }


}