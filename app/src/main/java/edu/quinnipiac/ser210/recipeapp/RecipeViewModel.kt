package edu.quinnipiac.ser210.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeViewModel class handles the logic associated with the NewRecipeFragment class. The class' methods
 * are invoked by view and data binding.
 */

class RecipeViewModel(val dao: RecipeDAO) : ViewModel()
{
    var newRecipeName = ""
    var newImageUrl = ""
    var newPrepTime = ""
    var newCookTime = ""
    var newIngredients = Array(8) { ArrayList<String>(3) }.toCollection(ArrayList())
    var newInstructions = ArrayList<String>()

    fun addRecipe()
    {
        viewModelScope.launch {
            val recipe = Recipe()

            recipe.recipeTitle = newRecipeName
            recipe.imageUrl = newImageUrl
            recipe.prepTime = newPrepTime
            recipe.cookTime = newCookTime
            recipe.totalTime = { newPrepTime.toInt() + newCookTime.toInt() }.toString()
            recipe.ingredients = newIngredients
            recipe.instructions = newInstructions

            dao.insertRecipe(recipe)
        }
    }
}