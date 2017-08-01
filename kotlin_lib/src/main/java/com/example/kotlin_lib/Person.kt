package com.example.kotlin_lib

/**
 *  Person类
 * Created by ShineF on 2017/7/24 0024.
 */

abstract class Person(var name: String) {
    abstract fun eat() : Unit
    abstract fun sleep() : Unit
    abstract  fun pee() : Unit
}


@Suppress("UNREACHABLE_CODE")
class Man(name: String) : Person(name) {
    override fun pee() {
        println("$name is peeing...")
    }

    override fun sleep() {
        println("$name is sleeping...")
    }

    override fun eat() {
        println("$name is eatting...")
    }

}