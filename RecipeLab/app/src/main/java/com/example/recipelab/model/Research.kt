package com.example.recipelab.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Research(
    @PrimaryKey var id: Long = 0,
    open var recipeNum: Long = 0
//    open var
//    open var date: String? = "date",
//    open var bean: String? = "bean",
//    open var water: String? = "water",
//    open var time: String? = "time",
//    open var score: String? = "score"
) : RealmObject() {
//
//    constructor(num: String) : this() {
//        id = num.toLong()
//        date = num
//        bean = num
//        water = num
//        time = num
//        score = num
//    }
}