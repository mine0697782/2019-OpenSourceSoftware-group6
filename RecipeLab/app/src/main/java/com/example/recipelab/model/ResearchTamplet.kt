package com.example.recipelab.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ResearchTamplet(
    @PrimaryKey var id: Long = 0,
    open var menu: String? = "케냐 AA",//null,
    open var date: String? = "yyyy.mm.dd",
    open var tag: RealmList<String> = RealmList(),
//    open var researches: RealmList<Research> = RealmList(),
    open var finished: Boolean = false
) : RealmObject() {

    open fun toStringTag(): String? {
        return " "
//        return tag!![0] + "  " + tag!![1] + "  " + tag!![2] + "  " + tag!![3]
    }

    constructor(num: Long) : this() {
        id = num
    }

    override fun toString(): String {
        return "id:"+id.toString()+" menu:"+menu+" date:"+date+" tag:"+tag.toString()+" researches: "//+researches.toString()
    }
}
