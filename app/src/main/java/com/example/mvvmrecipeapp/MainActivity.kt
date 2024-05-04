package com.example.mvvmrecipeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import com.example.mvvmrecipeapp.persention.theme.MVVMRecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


//        setContent {
//            MVVMRecipeAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//
//                }
//            }
//        }
    }
}