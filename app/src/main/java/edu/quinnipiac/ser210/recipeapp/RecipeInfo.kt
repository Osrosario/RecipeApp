package edu.quinnipiac.ser210.recipeapp

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeInfo data class sets the data that is retrieved from the API service.
 */

data class RecipeInfo (
    val id: Int?,
    val title: String?,
    val image: String?,
    val readyInMinutes: Int?
)