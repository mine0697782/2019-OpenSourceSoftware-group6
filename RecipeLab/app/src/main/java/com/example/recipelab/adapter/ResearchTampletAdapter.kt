package com.example.recipelab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.model.ResearchTamplet
import kotlinx.android.synthetic.main.item_research_list.view.*

class ResearchTampletAdapter(val data: ArrayList<ResearchTamplet>, val controller: NavController, val layout: Int) :
    RecyclerView.Adapter<ResearchTampletAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchTampletAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ResearchTampletAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResearchTampletAdapter.ViewHolder, position: Int) {
        val item = data[position]
        val listener = View.OnClickListener { it ->
            controller.navigate(R.id.action_nav_home_to_nav_recipe_researching_list)
            Toast.makeText(it.context, "Clicked: ${item}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(listener: View.OnClickListener, item: ResearchTamplet) {
            view.text_research_coffee_name.text = item.coffee
            view.text_research_coffee_date.text = item.date
            view.text_research_coffee_tag.text = "tag"
        }
    }

}