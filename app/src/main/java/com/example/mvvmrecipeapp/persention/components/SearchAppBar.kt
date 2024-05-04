package com.example.mvvmrecipeapp.persention.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory
import com.example.mvvmrecipeapp.persention.ui.recipe_list.getAllFoodCategories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    controller: SoftwareKeyboardController?,
    tabIndex: Int,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onToggleTheme: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(8.dp),
                    value = query,
                    onValueChange = { newString ->
                        onQueryChanged(newString)
                    },
                    label = { Text(text = "Search") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, "")
                    },

                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    colors = TextFieldDefaults
                        .colors(
                            //setting the text field background when it is focused
                            focusedContainerColor = MaterialTheme.colorScheme.background,
//                            //setting the text field background when it is unfocused or initial state
                            unfocusedContainerColor = MaterialTheme.colorScheme.background,
//                            //setting the text field background when it is disabled
                            disabledContainerColor = MaterialTheme.colorScheme.background,
                        ),

                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onExecuteSearch()
                            controller?.hide()
                        },
                    ),
                )
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        onClick = {
                            onToggleTheme()
                        },
                        modifier = Modifier
                            .constrainAs(menu) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    ) {
                        Icon(Icons.Filled.MoreVert, "theme")
                    }
                }

            }
//            Spacer(modifier = Modifier.padding(10.dp))
            val scrollState = rememberScrollState()
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier
                    .fillMaxWidth(),
                edgePadding = 0.dp,
             ) {

                for (category in getAllFoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = category == selectedCategory,
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                        },
                        onExecutionSearch = {
                            onExecuteSearch()
                        }
                    )
                }

            }

        }


    }


}