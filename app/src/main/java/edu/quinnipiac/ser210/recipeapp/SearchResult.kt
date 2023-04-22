package edu.quinnipiac.ser210.recipeapp

import com.google.gson.annotations.SerializedName

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The SearchResult data class sets the data that is retrieved from the API service search function.
 */

data class SearchResult (
    @SerializedName("results")
    val results: ArrayList<RecipeInfo>
)
