package com.example.mvvmrecipeapp.persention.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.util.DEFAULT_RECIPE_IMAGE
import com.example.mvvmrecipeapp.util.loadPicture
import com.example.mvvmrecipeapp.util.recipeJson

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun RecipeView(
    recipe: Recipe = recipeJson
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
//            .padding(vertical = 8.dp)
    ) {
        recipe.featuredImage.let { url ->
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(
                text = recipe.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentWidth(Alignment.Start),
            )

            Text(
                text = recipe.rating.toString(),
                modifier = Modifier
                    .fillMaxWidth()
//                        .background(color = Color.Green)
                    .wrapContentWidth(align = Alignment.End)
                    .align(Alignment.CenterVertically),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        }
        recipe.publisher?.let { publisher ->
            val updated = recipe.dateUpdated
            Text(
                text = if (updated != null) {
                    "Updated $updated by $publisher"
                } else {
                    "By $publisher"
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }
        for (ingredient in recipe.ingredients) {
            Text(
                text = ingredient,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

    }


}