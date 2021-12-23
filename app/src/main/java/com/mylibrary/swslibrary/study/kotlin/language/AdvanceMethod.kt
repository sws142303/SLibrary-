package com.mylibrary.swslibrary.study.kotlin.language

import com.mylibrary.swslibrary.study.java.SLog

/**
 *@author Sws
 *@Time 2021/11/17 22:23
 *@msg
 **/
fun main() {
    //创建一个元素为1,2,3的Int类型的List集合
    val mList = listOf(1, 2, 3)
    //调用自定义的高阶函数sum
    val result = mList.sum {
        //参数只有一个的时候 就可以使用it代替
        SLog.print("调用高阶函数-函数作为参数 遍历集合元素 it-value =  $it")
    }
    SLog.print("调用高阶函数-函数作为参数 输出返回值 result  =  $result")


    //调用自定义的高阶函数toIntSum
    // 因为toIntSum的返回值也是一个函数 可以使用toIntSum()(3)这个方式来调用他返回的函数 并传递一个Int类型的值：3
    val result2 = mList.toIntSum()(3)
    SLog.print("函数作为返回值 调用函数返回的函数 返回的value = $result2")

    testClosure(2)(8) {
        SLog.print("调用闭包方法 value = $it")
    }
    //解构声明
    test2()

    //调用匿名方法
    val fun1Result = fun1(1, 2)
    SLog.print("调用匿名方法 fun1 value = $fun1Result")
    val fun2Result = fun2(1, 2)
    SLog.print("调用匿名方法 fun2 value = $fun2Result")

    //方法字面值
    literal()

    //定义一个变量mResult 他接受一个参数类型为Int 返回值为boolean类型的函数
    var mResult: ((Int) -> Boolean)? = null
    //使用正常方式为mResult赋值函数
    //mResult = { p -> p > 10}
    //使用Lambda表达式为mResult赋值函数
    mResult = {it > 10}
    //此时的mResult为一个方法字面值
    //testLiteral接受一个参数为Int类型返回值为Boolean的函数体
    testLiteral(mResult)//调用方式一
    testLiteral { p -> p > 10 }//调用方式二
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
    //返回List中所有元素的和
    return result
}

/**
 * 函数作为返回值
 * 函数功能：对集合元素进行求和的高阶函数，并且返回一个声明为 (scale : Int) -> Float的函数
 * 函数解析：
 * 1.首先对Lsit<Int>这种类型进行方法扩展 扩展一个toIntSum方法出来
 * 2.设置toIntSum方法的返回值为一个方法（函数）
 * 3.toIntSum返回的方法 接受一个Int类型的参数 并且他的返回值是一个Float类型
 */
fun List<Int>.toIntSum(): (scale: Int) -> Float {
    //第一层函数
    return fun(scale): Float {
        var reasult = 0f
        //这里this就代表List<Int>本身
        for (v in this) {
            reasult += v * scale
        }
        //返回List中所有元素*2的和
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

/**
 * 自己练习方法字面值
 * 参数P 为一个函数 这个函数接受一个Int类型的参数 会返回一个Boolean类型的参数
 * 参数P这个函数封装的功能为：判断传入的Int类型的参数是否大于10 返回结果（true或者false）
 */
fun testLiteral(p: (Int) -> Boolean) {
    val invoke = p.invoke(11)
    SLog.print("自己练习方法字面值 result = $invoke")
}
