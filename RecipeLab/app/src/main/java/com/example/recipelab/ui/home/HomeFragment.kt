package com.example.recipelab.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.ResearchTampletAdapter
import com.example.recipelab.model.ResearchTamplet
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.ArrayList

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

    lateinit var recyclerViewResearching: RecyclerView
    lateinit var recyclerViewFinished: RecyclerView
    lateinit var adapterTop: ResearchTampletAdapter
    lateinit var adapterBottom: ResearchTampletAdapter
    lateinit var realm: Realm


    val data1: ArrayList<ResearchTamplet> = arrayListOf(
        ResearchTamplet(1),
        ResearchTamplet(2),
        ResearchTamplet(3),
        ResearchTamplet(4),
        ResearchTamplet(5),
        ResearchTamplet(6),
        ResearchTamplet(7),
        ResearchTamplet(8),
        ResearchTamplet(9),
        ResearchTamplet(10)
    )

    val data2: ArrayList<ResearchTamplet> = arrayListOf(
        ResearchTamplet(1, "에티오피아 예거치프"),
        ResearchTamplet(2, "브라질 산토스"),
        ResearchTamplet(3, "케냐 AA"),
        ResearchTamplet(4, "사향고양이 똥"),
        ResearchTamplet(5, "강아지똥"),
        ResearchTamplet(6, "에티오피아 예거치프2"),
        ResearchTamplet(7, "에티오피아 예거치프"),
        ResearchTamplet(8, "에티오피아 예거치프"),
        ResearchTamplet(9, "에티오피아 예거치프"),
        ResearchTamplet(10, "에티오피아 예거치프")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val controller = findNavController()

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        adapterTop = ResearchTampletAdapter(data2, controller, R.layout.item_research_list_card)

        val snapHelper = PagerSnapHelper()
        recyclerViewResearching = root.rv_list_researching
        recyclerViewResearching.adapter = adapterTop
        recyclerViewResearching.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewResearching.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        snapHelper.attachToRecyclerView(recyclerViewResearching)

        adapterBottom = ResearchTampletAdapter(data1, controller, R.layout.item_research_list)
        recyclerViewFinished = root.rv_list_research_finished
        recyclerViewFinished.adapter = adapterBottom
        recyclerViewFinished.layoutManager = LinearLayoutManager(context)
        recyclerViewFinished.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )

//        val textView: TextView = root.findViewById(R.id.text_home)

//        homeViewModel =
//            ViewModelProviders.of(this).get(HomeViewModel::class.java)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
}