package edu.quinnipiac.ser210.recipeapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeInterface class establishes the connection to the API service.
 */

interface RecipeInterface
{
    @Headers(
        "x-rapidapi-key: f729c3707fmsh309903d877de6e7p1c7c7djsn0b68a17e6861",
        "x-rapidapi-host: recipe-by-api-ninjas.p.rapidapi.com"
    )

    @GET("/v1/recipe")
    fun searchRecipes(@Query("query") query: String): Call<ArrayList<RecipeInfo?>?>?

    companion object
    {
        fun create(): RecipeInterface
        {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://recipe-by-api-ninjas.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(RecipeInterface::class.java)
        }
    }
}