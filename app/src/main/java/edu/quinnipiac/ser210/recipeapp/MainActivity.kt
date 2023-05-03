package edu.quinnipiac.ser210.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import edu.quinnipiac.ser210.recipeapp.databinding.ActivityMainBinding

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/18/2023
 *
 * The MainActivity class that sets the functionalities throughout the entire application.
 */

class MainActivity : AppCompatActivity()
{
    private lateinit var searchItem: MenuItem
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Finds the toolbar and sets the toolbar to be app's default toolbar. */

        val toolbar = binding.materialToolbar
        setSupportActionBar(toolbar)



        /**
         * Finds the Navigation Controller and sets the toolbar to navigate to the previous
         * fragment and drawer to navigate between fragments.
         */

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        binding.materialToolbar.setupWithNavController(navController)
        val drawer = binding.drawerLayout
        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(drawer)
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)

        /** Enable navigation when an item is clicked */
        val navView = binding.navView
        NavigationUI.setupWithNavController(navView, navController)

    }

    /** Inflate any menu items to the toolbar. */
    override fun onCreateOptionsMenu(menu: Menu): Boolean 
    {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        /** Find the search item in the menu and assign it to the searchItem property */
        searchItem = menu.findItem(R.id.searchRecipe)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
        {
            override fun onQueryTextSubmit(query: String?): Boolean
            {
                // create a bundle and pass the search term to SearchFragment
                val bundle = Bundle()
                bundle.putString("searchTerm", query)

                val searchFragment = SearchFragment()
                searchFragment.arguments = bundle

                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, searchFragment)
                    .commit()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean
            {
                return false
            }
        })

        return true
    }

    /** Navigates to a destination when an item is clicked. */
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {

            R.id.searchRecipe -> {
                if (searchItem.isActionViewExpanded) {
                    searchItem.collapseActionView()
                } else {
                    searchItem.expandActionView()
                }
                return true
            }

            /**
             * Enables the user to copy the article's source link to share or search on a browser.
             **/
/*
            R.id.add_recipe -> {

                val snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Recipe added",
                    Snackbar.LENGTH_SHORT
                )

                snackbar.show()

                return true
            }

 */

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}