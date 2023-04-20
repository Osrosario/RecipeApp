package edu.quinnipiac.ser210.recipeapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RecipeInterface {

    @Headers(
        "x-rapidapi-key: f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861",
        "x-rapidapi-host: spoonacular-recipe-food-nutrition-v1.p.rapidapi.com"
    )

    @GET("recipes/complexSearch")
    fun searchRecipes(@Query("query") query: String): Call<SearchResult?>?

     /*
    @GET("recipes/{id}/information")
    fun getRecipeDetails(
        @Path("id") id: String?,
    ): Call<Recipe?>?
     */

    companion object
    {
        fun create(): RecipeInterface
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RecipeInterface::class.java)
        }
    }
}