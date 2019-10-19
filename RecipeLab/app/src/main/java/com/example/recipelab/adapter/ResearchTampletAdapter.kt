package com.example.recipelab.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.activity.RecipeResearchingListActivity
import com.example.recipelab.model.ResearchTamplet
import kotlinx.android.synthetic.main.item_research_list.view.*

class ResearchTampletAdapter(val data: ArrayList<ResearchTamplet>, val context: Context, val layout: Int) :
    RecyclerView.Adapter<ResearchTampletAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchTampletAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ResearchTampletAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResearchTampletAdapter.ViewHolder, position: Int) {
        val item = data[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item}", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, RecipeResearchingListActivity::class.java).putExtra("key",item.id))
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
            view.setOnClickListener(listener)
            view.text_research_coffee_name.text = item.menu
            view.text_research_coffee_date.text = item.date
            view.text_research_coffee_tag1.text = "tag"
        }
    }

}