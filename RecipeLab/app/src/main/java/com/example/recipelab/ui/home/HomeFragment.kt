package com.example.recipelab.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.ResearchTampletAdapter
import com.example.recipelab.model.ResearchTamplet
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.ArrayList

class HomeFragment : Fragment() {

//    private lateinit var homeViewModel: HomeViewModel

    lateinit var recyclerViewResearching: RecyclerView
    lateinit var recyclerViewFinished: RecyclerView
    lateinit var adapter: ResearchTampletAdapter
    lateinit var realm: Realm

    val data: ArrayList<ResearchTamplet> = arrayListOf(
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val controller = findNavController()

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        adapter = ResearchTampletAdapter(data, controller)

        recyclerViewResearching = root.rv_list_researching
        recyclerViewResearching.adapter = adapter
        recyclerViewResearching.layoutManager = LinearLayoutManager(context)
        recyclerViewResearching.addItemDecoration(
            DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL)
        )
        recyclerViewFinished = root.rv_list_research_finished
        recyclerViewFinished.adapter = adapter
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