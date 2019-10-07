package com.example.recipelab.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Research(
    @PrimaryKey var id: Long = 0,
    open var date: String? = null,
    open var bean: String? = null,
    open var water: String? = null,
    open var time: String? = null,
    open var score: String? = null
) : RealmObject() {

    constructor(num: String) : this() {
        id = num.toLong()
        date = num
        bean = num
        water = num
        time = num
        score = num
    }
}