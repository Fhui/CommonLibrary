package com.example.kotlin_lib

import android.os.Parcel
import android.os.Parcelable

/**
 *  Interface
 * Created by ShineF on 2017/7/31 0031.
 */
interface IPerson {

    fun eat() : Unit

}

class Human : Parent(), IPerson {

    override fun eat() {
        println("eatting...")
    }

    override fun action() {
        super.action()
        println("action....")
    }

}