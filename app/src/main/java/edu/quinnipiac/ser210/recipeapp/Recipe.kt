package edu.quinnipiac.ser210.recipeapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The Recipe Data class creates a database consisting of a table with each column initializing a default variable by
 * name and type. This data class is used to create and store the data of a recipe.
 */

@Entity(tableName = "recipeTable")
data class Recipe (
    @PrimaryKey(autoGenerate = true)
    var recipeId: Long = 0L,

    @ColumnInfo(name = "title")
    var recipeTitle: String = "",

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String = "",

    @ColumnInfo(name = "prepTime")
    var prepTime: String = "",

    @ColumnInfo(name = "cookTime")
    var cookTime: String = "",

    @ColumnInfo(name = "totalTime")
    var totalTime: String = "",

    @ColumnInfo(name = "ingredients")
    var ingredients: String = "",

    @ColumnInfo(name = "instructions")
    var instructions: String = ""
)