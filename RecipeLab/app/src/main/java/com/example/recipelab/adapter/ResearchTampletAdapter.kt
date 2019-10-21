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
    // 메인화면에서 목록을 보여주는 어댑터

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResearchTampletAdapter.ViewHolder {
        // 데이터의 각 요소들을 넣어줄 레이아웃을 설정하는 부분. 대부분은 그대로 따라 쓰면 되는데, layout은 직접 넣어야한다.
        // 이 어댑터의 경우 초기화와 동시에 인자로 받아줬기 때문에 그대로 layout이라고 사용함.
        var view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ResearchTampletAdapter.ViewHolder(view)
    }

    // 딱히 손댈만한 부분은 없다. 거의 기본값이나 다름없는 코드
    override fun onBindViewHolder(holder: ResearchTampletAdapter.ViewHolder, position: Int) {
        val item = data[position]
        // 각 아이템에 클릭 이벤트 리스너를 설정하기 위해 미리 인스턴스화 해놓는 부분
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item}", Toast.LENGTH_SHORT).show()
            // 레시피를 클릭했을때 해당 레시피의 상세 내용을 보여주는 페이지로 이동
            context.startActivity(Intent(context, RecipeResearchingListActivity::class.java).putExtra("key",item.id))
        }
        holder.apply {
            // 레이아웃에 데이터를 넣어주는 함수
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