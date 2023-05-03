package edu.quinnipiac.ser210.recipeapp

import android.R
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import edu.quinnipiac.ser210.recipeapp.databinding.FragmentRecipeBinding

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
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}