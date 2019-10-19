package com.example.recipelab.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.recipelab.R
import com.example.recipelab.model.Research
import com.example.recipelab.model.ResearchTamplet
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.content_add_research.*
import java.text.SimpleDateFormat
import java.util.*

class AddResearchActivity : AppCompatActivity() {

    private val TAG = "AddResearchAct"

    lateinit var realm: Realm

    var key: Long? = 0

    lateinit var btn: Button
    lateinit var editBean: EditText
    lateinit var editWater: EditText
    lateinit var editTime: EditText
    lateinit var editScore: RatingBar
    lateinit var editComment: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_research)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        editBean = editText_add_research_bean
        editWater = editText_add_research_water
        editScore = rating_score
        editTime = editText_add_research_time
        editComment = editText_add_research_comment

        val bundle = intent.extras
        key = bundle?.getLong("key")

        realm = Realm.getDefaultInstance()

        btn = btn_add_research
        btn.setOnClickListener {
            realm.beginTransaction()

            val currentId = realm.where(Research::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toLong()+1

            val newObject = realm.createObject<Research>(nextId)

            newObject.run {
                recipeNum = key!!
                date =  SimpleDateFormat("MM.dd").format(Date(System.currentTimeMillis()))
                bean = editBean.text.toString()
                water = editWater.text.toString()
                score = editScore.rating.toString()
                time = editTime.text.toString()
            }

            realm.commitTransaction()

            Toast.makeText(this,newObject.toString(),Toast.LENGTH_SHORT).show()
            Log.d(TAG,newObject.toString())

            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }



}
