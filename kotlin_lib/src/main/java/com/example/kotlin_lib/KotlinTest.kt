@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.example.kotlin_lib


/**
 *  Kotlin Test
 * Created by ShineF on 2017/7/24 0024.
 */
fun main(args: Array<String>) {
    println("hello kotlin!")
    Person("王铁蛋").printName()
    //var 声明变量, val声明常量
    val quantity = 5
    val price: Double = 5.6
    val name: String = "大白菜"
    println("产品:" + name + "\n总计:" + (price * quantity))

    val x = 5
    val y = 10
    // in ? .. ?  在某某区间, boolean
    if (x in 1..y - 1) {
        println("$x 在1到${y - 1}的区间")
    }

    //声明一个长度为5的数组
    val arrays = arrayOfNulls<Int>(11)
    //声明一个空数组
    emptyArray<Int>()
    //Array声明闭包操作
    val arrays02 = Array(5, { i -> i * i })
    for (i in 0..10) {
        arrays[i] = i
    }
    println("数组arrays的长度是${arrays.size}")
    //遍历数组
    for (i in arrays02) {
        println("$i")
    }

    //List 在 Kotlin 中是不可变的，创建后就不允许作任何修改操作。
    //创建一个空数组
    emptyList<Int>()

    //流程表达式
    val a: Int = 10
    val b = if (a > 9) a else 0
    println("输出的结果是:$b ")
    val c = if (10 == b) "false" else b
    println("输出Any类型:$c")

    /**
     * mutableMapOf可变map
     * mapOf()不可变map
     */
    val muntableMap = mutableMapOf<String, String>()
    for (i in 0..10) {
        muntableMap.put("$i", "$i")
    }
    val map = mapOf("a" to 1, "B" to 2, "C" to 3, "D" to 4)
    println("muntableMap:${muntableMap.size} \n  map的长度是:${map.size}")
    //遍历map
    for ((k, v) in map) {
        println("$k ----> $v")
    }

    /**
     * 自定义一个类
     */
    class Counter {
        private val privateVal: Int = 10
        val publicVal: Int = 0
        var defaultVal: Int = 1

        fun test(): Unit {
            defaultVal = privateVal + publicVal
        }

        fun test02() = defaultVal
    }

    val counter = Counter()
    println("${counter.test()} --> ${counter.test02()}")

    class Student {

        private var name: String = ""

         private var age : Int = 10

        fun getName(): String = this.name

        fun getAge() : Int = this.age

        fun setName(name: String) {
            this.name = name
        }
    }

    val person = Student()
    person.setName("Hua")
    println("Student.getName ->  ${person.getName()}")

}

