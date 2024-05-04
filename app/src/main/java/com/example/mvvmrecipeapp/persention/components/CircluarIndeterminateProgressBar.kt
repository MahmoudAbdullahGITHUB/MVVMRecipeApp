package com.example.mvvmrecipeapp.persention.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val progressBar = createRef()
            val topGuidLine = createGuidelineFromTop(0.3f)

            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.constrainAs(progressBar) {
                    top.linkTo(topGuidLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

        }
    }

    /** The equivalent for in compose without ConstraintLayout

    //        Row(
    //            modifier = Modifier
    //                .fillMaxWidth()
    //                .padding(50.dp),
    //            horizontalArrangement = Arrangement.Center
    //        ) {
    //            CircularProgressIndicator(
    //                color = MaterialTheme.colorScheme.primary
    //            )
    //        }*/


}