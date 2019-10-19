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
    // 연구 기록을 추가하는 액티비티

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

        // 레이아웃에서 각 요소를 찾아서 변수에 연결해줌
        editBean = editText_add_research_bean
        editWater = editText_add_research_water
        editScore = rating_score
        editTime = editText_add_research_time
        editComment = editText_add_research_comment

        // 이전과 마찬가지로 전 액티비티에서 정보를 전달받음
        val bundle = intent.extras
        key = bundle?.getLong("key")

        realm = Realm.getDefaultInstance()

        // 하단의 확인버튼
        btn = btn_add_research
        // 버튼이 클릭되는 이벤트 리스너
        btn.setOnClickListener {
            realm.beginTransaction()

            // DB에 새로운 데이터를 추가하기 위해 가장 마지막 ID값을 찾음
            val currentId = realm.where(Research::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toLong()+1

            val newObject = realm.createObject<Research>(nextId)

            // 입력창에 입력받은 데이터들을 하나하나 DB의 새로운 오브젝트에 넣어줌
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
