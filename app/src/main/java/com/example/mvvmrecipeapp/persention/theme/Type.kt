package com.example.mvvmrecipeapp.persention.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mvvmrecipeapp.R


private val QuickSand = FontFamily(
    Font(R.font.quicksand_light, FontWeight.W300),
    Font(R.font.quicksand_regular, FontWeight.W400),
    Font(R.font.quicksand_medium, FontWeight.W500),
    Font(R.font.quicksand_bold, FontWeight.W600)
)

val QuickSandTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = QuickSand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = QuickSand,
        fontSize = 14.sp
    ),
//    button = TextStyle(
//        fontFamily = QuickSand,
//        fontWeight = FontWeight.W400,
//        fontSize = 15.sp,
//        color = Color.White
//    ),
//    caption = TextStyle(
//        fontFamily = QuickSand,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    ),
//    overline = TextStyle(
//        fontFamily = QuickSand,
//        fontWeight = FontWeight.W400,
//        fontSize = 12.sp
//    )
)