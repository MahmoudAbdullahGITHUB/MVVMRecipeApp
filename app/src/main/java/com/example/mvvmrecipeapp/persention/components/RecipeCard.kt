package com.example.mvvmrecipeapp.persention.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.util.DEFAULT_RECIPE_IMAGE
import com.example.mvvmrecipeapp.util.loadPicture


@Composable
fun RecipeCard(
    recipe: Recipe,
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
    ) {
        Column {
            recipe.featuredImage?.let { url ->
                val image = loadPicture(url = url, defaultImage = DEFAULT_RECIPE_IMAGE).value
                image?.asImageBitmap()?.let {
                    Image(
                        bitmap = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Empty Image",

                        )
                }
            }
            recipe.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(.85f),
                    )
                    Text(
                        text = recipe.rating.toString(),
                        textAlign = TextAlign.End
                    )
                }
            }

        }


    }


}