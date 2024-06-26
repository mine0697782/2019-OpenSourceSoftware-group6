package com.example.recipelab.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.model.Research
import kotlinx.android.synthetic.main.item_recipe_elements.view.*

class RecipeResearchingListAdapter(var data: ArrayList<Research>) :
    RecyclerView.Adapter<RecipeResearchingListAdapter.ViewHolder>() {
    // 레시피의 상세 페이지로 들어갔을때 연구 목록을 보여주는 어댑터

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate(R.layout.~~) 부분에서 레이아웃 외에는 건드릴 부분이 없음,
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_elements,parent,false)

        return ViewHolder(view)
    }

    // 기본값. 건드릴부분이 없음.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val listener = View.OnClickListener {
            Toast.makeText(it.context,"clicked ${item}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(listener: View.OnClickListener, item: Research) {
            // 이 부분에서 컴포넌트와 데이터를 연결해주면 됨.
            view.text_elements_1.text = item.date
            view.text_elements_2.text = item.bean
            view.text_elements_3.text = item.water
            view.text_elements_4.text = item.time
            view.text_elements_5.text = item.score
        }
    }
}