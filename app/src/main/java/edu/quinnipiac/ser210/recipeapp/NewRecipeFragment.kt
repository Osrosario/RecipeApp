package edu.quinnipiac.ser210.recipeapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import edu.quinnipiac.ser210.recipeapp.databinding.FragmentNewRecipeBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The NewRecipeFragment class creates its views and initializes all necessary properties and capabilities, such as enabling
 * view binding, building a instance of the Recipe Data Access Object, retrieving the Recipe View Model, and
 * enabling data binding.
 */

class NewRecipeFragment : Fragment()
{
    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        /** Builds the database, RecipeDAO, (if it doesn't already exist) and get a reference to the RecipeDAO property */
        val application = requireNotNull(this.activity).application
        val dao = RecipeDatabase.getDatabase(application).recipeDAO()

        /** Retrieves View Model */
        val viewModelFactory = RecipeViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeViewModel::class.java)

        /** Enables data binding to access the View Model's properties and methods in the XML file */
        binding.viewModel = viewModel

        binding.addButton.setOnClickListener {
            Snackbar.make(requireView(), "Recipe added", Snackbar.LENGTH_SHORT).show()
        }

        return view
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) 
    {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.WHITE)
    }

    /** Sets the _binding to null to prevent the fragment from trying to use view binding object */
    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}