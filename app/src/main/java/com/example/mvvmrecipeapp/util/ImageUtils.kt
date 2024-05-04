package com.example.mvvmrecipeapp.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.mvvmrecipeapp.R

val DEFAULT_RECIPE_IMAGE = R.drawable.empty_plate

@Composable
fun loadPicture(
    url: String,
    @DrawableRes defaultImage: Int
): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    // show default image while image loads
//    Glide.with(LocalContext.current)
//        .asBitmap()
//        .load(defaultImage)
//        .into(object : CustomTarget<Bitmap>() {
//            override fun onLoadCleared(placeholder: Drawable?) {}
//            override fun onResourceReady(
//                resource: Bitmap,
//                transition: Transition<in Bitmap>?
//            ) {
//                bitmapState.value = resource
//            }
//        })

    // GetRecipeEvent network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}


@Composable
fun loadPicture3(
    url: String,
    @DrawableRes defaultImage: Int = DEFAULT_RECIPE_IMAGE
): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = remember { mutableStateOf(null) }

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(url)
        .placeholder(defaultImage)
        .dontAnimate()
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {
                // Optional: Handle placeholder clearing if needed
            }

            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}
