package com.example.recipelab.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

// 각 레시피의 연구 목록
open class Research(
    @PrimaryKey var id: Long = 0,
    // 어떤 레시피의 연구인지 찾기 위한 레시피번호
    open var recipeNum: Long = 0,
    open var date: String = "",
    open var bean: String = "",
    open var water: String = "",
    open var time: String = "",
    open var score: String = ""
) : RealmObject() {

    // 디버그 로그로 출력하기 위해 String 타입으로 반환하는 메서드
    override fun toString(): String {
        return "id:"+id.toString()+" recipeNo:"+recipeNum.toString()+" date:"+date+" bean:"+bean+" water:"+water+" time:"+time+" score:"+score
    }
}