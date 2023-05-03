package edu.quinnipiac.ser210.recipeapp

import android.content.Context
import androidx.fragment.app.FragmentManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.CollapsibleActionView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isInvisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The SearchFragment class is responsible for creating a recycler view. If a RecipeInterface does not exist,
 * a error message will be displayed, otherwise, creates an RecipeInterface object and extracts a list
 * of recipes and sends it to the MyRecipesAdapter.kt to display information.
 */

class MyRecipesFragment : Fragment()
{
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: MyRecipesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_my_recipes, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recipes_recyclerview)
        recyclerAdapter = MyRecipesAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        GlobalScope.launch(Dispatchers.IO) {
            val allRecipes = getAllRecipesFromDatabase(requireContext())
            withContext(Dispatchers.Main) {
                allRecipes?.let {
                    recyclerAdapter.setRecipeListItems(ArrayList(it))
                }
            }
        }

    }

    suspend fun getAllRecipesFromDatabase(context: Context): List<Recipe>? {
        val recipeDao = RecipeDatabase.getDatabase(context).recipeDAO()
        return recipeDao.getAll()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        recyclerAdapter.removeRecipeListItems()
        recyclerView.adapter = recyclerAdapter
    }



}

