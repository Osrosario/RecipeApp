package edu.quinnipiac.ser210.recipeapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

/**
 * @title Make Meals
 * @author Michael Ruocco, Omar Rosario
 * @date 5/X/2023
 *
 * The RecipeAdapter class displays the title and image of a recipe obtained from theSearchFragment class.
 * Utilizes the "recipe_info_item.xml" and recycle view to organize views. If a material card is pressed,
 * it will get the id of that recipe and navigate to a fragment that displays details of the recipe based
 * upon that id.
 */

var myRecipesList : ArrayList<Recipe> = ArrayList()

class MyRecipesAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<MyRecipesAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_info_item,parent,false)
        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int
    {
        return myRecipesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.bind(position)
    }

    fun setRecipeListItems(myRecipesListparam: ArrayList<Recipe>)
    {
        myRecipesList = myRecipesListparam
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: MyViewHolder)
    {
        super.onViewRecycled(holder)
        holder.itemView.setOnClickListener(null)
        myRecipesList.clear()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView)
        myRecipesList.clear()
        notifyDataSetChanged()
    }

    fun removeRecipeListItems()
    {
        myRecipesList.clear()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView)
    {
        private val title: TextView = itemView!!.findViewById(R.id.item_title)
        private var pos:Int = 0

        init
        {
            itemView.setOnClickListener {

                val activity = itemView.context as AppCompatActivity
                val toolbar = activity.findViewById<MaterialToolbar>(R.id.materialToolbar)
                //val menuAdd = toolbar.menu.findItem(R.id.add_recipe)
                val menuSearch = toolbar.menu.findItem(R.id.searchRecipe)
                menuSearch.collapseActionView()
                menuSearch.isVisible = !menuSearch.isVisible
                //menuAdd.isVisible = !menuAdd.isVisible

                val bundle = Bundle().apply {
                    putString("title", myRecipesList.get(pos).title)
                    putString("servings", myRecipesList.get(pos).servings)
                    putString("ingredients", myRecipesList.get(pos).ingredients)
                    putString("instructions", myRecipesList.get(pos).instructions)
                }

                val action = MyRecipesFragmentDirections.actionMyRecipesFragmentToRecipeFragment(bundle)
                navController.navigate(action)
            }
        }

        fun bind(position: Int)
        {
            pos = position
            title
            val currRecipe = myRecipesList.get(position)
            title.text = currRecipe.title
        }
    }
}