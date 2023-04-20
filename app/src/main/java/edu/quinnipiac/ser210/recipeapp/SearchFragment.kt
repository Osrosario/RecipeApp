package edu.quinnipiac.ser210.recipeapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.CollapsibleActionView
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isInvisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/18/2023
 *
 * Fragment class responsible for creating a recycler view. If a RecipeInterface does not exist,
 * a error message will be displayed, otherwise, creates an RecipeInterface object and extracts a list
 * of recipes and sends it to the RecipeAdapter.kt to display information.
 */

class SearchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecipeAdapter
    lateinit var searchTerm: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val searchTerm = arguments?.getString("searchTerm")
        this.searchTerm = searchTerm.toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerAdapter = RecipeAdapter(requireContext(), Navigation.findNavController(view))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter

        view.setBackgroundColor(Color.WHITE)
        searchRecipes()
    }

    private fun searchRecipes() {
        val apiInterface = RecipeInterface.create().searchRecipes(searchTerm)
        view?.clearFocus()
        apiInterface?.enqueue(object : Callback<SearchResult?> {
            override fun onResponse(
                call: Call<SearchResult?>,
                response: Response<SearchResult?>
            ) {
                if (response.body() != null) {
                    recyclerAdapter.clearItems()
                    recyclerAdapter.setHerosListItems(response.body()!! as SearchResult)
                }
            }

            override fun onFailure(call: Call<SearchResult?>, t: Throwable) {
                if (t != null) {
                    t.message?.let { Log.d("onFailure", it) }
                }
            }
        })
    }
}

