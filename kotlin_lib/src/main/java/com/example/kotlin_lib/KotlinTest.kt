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
    val arrays02 = Array(5, { i -> i*i })
    for (i in 0..10) {
        arrays[i] = i
    }
    println("数组arrays的长度是${arrays.size}")
    //遍历数组
    for(i in arrays02){
        print("$i")
    }

    //List 在 Kotlin 中是不可变的，创建后就不允许作任何修改操作。
    //创建一个空数组
    emptyList<Int>()


}