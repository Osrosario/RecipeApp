package edu.quinnipiac.ser210.recipeapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeDatabase class creates an instance of the RecipeDatabase (if it doesn't already exist) and returns it so that other classes
 * can utilize the Recipe DAO.
 */

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase()
{
    abstract val recipeDAO: RecipeDAO

    companion object
    {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase
        {
            synchronized(this)
            {
                var instance = INSTANCE

                if (instance == null)
                {
                    instance = Room.databaseBuilder(context.applicationContext, RecipeDatabase::class.java, "recipe_dataBase").build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}