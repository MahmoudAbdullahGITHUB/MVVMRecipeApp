package com.example.mvvmrecipeapp.persention.components

import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.persention.components.util.ShimmerListItem
import com.example.mvvmrecipeapp.persention.components.util.SnackbarController
import com.example.mvvmrecipeapp.persention.ui.recipe_list.PAGE_SIZE
import com.example.mvvmrecipeapp.persention.ui.recipe_list.RecipeListEvent
import com.example.mvvmrecipeapp.persention.ui.recipe_list.shimmerDummySize
import kotlinx.coroutines.launch

@Composable
fun RecipeList(
    values: PaddingValues,
    loading: Boolean,
    recipes: List<Recipe>,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    page: Int,
    onNextPage: (RecipeListEvent) -> Unit,
    lazyListState: LazyListState,
    snackbarController: SnackbarController,
    snackbarHostState: SnackbarHostState,
    context: Context,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(values)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            if (loading && recipes.isEmpty()) {
                items(shimmerDummySize) {
                    ShimmerListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            } else {
                itemsIndexed(recipes) { index, recipe ->

                    println("index equal $index")
                    onChangeRecipeScrollPosition(index)

                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onNextPage(RecipeListEvent.NextPageEvent)
                    }

                    RecipeCard(recipe = recipe,
                        onClick = {
                            if (recipe.id != null) {
                                val bundle = Bundle()
                                bundle.putInt("recipeId", recipe.id)
                                navController.navigate(R.id.viewRecipe, bundle)
                            } else {
                                snackbarController.getScope().launch {
                                    snackbarController.showSnackbar(
                                        message = "BaseSnackbar",
                                        actionLabel = "action",
                                        snackbarHostState = snackbarHostState,
                                        context = context
                                    )
                                }

                            }

                        }
                    )
                }
            }
        }
    }

}