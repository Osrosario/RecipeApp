package edu.quinnipiac.ser210.recipeapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The Recipe Data Access Object creates methods that accesses the data
 * provided by the Recipe Data Class. This DAO inserts a new recipe into
 * the database and updates the database.
 */

@Dao
interface RecipeDAO
{
    @Insert
    suspend fun insertRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipeTable WHERE recipeId = :recipeId")
    fun get(recipeId: Long): LiveData<Recipe>

}