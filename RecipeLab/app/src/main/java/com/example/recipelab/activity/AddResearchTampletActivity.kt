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
    // 탬플릿을 추가하는 액티비티

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

        // 탬플릿에 재료를 추가하는 버튼, 구현에 실패함
//        btnAdd.setOnClickListener {
//            adapter.data.add(adapter.data.size+1)
//            adapter.notifyDataSetChanged()
//            Toast.makeText(this,adapter.data.size,Toast.LENGTH_SHORT).show()
//        }

        // 확인버튼
        btnCommit.setOnClickListener {

            realm.beginTransaction()

            // 현재 시간을 받아와서 저장
            val time = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())/*date*/)+" 수정"

            // Realm 데이터베이스는 PrimaryKey가 자동으로 증가가 되지 않기 때문에
            // 데이터베이스에 새로운 데이터를 추가하기 위해 가장 마지막 ID를 찾아 새로 추가할 ID 값을 직접 설정한다.
            val currentId = realm.where(ResearchTamplet::class.java).max("id")
            val nextId = if (currentId == null) 1 else currentId.toLong()+1

            // 새롭게 데이터를 생성함 (이 시점에서 이미 DB에 추가가 되있음)
            val newObject = realm.createObject<ResearchTamplet>(nextId)

            // 초기화된 데이터에 저장할 정보를 직접 넣어줌
            newObject.menu = editTitle.text.toString()
            newObject.date = time
            newObject.comment = editComment.text.toString()

            // DB를 닫음으로서 저장하는 과정이 끝남
            realm.commitTransaction()

            Toast.makeText(this,newObject.toString(),Toast.LENGTH_SHORT).show()
            Log.d(TAG,newObject.toString())

            // 현재 액티비를 종료 (뒤로 나가기)
            finish()
        }

        recyclerView = recyclerView_tamplet_elements_list
        recyclerView.run {
            adapter = adapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
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
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }

}
