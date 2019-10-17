package com.example.recipelab.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.model.Research
import com.example.recipelab.model.ResearchTamplet
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.RealmList
import kotlinx.android.synthetic.main.nav_main.*

class RecipeResearchingListActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    lateinit var navView: NavigationView
    lateinit var recyclerView: RecyclerView
    lateinit var realm: Realm

    lateinit var data: RealmList<Research>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_research_list)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        realm = Realm.getDefaultInstance()

        navView = nav_view
        navView.setNavigationItemSelectedListener(this@RecipeResearchingListActivity)

//        val bundle = intent.extras
//        val itemId = realm.where(ResearchTamplet::class.java).equalTo("id",bundle?.getLong("key")).findFirst()
//        val recipe =
//        data = RealmList()
//        data = recipe!!.researches
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
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
