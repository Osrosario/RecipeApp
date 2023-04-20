package edu.quinnipiac.ser210.recipeapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RecipeDAO
{
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Update
    suspend fun updateTitle(newTitle: String)

    @Update
    suspend fun updatePrepTime(newTime: String)

    @Update
    suspend fun updateCookTime(newTime: String)

    @Update
    suspend fun updateTotalTime(newTime:String)

    @Update
    suspend fun updateIngredients(newList: ArrayList<String>)

    @Update
    suspend fun updateInstruction(newList: ArrayList<String>)

    @Query("SELECT * FROM recipeTable WHERE recipeId = :recipeId")
    fun get(recipeId: Long): LiveData<Recipe>
}