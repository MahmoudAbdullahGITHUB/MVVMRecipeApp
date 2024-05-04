package com.example.mvvmrecipeapp.persention.ui.recipe_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.mvvmrecipeapp.BaseApplication
import com.example.mvvmrecipeapp.persention.components.RecipeList
import com.example.mvvmrecipeapp.persention.components.SearchAppBar
import com.example.mvvmrecipeapp.persention.components.util.*
import com.example.mvvmrecipeapp.persention.theme.AppTheme
import com.example.mvvmrecipeapp.persention.ui.recipe_list.RecipeListEvent.NewSearchEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

const val shimmerDummySize = 5

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()
    private val snackbarController = SnackbarController(scope = lifecycleScope)
    var tabIndex = 0
    var navigatedBack = false

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        /*viewModel.recipes.observe(viewLifecycleOwner) { recipes ->

        }
        */

        return ComposeView(requireContext()).apply {

            setContent {
                val recipes = viewModel.recipes.observeAsState().value ?: listOf()
                val query = viewModel.query.value
                val controller = LocalSoftwareKeyboardController.current
                val selectedCategory = viewModel.selectedCategory.value
                val loading = viewModel.loading.value
                tabIndex = viewModel.tabIndex
                val lazyListState = rememberLazyListState()
                val snackbarHostState = remember { SnackbarHostState() }
                val page = viewModel.page.value
                navigatedBack = viewModel.navigatedBackFromRecipeScreen

                AppTheme(darkTheme = application.isDark.value, displayProgressBar = loading) {


                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = { viewModel.onTriggerEvent(NewSearchEvent) },
                                controller = controller,
                                tabIndex = tabIndex,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = {
                                    snackbarController.getScope().launch {
                                        snackbarController.showSnackbar(
                                            message = "BaseSnackbar",
                                            actionLabel = "action",
                                            snackbarHostState = snackbarHostState,
                                            context = context
                                        )
                                    }

//                                    application.toggleTheme()
                                }
                            )
                        },
                        bottomBar = {

                        },
                    ) { values ->
                        RecipeList(
                            values = values,
                            loading = loading,
                            recipes = recipes,
                            onChangeRecipeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                            page = page,
                            onNextPage = viewModel::onTriggerEvent,
                            lazyListState = lazyListState,
                            snackbarController = snackbarController,
                            snackbarHostState = snackbarHostState,
                            context = LocalContext.current,
                            navController = findNavController()
                        )
                    }


//                     TODO: LaunchedEffect
                    LaunchedEffect(loading) {
                        println(
                            "navigatedBack $navigatedBack vs " +
                                    "viewModel.navigatedBackFromRecipeScreen " +
                                    "${viewModel.navigatedBackFromRecipeScreen}"
                        )

                        if (!navigatedBack) {
                            if (!loading && page == 0) {
                                lazyListState.scrollToItem(0)
                            }
                        } else {
                            viewModel.navigatedBackFromRecipeScreen = false
                        }

                    }
                    LaunchedEffect(tabIndex) {
                        if (!navigatedBack) {
                            lazyListState.scrollToItem(0)
                        } else {
                            viewModel.navigatedBackFromRecipeScreen = false
                        }
                    }


                }


            }
        }
    }

    override fun onPause() {
        super.onPause()
        navigatedBack
        viewModel.navigatedBackFromRecipeScreen = true
    }

    override fun onResume() {
        super.onResume()
        navigatedBack

    }
}


