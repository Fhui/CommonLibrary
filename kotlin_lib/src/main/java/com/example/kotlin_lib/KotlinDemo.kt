package com.example.kotlin_lib

import java.util.*


/**
 *  简书
 * Created by ShineF on 2017/7/27 0027.
 */

/**
 * -----关于数据类型-----
 * Byte 存储值范围 整数-128~127
 * Short 存储值范围 整数-32768~32767
 * Int 存储值范围 整数-2147483648~2147483647
 * Long 存储值范围 整数-9223372036854775807 ~ 9223372036854775807
 * Float 存储值范围 小数,小数点可以精确到6位
 * Double 存储值范围 小数,小数点可以精确到15-16位
 * String 存储值范围 字符串,用"'双引号引起来的字符串都可以存
 *
 * -----关于声明变量||常量-----
 * var 声明变量
 * val 声明常量
 * kotlin会通过类型推断自动推断数据类型
 *
 * -----字符串比较-----
 * kotlin中==相当于Java中的.equals
 * kotlin (Object, boolean)如果boolean为true忽略字母大小写, 否则不忽略字母大小写
 *
 * -----空值处理-----
 *  方法接收的参数其实是非空的, 直接传递null会直接报错
 *  如果接收的参数可以在String后面加上? 表示参数可以为空
 *
 *  1 .. 100表示1到100的区间 开区间
 *  num 0 in 100
 *  step 步长
 *  reversed 反转
 *
 *  -----List&Map-----
 *
 *
 * -----函数表达式-----
 *  如果方法又返回值, 可以直接=返回值
 *   val i = { a: Int, b: Int -> a + b }
 *   val sum : (Int, Int) -> Int = { x, y -> x+y}
 *
 *  -----具名参数-----
 *  val Pi = 3.1213f
 *  fun temp(PI : Float = Pi, radius : Float)
 *  temp(radius = 3.0f)
 */

val Pi = 3.14159f

fun hello() {
    println("hello, kotlin")
}

fun printMethod(): Unit {
    println("printMethod")
}

fun sayHello(content: String): String {
    return content
}

fun checkAge(age: Int, age1: Int): Boolean {
    return age > age1
}

fun saveLog(log: Int): Unit {
    println(log)
}

/**
 * if--else练习
 */
fun checkFace(face: Int): String {
    return if (face > 70) "nice" else "bad"
}

/**
 * when联系
 */
fun checkPerson(score: Int): String {
    return when (score) {
        100 -> "good"
        90 -> "nice"
        80 -> "butter"
        else -> {
            "bad"
        }
    }
}

/**
 * $练习
 */
fun printNumber(number: Int): Unit {
    println("number = $number")
}

/**
 * 同步代码
 */
fun open(): Unit {
    Thread.sleep(500)
    println("open")
}

fun comeIn(): Unit {
    Thread.sleep(1500)
    println("come in ")
}

fun close(): Unit {
    Thread.sleep(1000)
    println("close")
}

/**
 * 字符串比较
 */
fun checkString(name1: String, name2: String): Boolean {
    return name1 == name2
}

/**
 * 空值处理
 */
fun checkNull(check: String?): Unit {
    println("params $check")
}

/**
 * 循环练习
 */
fun loopTest(): Unit {
    val numbers = 1..100
    val result = numbers.sum()
    println("sum = $result")
}

/**
 * 区间练习
 */
fun rangeTest(): Unit {
    val numbers = 1 until 100
    println(numbers)
    for (num in numbers step 2) {
        print("$num, ")
    }
    val reverseNumber = numbers.reversed()
    for (num in reverseNumber) {
        print("$num, ")
    }
}

/**
 * List练习
 */
fun listTest(): Unit {
    val list = listOf("a", "b", "c")
    for (params in list) {
        println(params)
    }
    for ((i, e) in list.withIndex()) {
        println("$i, $e")
    }
}

/**
 * map练习
 */
fun mapTest(): Unit {
    val map = TreeMap<String, String>()
    map["hao"] = "good"
    map.put("sang", "up")
    println(map["hao"])
}

/**
 * 函数表达式
 */
fun functionTest(numberA: Int, numberB: Int): Int = numberA + numberB

fun functionTest2(numberA: Int, numberB: Int): Boolean {
    val i = { a: Int, b: Int -> a + b }
    return i(1, 2) > numberB + numberA
}

fun functionTest3(numberA: Int, numberB: Int): Int {
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    return sum(numberA, numberB)
}

/**
 * 方法中默认参数和具名参数
 */
fun getSquareArea(x: Float, y: Float): Float = x * y

fun getRoundArea(PI: Float = Pi, radius: Float): Float = 2 * PI * radius

/**
 * 字符串转数字
 */
fun stringValueOfInt(str: String): Int = str.toInt()

/**
 * 数字转字符串
 */
fun intValueOfString(int: Int): String = int.toString()

/**
 * 接收键盘
 */
fun inputTest(): String {
    print("please fir")
    var number1 = readLine()
    var number2 = readLine()
    return number1+number2
}


fun main(args: Array<String>) {
    hello()
    @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
    var name: String = "A"
    name = "B"
    println("name=$name")
    printMethod()
    println(sayHello("say Hello"))
    println(checkAge(9, 10))
    saveLog(101010101)
    println(checkFace(10))
    println(checkPerson(100))
    printNumber(100)
//    val startTime = System.currentTimeMillis()
//    open()
//    comeIn()
//    close()
//    val endTime = System.currentTimeMillis()
//    println("$startTime, $endTime")
//    println(checkString("brant", "brant"))
    checkNull(null)
//    loopTest()
//    rangeTest()
    listTest()
    mapTest()
    println(functionTest(1, 2))
    println(functionTest2(2, 3))
    println(functionTest3(2, 3))
    println("the square of area is ${getSquareArea(3.0f, 3.0f)}")
    println("the round of area is ${getRoundArea(radius = 3.5f)}")
    println(stringValueOfInt("10"))
    println(intValueOfString(10))
}