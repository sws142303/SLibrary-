package com.mylibrary.swslibrary.kotlin

import com.mylibrary.swslibrary.utils.SLog

/**
 *@author Sws
 *@Time 2021/11/22 22:13
 *@msg
 **/
fun main() {
    /**
     * 实现泛型接口
     */
    val coke = Coke()
    coke.taste()

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
 * 泛型接口 通过interface来定义一个接口，制定泛型为 T
 */
interface Drinks<T> {
    fun taste() : T //定义抽象方法taste 返回值为泛型T
    fun price(t: T) //定义抽象方法taste 入参为泛型T
}
//定义一个Sweet类
open class Sweet {
    //定义一个Int常量
    open var price = 5
    open var name = "Sweet"
}
//使用泛型接口
class Coke : Drinks<Sweet> {
    override fun taste(): Sweet {
        SLog.print("实现泛型接口 Coke taste")
        //通过方法表达式 更改Sweet类中的字段值
        val mSweet = object : Sweet(){
            override var price: Int
                get() = 16888
                set(value) {}

            override var name: String
                //                get() = super.name //使用父类的name值
                get() = "Sws--帅哥"   //覆盖父类变量值
                set(value) {}
        }
        price(mSweet)
        return mSweet
    }
    override fun price(t: Sweet) {
        SLog.print("实现泛型接口 Coke price price = ${t.price} name = ${t.name}")
    }
}

/**
 * 泛型类相关
 * 为Color抽象类添加泛型为T 且Color主构造需要一个T类型的参数
 */
abstract class Color<T>(var t2: T/*泛型字段*/) {
    abstract fun printColor()
}
//定义Blue类
class Blue() {
    //定义color常量
    val color: String = "Blue"
}
//定义BlueColor类 实现Color泛型类（也是一个抽象类）
class BlueColor(t: Blue) : Color<Blue>(t) {
    override fun printColor() {
        //可以直接访问父类Color中的泛型字段t2
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
