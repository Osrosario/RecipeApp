package edu.quinnipiac.ser210.recipeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import edu.quinnipiac.ser210.recipeapp.databinding.FragmentNewRecipeBinding

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

        return view
    }
}
