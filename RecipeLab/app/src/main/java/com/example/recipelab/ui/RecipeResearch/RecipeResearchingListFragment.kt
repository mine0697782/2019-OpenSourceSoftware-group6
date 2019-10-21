package com.example.recipelab.ui.RecipeResearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.RecipeResearchingListAdapter
import com.example.recipelab.model.Research
import io.realm.Realm

class RecipeResearchingListFragment : Fragment() {
    lateinit var adapter: RecipeResearchingListAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var realm: Realm

    var data = arrayListOf(
        Research(1),
        Research(2),
        Research(3),
        Research(4),
        Research(5),
        Research(6),
        Research(7),
        Research(8)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.content_recipe_research_list,container,false)

        adapter = RecipeResearchingListAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}