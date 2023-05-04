package edu.quinnipiac.ser210.recipeapp

import android.util.Log
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
    var newIngredientList = ArrayList<String>(1)
    var newInstructionList = ArrayList<String>(1)
    var newIngredients = ""
    var newInstructions = ""
    var newIng1 = ""
    var newIng2 = ""
    var newIng3 = ""
    var newIng4 = ""
    var newIng5 = ""
    var newIng6 = ""
    var newIng7 = ""
    var newIng8 = ""
    var newIns1 = ""
    var newIns2 = ""
    var newIns3 = ""
    var newIns4 = ""
    var newIns5 = ""
    var newIns6 = ""
    var newIns7 = ""
    var newIns8 = ""

    fun addIngredient(ingredient: String) {
        if (ingredient != "") {
            newIngredientList.add(ingredient)
        }
    }
    fun addInstruction(instruction: String) {
        if (instruction != "") {
            newInstructionList.add(instruction)
        }
    }

    fun addRecipe(): Unit
    {
        viewModelScope.launch {
            /** Create a new recipe from the Recipe. */
            val newRecipe = Recipe(
                title = newRecipeName,
                servings = newServings,
                ingredients = newIngredients,
                instructions = newInstructions
            )
            /** Assign local variables to the data of the recipe. */
            addIngredient(newIng1)
            addIngredient(newIng2)
            addIngredient(newIng3)
            addIngredient(newIng4)
            addIngredient(newIng5)
            addIngredient(newIng6)
            addIngredient(newIng7)
            addIngredient(newIng8)
            addInstruction(newIns1)
            addInstruction(newIns2)
            addInstruction(newIns3)
            addInstruction(newIns4)
            addInstruction(newIns5)
            addInstruction(newIns6)
            addInstruction(newIns7)
            addInstruction(newIns8)
            newIngredients = newIngredientList.joinToString("|")
            //Log.d("Ingredients", newIngredientList[0])
            newInstructions = newInstructionList.joinToString(".")
            newRecipe.title = newRecipeName
            newRecipe.servings = "$newServings Servings"
            newRecipe.ingredients = newIngredients
            newRecipe.instructions = newInstructions

            /** Add new recipe to the database. */
            dao.insert(newRecipe)
        }
    }
}
