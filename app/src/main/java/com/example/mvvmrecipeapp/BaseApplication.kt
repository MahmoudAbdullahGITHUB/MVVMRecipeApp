package com.example.mvvmrecipeapp

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {

    var isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}