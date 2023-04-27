package edu.quinnipiac.ser210.recipeapp

import android.view.View
import android.widget.RadioButton
import androidx.databinding.BindingAdapter
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
    /** Initial variables replicating the columns of the Recipe data class */
    var newRecipeName = ""
    var newImageUrl = ""
    var newPrepTime = ""
    var newCookTime = ""
    var newIngredientList = ArrayList<String>(8)
    var newInstructionList = ArrayList<String>(8)

    fun addIngredient(ingredient: String) = newIngredientList.add(ingredient)
    fun addInstruction(instruction: String) = newInstructionList.add(instruction)

    fun addRecipe(): Unit
    {
        viewModelScope.launch {
            /** Create a new recipe from the Recipe. */
            val recipe = Recipe()

            /** Assign local variables to the data of the recipe. */
            recipe.recipeTitle = newRecipeName
            recipe.imageUrl = newImageUrl
            recipe.prepTime = newPrepTime
            recipe.cookTime = newCookTime
            recipe.totalTime = { newPrepTime.toInt() + newCookTime.toInt() }.toString()
            recipe.ingredients = newIngredientList.joinToString(separator = ".")
            recipe.instructions = newInstructionList.joinToString(separator = ".")

            /** Add new recipe to the database. */
            dao.insertRecipe(recipe)
        }
    }
}