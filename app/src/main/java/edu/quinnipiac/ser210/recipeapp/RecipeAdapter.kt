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

var recipeList : ArrayList<RecipeInfo> = ArrayList()

class RecipeAdapter(val context: Context,  var navController: NavController) : RecyclerView.Adapter<RecipeAdapter.MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_info_item,parent,false)
        return MyViewHolder(view, context)
    }

    override fun getItemCount(): Int
    {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        holder.bind(position)
    }

    fun setRecipeListItems(recipeListparam: ArrayList<RecipeInfo>)
    {
        recipeList = recipeListparam
        notifyDataSetChanged()
    }

    override fun onViewRecycled(holder: MyViewHolder) 
    {
        super.onViewRecycled(holder)
        holder.itemView.setOnClickListener(null)
        recipeList.clear()
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView)
    {
        super.onDetachedFromRecyclerView(recyclerView)
        recipeList.clear()
        notifyDataSetChanged()
    }

    fun removeRecipeListItems() 
    {
        recipeList.clear()
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
                val menuSearch = toolbar.menu.findItem(R.id.searchRecipe)
                menuSearch.collapseActionView()
                menuSearch.isVisible = !menuSearch.isVisible

                val bundle = Bundle().apply {
                    putString("title", recipeList.get(pos).title)
                    putString("servings", recipeList.get(pos).servings)
                    putString("ingredients", recipeList.get(pos).ingredients)
                    putString("instructions", recipeList.get(pos).instructions)
                }

                val action = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(bundle)
                navController.navigate(action)
            }
        }

        fun bind(position: Int)
        {
            pos = position
            title
            val currRecipe = recipeList.get(position)
            title.text = currRecipe.title
        }
    }
}