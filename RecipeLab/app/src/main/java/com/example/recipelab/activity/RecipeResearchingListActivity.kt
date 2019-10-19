package com.example.recipelab.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.model.Research
import com.example.recipelab.model.ResearchTamplet
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.content_recipe_research_list.*
import kotlinx.android.synthetic.main.item_research_list.*
import kotlinx.android.synthetic.main.nav_main.*

class RecipeResearchingListActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var navView: NavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var realm: Realm

    lateinit var textName: TextView
    lateinit var textDate: TextView
    lateinit var textTag: TextView

    lateinit var data: RealmList<Research>

    var key: Long? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_research_list)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = fab_add_research
        fab.setOnClickListener {

        }


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
//        textTag.text =

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
