package edu.quinnipiac.ser210.recipeapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/18/2023
 *
 * MainActivity class that sets the functionalities throughout the entire application.
 */

class MainActivity : AppCompatActivity()
{
    private lateinit var searchItem: MenuItem
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Finds the Navigation Controller and sets the toolbar to navigate between fragments.
         **/
        val materialToolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(materialToolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<Toolbar>(R.id.materialToolbar).setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        // Find the search item in the menu and assign it to the searchItem property
        searchItem = menu.findItem(R.id.searchRecipe)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }


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
             * Changes the theme of the app to dark mode when the "Eye" icon is pressed.
             **/
            R.id.displayMode -> {
                val nightModeFlags = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                    Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
                AppCompatDelegate.setDefaultNightMode(nightModeFlags)
                recreate()

                return true
            }

            /**
             * Displays a pop-up describing how to use the app when the "?" is pressed.
             **/
            R.id.helpPopup -> {
                val popupView = layoutInflater.inflate(R.layout.popup_help, null)
                val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)
                popupWindow.showAtLocation(findViewById(R.id.materialToolbar), Gravity.CENTER, 0, 0)

                popupView.setOnTouchListener { _, _ ->
                    popupWindow.dismiss()
                    true
                }
            }

            /**
             * Enables the user to copy the article's source link to share or search on a browser.
             **/
            R.id.shareLink -> {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                val snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Link copied",
                    Snackbar.LENGTH_SHORT
                )

                snackbar.show()

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}