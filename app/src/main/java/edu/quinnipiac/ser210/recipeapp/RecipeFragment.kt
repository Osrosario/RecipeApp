package edu.quinnipiac.ser210.recipeapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope

import com.google.android.material.snackbar.Snackbar
import edu.quinnipiac.ser210.recipeapp.databinding.FragmentRecipeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Michael Ruocco, Omar Rosario
 * @date
 */

class RecipeFragment : Fragment()
{
    /**
     * Initial declaration
     **/
    lateinit var args: RecipeFragmentArgs
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding !!
    private var id: Long = 0
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        args = RecipeFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        /**
         * Declare view binding.
         **/
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.WHITE)

        val ingredientList: MutableList<String> = args.myBundle.getString("ingredients")?.split("|")?.toMutableList() ?: mutableListOf()
        val instructionList: MutableList<String> = args.myBundle.getString("instructions")?.split(".")?.toMutableList() ?: mutableListOf()

        binding.title.text = args.myBundle.getString("title")
        binding.servings.text = args.myBundle.getString("servings")

        val ingredientsLayout = binding.ingredientsList
        val instructionsLayout = binding.instructionsList

        for (ingredient in ingredientList) {
            val textView = TextView(requireContext())
            textView.text = ingredient.trim()
            textView.layoutParams = GridLayout.LayoutParams().apply {
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }
            ingredientsLayout.addView(textView)
        }

        for (instruction in instructionList) {
            val textView = TextView(requireContext())
            textView.text = instruction.trim()
            textView.layoutParams = GridLayout.LayoutParams().apply {
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
            }
            instructionsLayout.addView(textView)
        }

        binding.addButton.isVisible = args.myBundle.getInt("flag") != 2
        binding.addButton.setOnClickListener {
            val recipeName = args.myBundle.getString("title").toString()
            val recipeServings = args.myBundle.getString("servings").toString()
            val recipeIngredients = args.myBundle.getString("ingredients").toString()
            val recipeInstructions = args.myBundle.getString("instructions").toString()

            val newRecipe = Recipe(
                title = recipeName,
                servings = recipeServings,
                ingredients = recipeIngredients,
                instructions = recipeInstructions
            )

            GlobalScope.launch(Dispatchers.IO) {
                addRecipeToDatabase(newRecipe, requireContext())
            }
            Snackbar.make(requireView(), "Recipe added", Snackbar.LENGTH_SHORT).show()

            GlobalScope.launch(Dispatchers.IO) {
                val recipe = getRecipeFromDatabase( recipeName, requireContext())
                recipe?.let {
                    // Do something with the recipe, such as display it in a TextView
                    Log.d("Recipe", "Title: ${it.title}, Servings: ${it.servings}, Ingredients: ${it.ingredients}, Instructions: ${it.instructions}")
                }
            }

        }
    }

    suspend fun addRecipeToDatabase(recipe: Recipe, context: Context) {
        val recipeDao = RecipeDatabase.getDatabase(context).recipeDAO()
        recipeDao.insert(recipe)
    }

    suspend fun getRecipeFromDatabase(recipeTitle: String, context: Context): Recipe? {
        val recipeDao = RecipeDatabase.getDatabase(context).recipeDAO()
        return recipeDao.getRecipeByTitle(recipeTitle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
