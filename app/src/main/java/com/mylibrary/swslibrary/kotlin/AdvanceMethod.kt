package com.mylibrary.swslibrary.kotlin

import com.mylibrary.swslibrary.utils.SLog

/**
 *@author Sws
 *@Time 2021/11/17 22:23
 *@msg
 **/
fun main() {

    val mList = listOf(1, 2, 3)
    val result = mList.sum { SLog.print("调用高阶函数-函数作为参数 遍历集合元素 it-value =  $it") }
    SLog.print("调用高阶函数-函数作为参数 输出返回值 result  =  $result")

    val result2 = mList.toIntSum()(3)
    SLog.print("函数作为返回值 调用函数返回的函数 返回的value = $result2")

    testClosure(2)(8) {
        SLog.print("调用闭包方法 value = $it")
    }
    //解构声明
    test2()

    //方法字面值
    literal()
}

/**
 * 高阶函数--函数作为参数
 * 函数功能：实现对集合元素求和的高阶函数，并且每次遍历一个集合都要有回调
 *
 * 函数解析：
 * 1。首先对Lsit<Int>这种类型进行方法扩展 扩展一个sum方法出来
 * 2。定义参数为一个callback 确保每次遍历元素都会触发该回调
 * 3。添加一个方法 用来返回遍历List的元素 定义一个参数为（Int） 返回值为Unit 表示空的意思
 * 4。定义sum扩展方法返回值类型 返回集合的总和
 */
fun List<Int>.sum(callback: (Int) -> Unit): Int {
    var result = 0
    //这里this就代表List<Int>本身
    for (v in this) {
        result += v
        callback(v)
    }
    return result
}

/**
 * 函数作为返回值
 * 函数功能：对集合元素进行求和的高阶函数，并且返回一个声明为 (scale : Int) -> Float的函数
 */
fun List<Int>.toIntSum(): (scale: Int) -> Float {
    //第一层函数
    return fun(scale): Float {
        var reasult = 0f
        for (v in this) {
            reasult += v * scale
        }
        return reasult
    }
}

/**
 * 需求：实现一个名为testClosure方法 ，该方法接受一个Int类型v1参数，
 * 同时能够返回一个声明为（v2:Int,(Int) -> Unit）-> Unit的函数，并且这个函数可以计算v1，v2的和.
 *
 * 该方法是一个闭包，也是一个高级函数
 */
fun testClosure(v1: Int): (v2: Int, (Int) -> Unit) -> Unit {
    return fun(v2: Int, printer: (Int) -> Unit) {
        printer(v1 + v2)
    }
}

/**
 * 解构声明
 */
//创建一个数据类
data class Result(val message: String, val code: Int);
//对Result类的变量进行解构 并输出
fun test2() {
    val result = Result("帅哥Sws", 16888)
    val (message, code) = result
    SLog.print("解构Result对象中的变量 message = $message  code = $code")
}

/**
 * 匿名方法(没有方法名的方法)
 * fun (a : Int,b:Int):Int = a+b 就是一个匿名方法 也是一个表达式方法
 */
val fun1 = fun(a: Int, b: Int): Int = a + b
val fun2 = fun(a: Int, b: Int): Int {
    return a + b
}

/**
 * 方法字面值
 */
fun literal() {
    //定义了一个变量temp 而该变量的类型就是（Int）-> Boolean
    var temp: ((Int) -> Boolean?)? = null
    // { num -> (num > 10) } 方法字面值
    temp = { num -> (num > 10) }
    val result = temp(12)
    SLog.print("方法字面值 val result = temp(12) result = $result")
}
