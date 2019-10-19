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

    // 메인 액티비티에서 클릭된 아이템의 고유 번호로, 데이터베이스에서 정보를 찾기 위한 키 (Primary Key)
    var key: Long? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_research_list)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val fab = fab_add_research
        // 오른쪽 하단의 버튼 클릭 이벤트 리스너. 누르면 연구 기록 추가 페이지로 넘어가며, 현재 보고있는 레시피의 고유번호를 전달
        fab.setOnClickListener {
            startActivity(Intent(this,AddResearchActivity::class.java).putExtra("key",key))
        }

        // 어탭터 선언부. 연구 기록을 보여주는 어댑터
        adapter = RecipeResearchingListAdapter(data)
        recyclerView = rv_list_researching_elements
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        // 어댑터에 가로로 구분선 추가
        recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
        )

        Realm.init(this)

        realm = Realm.getDefaultInstance()

        navView = nav_view
        navView.setNavigationItemSelectedListener(this@RecipeResearchingListActivity)

        textName = text_research_coffee_name
        textDate = text_research_coffee_date
        textTag = text_research_coffee_tag1

        realm.beginTransaction()
        // 메인 액티비티에서 인텐트로 전달받은 정보를 받는 변수
        val bundle = intent.extras
        // 미리 선언해둔 변수에 전달받은 데이터를 찾아서 넣어준다
        key = bundle?.getLong("key")
        // 전달받은 고유번호(레시피 번호)로 레시피의 정보를 찾아온다 (제목, 날짜 등)
        val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()

        // 상단의 텍스트를 설정
        textName.text = item?.menu
        textDate.text = item?.date

        // 레시피 번호가 포함된 연구 기록을 찾아서 어댑터에 넣어준다
        adapter.data.addAll(realm.where(Research::class.java).equalTo("recipeNum",key).findAll())
        adapter.notifyDataSetChanged()

        realm.commitTransaction()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.researching, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_finish -> {
                // 데이터베이스에서 현재 보고있는 레시피를 찾아 연구 완료 여부를 참으로 바꿈
                realm.beginTransaction()
                val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()
                item?.finished = true
                realm.commitTransaction()

                finish()
            }

            R.id.action_delete -> {
                // 데이터베이스에서 현재 보고있는 레시피를 찾아 삭제함
                realm.beginTransaction()
                val item = realm.where(ResearchTamplet::class.java).equalTo("id",key).findFirst()
                item?.deleteFromRealm()
                realm.commitTransaction()

                finish()
            }

            R.id.action_refresh -> {
                // 어댑터의 데이터를 비우고 데이터베이스에서 다시 새로 꺼내 넣어줌 (갱신)
                realm.beginTransaction()
                adapter.data.clear()
                adapter.data.addAll(realm.where(Research::class.java).equalTo("recipeNum",key).findAll())
                adapter.notifyDataSetChanged()
                realm.commitTransaction()
                Toast.makeText(this,"refresh",Toast.LENGTH_SHORT).show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        return true
    }
}
