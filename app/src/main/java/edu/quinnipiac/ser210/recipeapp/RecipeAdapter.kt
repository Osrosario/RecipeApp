package edu.quinnipiac.ser210.recipeapp

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.MaterialToolbar

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/18/2023
 *
 * A Kotlin class responsible for displaying the title and image of a recipe obtained from the
 * SearchFragment class. Utilizes the "recipe_info_item.xml" and recycle view to organize views. If a material
 * card is pressed, it will get the id of that recipe and navigate to a
 * fragment that displays details of the recipe based upon that id.
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

    fun setHerosListItems(recipeListparam: SearchResult)
    {
        recipeList.clear()
        recipeList.addAll(recipeListparam.results)
        notifyDataSetChanged()

        Log.d("RecipeAdapter", "setHerosListItems() called with ${recipeListparam.results.size} items")
    }

    inner class MyViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView)
    {
        private val title: TextView = itemView!!.findViewById(R.id.item_title)
        private val image: ImageView = itemView!!.findViewById(R.id.item_image)
        private var pos:Int = 0

        init
        {
            itemView.setOnClickListener {

                val action = SearchFragmentDirections.actionSearchFragmentToNewRecipeFragment(pos)
                navController.navigate(action)
                recipeList.clear()

                val activity = itemView.context as AppCompatActivity
                val toolbar = activity.findViewById<MaterialToolbar>(R.id.materialToolbar)
                val menuShare = toolbar.menu.findItem(R.id.shareLink)
                val menuSearch = toolbar.menu.findItem(R.id.searchRecipe)
                menuSearch.collapseActionView()
                menuSearch.isVisible = !menuSearch.isVisible
                menuShare.isVisible = !menuShare.isVisible

            }
        }

        fun bind(position: Int)
        {
            pos = position
            val currRecipe = recipeList.get(position)
            title.text = currRecipe.title

            Glide.with(context).load(currRecipe.image)
                .apply(RequestOptions().centerCrop())
                .into(image)
        }

    }

    fun clearItems() {
        recipeList.clear()
        notifyDataSetChanged()
    }
}