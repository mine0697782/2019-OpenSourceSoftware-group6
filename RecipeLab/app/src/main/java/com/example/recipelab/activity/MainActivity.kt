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

    private val TAG = "MainActivity"    // 디버그 로그를 띄울때 표시할 액티비티 이름

    // 컴포넌트들로 초기화할 변수 선언부
    lateinit var recyclerViewResearching: RecyclerView
    lateinit var recyclerViewFinished: RecyclerView
    lateinit var adapterTop: ResearchTampletAdapter
    lateinit var adapterBottom: ResearchTampletAdapter
    lateinit var realm: Realm
    lateinit var testButton: FloatingActionButton

    // 어댑터에 전송할 데이터 포맷을 미리 선언한 부분
    val dataTop: ArrayList<ResearchTamplet> = arrayListOf()
    val dataBot: ArrayList<ResearchTamplet> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

//z        this.window.statusBarColor = "#E3BE49".toInt()

        // 화면 우측 하단의 버튼을 찾고, 클릭 이벤트를 감지하도록 메서드 설정
        val floatingBtn: FloatingActionButton = fab
        floatingBtn.setOnClickListener {
            val intent = Intent(this, AddResearchTampletActivity::class.java)
            startActivity(intent)
        }

        // Realm 데이터베이스를 사용하기 위해 액티비티의 생성 직후 초기화
        Realm.init(this)

        // 레이아웃 상단 하단에 리스트에 각각 사용될 어댑터를을 초기화
        adapterTop = ResearchTampletAdapter(dataBot,this,R.layout.item_research_list_card)
        adapterBottom = ResearchTampletAdapter(dataTop,this, R.layout.item_research_list)

        // realm 데이터베이스를 사용하기 위해 객체(인스턴스)를 받는 코드
        realm = Realm.getDefaultInstance()

        // 데이터베이스에서 연구가 완료된 레시피와 진행중인 레시피를 각각 찾아 어댑터에 삽입하고 데이터가 변경됐다고 신호를 보내는 코드
        adapterTop.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", false).findAll())
        adapterTop.notifyDataSetChanged()
        adapterBottom.data.addAll(realm.where(ResearchTamplet::class.java).equalTo("finished", true).findAll())
        adapterBottom.notifyDataSetChanged()


        // 테스트용 데이터를 추가하는 임시 코드
//        testButton = fab_testdata
//        testButton.setOnClickListener {
//            val time = SimpleDateFormat("yyyy-MM-dd").format(Date(System.currentTimeMillis())/*date*/)+" 수정"
//
//            realm.beginTransaction()
//            val currentId = realm.where<ResearchTamplet>(ResearchTamplet::class.java).max("id")
//            val nextId = if (currentId == null) 1 else currentId.toLong()+1
//
//            val newObject = realm.createObject<ResearchTamplet>(nextId)
//
//            newObject.menu = nextId.toString()+"번"
//            newObject.date = time
//
//            realm.commitTransaction()
//
//            adapterTop.data.add(newObject)
//            adapterTop.notifyDataSetChanged()
//            Toast.makeText(this,newObject.toString()+" ",Toast.LENGTH_SHORT).show()
//            Log.d(TAG,newObject.toString())
//        }

        // 좌측에서 끌어서 사용하는 네비게이션 뷰를 초기화하는 부분
        val navView: NavigationView = findViewById(R.id.nav_view)
        // 네비게이션 메뉴의 아이템을 클릭했을때 반응하기 위한 이벤트 리스너 설정
        navView.setNavigationItemSelectedListener(this@MainActivity)


        // 상단의 레시피 목록이 레이아웃에 딱 맞아떨어지도록 탄력적으로 고정시켜주는 함수?
        val snapHelper = PagerSnapHelper()

        // 연구중인 레시피를 보여주는 recyclerView
        recyclerViewResearching = rv_list_researching
        // 미리 초기화한 어댑터를 집어넣어준다.
        recyclerViewResearching.adapter = adapterTop
        // 가로로 움직이도록 지정
        recyclerViewResearching.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        // 각 아이템 사이에 구분선을 추가
        recyclerViewResearching.addItemDecoration(
            DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL)
        )
        // 아이템이 레이아웃에 딱 맞아떨어지도록 설정
        snapHelper.attachToRecyclerView(recyclerViewResearching)


        // 연구가 완료된 레시피를 보여주는 하단의 recyclerView
        recyclerViewFinished = rv_list_research_finished
        // recyclerViewFinished.~~ 로 공통적으로 이어지는 코드의 앞부분을 생략하는 문법
        recyclerViewFinished.run {
            // recyclerViewFinished.adapter = adapterBottom
            adapter = adapterBottom
            // recyclerViewFinished.layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context)
            // recyclerViewFinished.addItemDecoration~~~
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    // 이 함수를 호출하면 상단 툴바 우측에 메뉴가 생긴다
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // 어떤 메뉴를 붙일건지 설정
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // 위 함수로 생성한 메뉴 아이템이 클릭되는 이벤트를 감지한다
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            // item.itemId 가 R.id.action_refresh 인 경우
            R.id.action_refresh -> {
                notifyToMain()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 이 함수 호출로 네비게이션 드로워가 생성됨
    override fun onSupportNavigateUp(): Boolean {
        return true
    }

    // 네비게이션 드로워의 아이템이 클릭되는 이벤트를 감지
    override fun onNavigationItemSelected(p0:MenuItem): Boolean {
        return true
    }

    // 현재 액티비티가 종료될 때 실행되는 함수
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    // 149번 줄 에서 실행되는 함수
    // 현재 어댑터의 데이터를 싹 비우고 데이터베이서에서 새로 꺼내는 함수
    // 다른 액티비티에서 데이터베이스에 데이터를 추가하고난 후 수동으로 갱신하기 위해 만듬
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
