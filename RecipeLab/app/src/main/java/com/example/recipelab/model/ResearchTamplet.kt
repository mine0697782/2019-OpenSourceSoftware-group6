package com.example.recipelab.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ResearchTamplet(
    @PrimaryKey var id: Long = 0,
    open var menu: String? = "케냐 AA",
    open var date: String? = "yyyy.MM.dd",
    open var tag: RealmList<String> = RealmList(),
    open var finished: Boolean = false,
    open var comment: String = ""
) : RealmObject() {

    constructor(num: Long) : this() {
        id = num
    }

    // 디버그 로그로 출력하기 위해 String 타입으로 반환하는 메서드
    override fun toString(): String {
        return "id:"+id.toString()+" menu:"+menu+" date:"+date+" tag:"+tag.toString()
    }
}
