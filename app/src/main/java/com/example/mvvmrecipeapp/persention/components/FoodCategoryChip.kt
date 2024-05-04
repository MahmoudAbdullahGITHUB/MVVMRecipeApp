package com.example.mvvmrecipeapp.persention.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory

@Composable
fun FoodCategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecutionSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 6.dp),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChanged(category)
                    onExecutionSearch()
                }

            ),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )

        }

    }

}





















