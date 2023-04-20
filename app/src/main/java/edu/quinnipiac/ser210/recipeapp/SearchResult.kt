package edu.quinnipiac.ser210.recipeapp

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("results")
    val results: ArrayList<RecipeInfo>
)
