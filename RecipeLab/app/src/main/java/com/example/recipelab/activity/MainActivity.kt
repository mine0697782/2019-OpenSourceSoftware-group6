package com.example.recipelab.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.ResearchTampletAdapter
import com.example.recipelab.model.ResearchTamplet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.app_bar_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "MainActivity"

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var recyclerViewResearching: RecyclerView
    lateinit var recyclerViewFinished: RecyclerView
    lateinit var adapterTop: ResearchTampletAdapter
    lateinit var adapterBottom: ResearchTampletAdapter
    lateinit var realm: Realm

    lateinit var testButton: FloatingActionButton

    val dataTop: ArrayList<ResearchTamplet> = arrayListOf(/*
        ResearchTamplet(1),
        ResearchTamplet(2),
        ResearchTamplet(3),
        ResearchTamplet(4),
        ResearchTamplet(5),
        ResearchTamplet(6),
        ResearchTamplet(7),
        ResearchTamplet(8),
        ResearchTamplet(9),
        ResearchTamplet(10)*/
    )

    val dataBot: ArrayList<ResearchTamplet> = arrayListOf(/*
        ResearchTamplet(1, "에티오피아 예거치프"),
        ResearchTamplet(2, "브라질 산토스"),
        ResearchTamplet(3, "케냐 AA"),
        ResearchTamplet(4, "사향고양이 똥"),
        ResearchTamplet(5, "강아지똥"),
        ResearchTamplet(6, "에티오피아 예거치프2"),
        ResearchTamplet(7, "에티오피아 예거치프"),
        ResearchTamplet(8, "에티오피아 예거치프"),
        ResearchTamplet(9, "에티오피아 예거치프"),
        ResearchTamplet(10, "에티오피아 예거치프")*/
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val floatingBtn: FloatingActionButton = fab
        floatingBtn.setOnClickListener {
            val intent = Intent(this, AddResearchTampletActivity::class.java)
            startActivity(intent)
        }
        Realm.init(this)

        adapterTop = ResearchTampletAdapter(dataBot,this,R.layout.item_research_list_card)
        adapterBottom = ResearchTampletAdapter(dataTop,this, R.layout.item_research_list)


        realm = Realm.getDefaultInstance()
        adapterTop.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", false).findAll())
        adapterTop.notifyDataSetChanged()
        adapterBottom.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", true).findAll())
        adapterBottom.notifyDataSetChanged()

        testButton = fab_testdata
        testButton.setOnClickListener {
//            val now = System.currentTimeMillis()
//            val date = Date(now)
//            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val time = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())/*date*/)+" 수정"

            realm.beginTransaction()
            val currentId = realm.where<ResearchTamplet>(ResearchTamplet::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toLong()+1

            val newObject = realm.createObject<ResearchTamplet>(nextId)

//            newObject.id = nextId
            newObject.menu = nextId.toString()+"번"
            newObject.date = time

            realm.commitTransaction()

            adapterTop.data.add(newObject)
            adapterTop.notifyDataSetChanged()
            Toast.makeText(this,newObject.toString()+" ",Toast.LENGTH_SHORT).show()
            Log.d(TAG,newObject.toString())
        }


//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this@MainActivity)

        val snapHelper = PagerSnapHelper()
        recyclerViewResearching = rv_list_researching
        recyclerViewResearching.adapter = adapterTop
        recyclerViewResearching.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewResearching.addItemDecoration(
            DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
        )
        snapHelper.attachToRecyclerView(recyclerViewResearching)


        recyclerViewFinished = rv_list_research_finished
        recyclerViewFinished.run {
            adapter = adapterBottom
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_refresh -> {
                notifyToMain()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    open fun notifyToMain(){
        realm.beginTransaction()

        adapterTop.data.clear()
        adapterTop.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", false).findAll())
        adapterTop.notifyDataSetChanged()
        adapterBottom.data.clear()
        adapterBottom.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", true).findAll())
        adapterBottom.notifyDataSetChanged()

        realm.commitTransaction()
    }
}
