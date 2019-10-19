package com.example.recipelab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import kotlinx.android.synthetic.main.item_tamplet_add_element.view.*


//////////////////////////// 쓰이지 않는 어댑터 ///////////////////////////////
class AddTampletElementsAdapter(val context: Context, var data: ArrayList<Int>) :
    RecyclerView.Adapter<AddTampletElementsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tamplet_add_element,parent,false)

        return AddTampletElementsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listener = View.OnClickListener {
            delete(position)
        }
        holder.apply {
            bind(position, listener)
            itemView.tag = position
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun delete(pos: Int) {
        data.remove(pos)
        this.notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(pos: Int, listener: View.OnClickListener) {
            view.btn_tample_element_close.setOnClickListener(listener)
            view.tag = pos
        }
    }
}