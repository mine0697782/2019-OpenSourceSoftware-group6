package com.example.recipelab.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipelab.R
import com.example.recipelab.adapter.AddTampletElementsAdapter
import com.example.recipelab.model.ResearchTamplet
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.content_add_research_tamplet.*
import kotlinx.android.synthetic.main.nav_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddResearchTampletActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "AddResearchTamplet"

    lateinit var navView: NavigationView

    lateinit var editTitle: EditText
    lateinit var editTag: EditText
    lateinit var editComment: EditText

    lateinit var btnCommit: Button
    lateinit var btnAdd: TextView

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: AddTampletElementsAdapter

    var data: ArrayList<Int> = arrayListOf()

    val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_research_tamplet)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        navView = nav_view
        navView.setNavigationItemSelectedListener(this@AddResearchTampletActivity)

        editTitle = editText_add_tamplet_title
        editTag = editText_add_tamplet_tag
        editComment = editText_add_tamplet_comment

        btnCommit = btn_recipe_add_research_tamplet
        btnAdd = text_btn_add_elements


        adapter = AddTampletElementsAdapter(this, data)

        btnAdd.setOnClickListener {
            adapter.data.add(adapter.data.size+1)
            adapter.notifyDataSetChanged()
//            Toast.makeText(this,adapter.data.size,Toast.LENGTH_SHORT).show()
        }

        btnCommit.setOnClickListener {

            realm.beginTransaction()

            val time = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())/*date*/)+" 수정"

            val currentId = realm.where<ResearchTamplet>(ResearchTamplet::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toLong()+1

            val newObject = realm.createObject<ResearchTamplet>(nextId)

            newObject.menu = editTitle.text.toString()
//            newObject.menu = nextId.toString()+"번"
//            newObject.tag = editTag.text.toString()
            newObject.date = time
            newObject.comment = editComment.text.toString()

            realm.commitTransaction()

            Toast.makeText(this,newObject.toString(),Toast.LENGTH_SHORT).show()
            Log.d(TAG,newObject.toString())

            finish()
        }

        recyclerView = recyclerView_tamplet_elements_list
        recyclerView.run {
            adapter = adapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(applicationContext,
                    DividerItemDecoration.HORIZONTAL)
            )
        }

    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        Toast.makeText(this,p0.toString(),Toast.LENGTH_SHORT).show()
        Log.d(TAG, "nav Item Selected : "+p0.toString())
        return true
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

}
