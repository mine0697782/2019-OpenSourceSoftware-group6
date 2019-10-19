package com.example.recipelab.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Research(
    @PrimaryKey var id: Long = 0,
    open var recipeNum: Long = 0,
    open var date: String = "",
    open var bean: String = "",
    open var water: String = "",
    open var time: String = "",
    open var score: String = ""
//    open var bean: Float = 0f,
//    open var water: Float = 0f,
//    open var score: Float = 0f

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

    override fun toString(): String {
        return "id:"+id.toString()+" recipeNo:"+recipeNum.toString()+" date:"+date+" bean:"+bean+" water:"+water+" time:"+time+" score:"+score
    }
}