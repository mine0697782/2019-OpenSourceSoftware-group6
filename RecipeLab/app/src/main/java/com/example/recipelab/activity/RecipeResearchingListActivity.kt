package com.example.recipelab.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.RecipeResearchingListAdapter
import com.example.recipelab.model.Research
import com.example.recipelab.model.ResearchTamplet
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.content_add_research_tamplet.*
import kotlinx.android.synthetic.main.content_recipe_research_list.*
import kotlinx.android.synthetic.main.item_research_list.*
import kotlinx.android.synthetic.main.nav_main.*

class RecipeResearchingListActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var navView: NavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var realm: Realm
    lateinit var adapter: RecipeResearchingListAdapter

    lateinit var textName: TextView
    lateinit var textDate: TextView
    lateinit var textTag: TextView

    var data: ArrayList<Research> = arrayListOf()

    var key: Long? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_research_list)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = fab_add_research
        fab.setOnClickListener {
            startActivity(Intent(this,AddResearchActivity::class.java).putExtra("key",key))
        }

        adapter = RecipeResearchingListAdapter(data)
        recyclerView = rv_list_researching_elements
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL)
        )

        Realm.init(this)

        realm = Realm.getDefaultInstance()

        navView = nav_view
        navView.setNavigationItemSelectedListener(this@RecipeResearchingListActivity)

        textName = text_research_coffee_name
        textDate = text_research_coffee_date
        textTag = text_research_coffee_tag1

        realm.beginTransaction()

        val bundle = intent.extras
        key = bundle?.getLong("key")
        val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()

        textName.text = item?.menu
        textDate.text = item?.date

        adapter.data.addAll(realm.where(Research::class.java).equalTo("recipeNum",key).findAll())
        adapter.notifyDataSetChanged()

        realm.commitTransaction()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.researching, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_finish -> {
                realm.beginTransaction()
                val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()
                item?.finished = true
                realm.commitTransaction()

                finish()
            }

            R.id.action_delete -> {
                realm.beginTransaction()
                val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()
                item?.deleteFromRealm()
                realm.commitTransaction()

                finish()
            }

            R.id.action_refresh -> {
                realm.beginTransaction()
                adapter.data.clear()
                adapter.data.addAll(realm.where(Research::class.java).equalTo("recipeNum",key).findAll())
                adapter.notifyDataSetChanged()
                realm.commitTransaction()
                Toast.makeText(this,"sibal",Toast.LENGTH_SHORT).show()
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
}
