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
import com.example.recipelab.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_add_research_tamplet.*
import kotlinx.android.synthetic.main.nav_main.*

class AddResearchTampletActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val TAG = "AddResearchTamplet"

    lateinit var navView: NavigationView

    lateinit var editTitle: EditText
    lateinit var editTag: EditText
    lateinit var editComment: EditText

    lateinit var btnCommit: Button
    lateinit var btnAdd: TextView

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

        btnAdd.setOnClickListener {
            Toast.makeText(this,"asd",Toast.LENGTH_SHORT).show()
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
