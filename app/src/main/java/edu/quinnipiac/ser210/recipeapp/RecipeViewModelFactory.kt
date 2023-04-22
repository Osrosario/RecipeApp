package edu.quinnipiac.ser210.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeViewModelFactory creates an instance of the RecipeViewModel (if it doesn't already exist)
 * and enables the RecipeViewModel to have parameters passed into it.
 */

class RecipeViewModelFactory(private val dao: RecipeDAO) : ViewModelProvider.Factory
{
    override fun <T: ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java))
        {
            return RecipeViewModel(dao) as T
        }

        throw IllegalAccessException("Unknown ViewModel")
    }
}