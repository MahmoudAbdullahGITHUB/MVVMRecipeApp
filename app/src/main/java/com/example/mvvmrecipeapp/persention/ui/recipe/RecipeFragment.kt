package com.example.mvvmrecipeapp.persention.ui.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmrecipeapp.BaseApplication
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.persention.components.CircularIndeterminateProgressBar
import com.example.mvvmrecipeapp.persention.components.RecipeView
import com.example.mvvmrecipeapp.persention.components.util.ShimmerItem
import com.example.mvvmrecipeapp.persention.components.util.ShimmerListItem
import com.example.mvvmrecipeapp.persention.components.util.SnackbarController
import com.example.mvvmrecipeapp.persention.theme.AppTheme
import com.example.mvvmrecipeapp.persention.ui.recipe.RecipeEvent.GetRecipeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(scope = lifecycleScope)

    private val viewModel: RecipeViewModel by viewModels()

    private var recipeId: MutableState<Int> = mutableStateOf(-1)
//    private var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { rId ->
            viewModel.onTriggerEvent(GetRecipeEvent(rId))
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val recipe = viewModel.recipe.value
                val loading = viewModel.loading.value

                val snackbarHostState = remember { SnackbarHostState() }


                AppTheme(
                    darkTheme = application.isDark.value,
                    displayProgressBar = loading
                ) {
                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(snackbarHostState)
                        },
                        topBar = {},
                        bottomBar = {}
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            if (loading && recipe == null) {

                                ShimmerItem(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
//                                Text(text = "Loading...")
                            } else {
                                recipe?.let {
                                    if (it.id == 2050) {
                                        snackbarController.showSnackbar(
                                            message = "An error Occurred " +
                                                    "with this recipe.",
                                            actionLabel = "Ok",
                                            snackbarHostState = snackbarHostState,
                                            context = context
                                        )

                                    } else
                                        RecipeView(recipe = it)
                                }
                            }

                        }
                    }
                }

//                recipe?.let {
//                    RecipeView(recipe = recipe)
//
//                }


//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(
//                        text = recipe?.let {
//                            "Selected recipeId: ${recipe.id}"
//                        } ?: "Loading...",
//                        style = TextStyle(
//                            fontSize = 21.sp
//                        )
//                    )
//                }
            }
        }
    }

}