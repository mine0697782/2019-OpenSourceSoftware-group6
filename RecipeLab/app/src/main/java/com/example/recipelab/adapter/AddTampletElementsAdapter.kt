package com.example.recipelab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R

class AddTampletElementsAdapter() :
    RecyclerView.Adapter<AddTampletElementsAdapter.ViewHolder>(){

//    var

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tamplet_add_element,parent,false)

        return AddTampletElementsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){

    }
}