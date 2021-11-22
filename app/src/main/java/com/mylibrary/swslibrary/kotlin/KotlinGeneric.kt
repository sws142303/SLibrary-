package com.mylibrary.swslibrary.kotlin

import com.mylibrary.swslibrary.utils.SLog

/**
 *@author Sws
 *@Time 2021/11/22 22:13
 *@msg
 **/
fun main() {
    /**
     * 泛型接口
     */
    SLog.print("Coke().taste().price value = ${Coke().taste().price}")

    /**
     * 泛型类
     */
    BlueColor(Blue()).printColor()

    /**
     * 泛型约束
     */
    test()
}

/**
 * 泛型接口
 */
interface Drinks<T> {
    fun taste(): T
    fun price(t: T)
}

class Sweet {
    val price = 5
}

class Coke : Drinks<Sweet> {
    override fun taste(): Sweet {
        SLog.print("Coke taste")
        return Sweet()
    }

    override fun price(t: Sweet) {
        SLog.print("Coke price value = ${t.price}")
    }


}

/**
 * 泛型类
 */
abstract class Color<T>(var t2: T/*泛型字段*/) {
    abstract fun printColor()
}

class Blue() {
    val color: String = "Blue"
}

class BlueColor(t: Blue) : Color<Blue>(t) {
    override fun printColor() {
        SLog.print("BlueColor-printColor color = ${t2.color}")
    }

}

/**
 * 泛型方法
 */
fun <T> fromJson(json: String, tClass: Class<T>): T? {
    //获取T的实例
    val t: T = tClass.newInstance()
    return t
}

/**
 * 泛型约束
 */
fun test() {
    sort(listOf(1, 2, 3)) //可以正常传入 Int是Comparable<Int>的子类型
//    sort(listOf(Blue())) //不可以正常传入 Blue不是Comparable<Blue>的子类型

    //多个上界
    val listString = listOf("A","B","C")
    val list = testT(listString,"B")
    SLog.print("多个上界 result = $list")
}

//单个上界
fun <T : Comparable<T>?> sort(list: List<T>?): Unit {}

//多个上界
fun <T> testT(list: List<T>, threshold: T): List<T>
        where T : Comparable<T>,
              T : CharSequence {
    return list.filter { it > threshold }.map { it }
}
