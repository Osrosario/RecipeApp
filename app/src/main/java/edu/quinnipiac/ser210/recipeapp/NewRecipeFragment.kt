package edu.quinnipiac.ser210.recipeapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.ser210.recipeapp.databinding.FragmentNewRecipeBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

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
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) 
    {
        super.onViewCreated(view, savedInstanceState)
        view.setBackgroundColor(Color.WHITE)
    }
}