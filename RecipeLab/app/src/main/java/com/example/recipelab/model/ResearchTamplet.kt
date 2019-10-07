package com.example.recipelab.model

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ResearchTamplet(
    @PrimaryKey var id: Long = 0,
    open var coffee: String? = "케냐 AA",//null,
    open var date: String? = "yyyy.mm.dd",
    open var tag: RealmList<String>? = null,
    open var researches: RealmList<Research>? = null
) : RealmObject() {

    open fun toStringTag(): String? {
        return " "
//        return tag!![0] + "  " + tag!![1] + "  " + tag!![2] + "  " + tag!![3]
    }

    constructor(num: Long) : this() {
        id = num
    }
}