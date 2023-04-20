package edu.quinnipiac.ser210.recipeapp

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/18/2023
 *
 * A data class to assign data retrieved from the API service.
 */

data class RecipeInfo (
    val id: Int?,
    val title: String?,
    val image: String?,
    val readyInMinutes: Int?
)