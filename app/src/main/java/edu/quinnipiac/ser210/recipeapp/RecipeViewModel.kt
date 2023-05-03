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
    var newServings = ""
    var newIngredientList = ArrayList<String>(8)
    var newInstructionList = ArrayList<String>(8)

    fun addIngredient(ingredient: String) = newIngredientList.add(ingredient)
    fun addInstruction(instruction: String) = newInstructionList.add(instruction)

    fun addRecipe(): Unit
    {
        viewModelScope.launch {
            /** Create a new recipe from the Recipe. */
            val newRecipe = Recipe(
                title = newRecipeName,
                servings = newServings,
                ingredients = newIngredientList.toString(),
                instructions = newInstructionList.toString()
            )
            /** Assign local variables to the data of the recipe. */
            newRecipe.title = newRecipeName
            newRecipe.servings = newServings
            newRecipe.ingredients = newIngredientList.joinToString(separator = "|").toString()
            newRecipe.instructions = newInstructionList.joinToString(separator = ".")

            /** Add new recipe to the database. */
            dao.insert(newRecipe)
        }
    }
}
