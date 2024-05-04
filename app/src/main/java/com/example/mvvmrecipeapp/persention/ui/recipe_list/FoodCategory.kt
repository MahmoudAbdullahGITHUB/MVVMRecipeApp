package com.example.mvvmrecipeapp.persention.ui.recipe_list

import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.BEEF
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.CHICKEN
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.DESSERT
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.DONUT
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.MILK
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.PIZZA
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.SOUP
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.VEGAN
import com.example.mvvmrecipeapp.persention.ui.recipe_list.FoodCategory.VEGETARIAN

enum class FoodCategory(val value: String, val tabIndex: Int) {
    CHICKEN("Chicken", 0),
    BEEF("Beef", 1),
    SOUP("Soup", 2),
    DESSERT("Dessert", 3),
    VEGETARIAN("Vegetarian", 4),
    MILK("Milk", 5),
    VEGAN("Vegan", 6),
    PIZZA("Pizza", 7),
    DONUT("Donut", 8),
}

fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, MILK, VEGAN, PIZZA, DONUT)
}

// imp
fun getFoodCategory(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}