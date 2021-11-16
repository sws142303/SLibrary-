package com.mylibrary.swslibrary.kotlin

import android.app.slice.Slice
import android.os.Build
import androidx.annotation.RequiresApi
import com.mylibrary.swslibrary.utils.SLog

/**
 *@author Sws
 *@Time 2021/11/16 21:48
 *@msg
 **/
fun main() {
    /**
     * 调用带返回值的函数体
     */
    val fun99 = functionLear(99)
    val fun100 = functionLear(100)
    val fun101 = functionLear(101)
    SLog.print("fun99 = $fun99 \r\nfun100 = $fun100 \r\nfun101 = $fun101")

    //调用成员方法
    Person().test1()
    //调用类方法
    Person.test2()

    //调用静态类中的静态方法
    SLog.print("调用静态类中的静态方法 = ${NumUtil.double(260)}")

    //调用单表达式方法
    SLog.print("调用单表达式方法 double(260) value = ${double(260)}")

    //调用带有默认值参数的方法
    read(listOf(1, 1, 1)) //使用方法中的默认值
    read(listOf(1, 1, 1), 99999999, 9999999) //使用方法中传入的值

    //调用可变参数的方法
    SLog.print("调用可变参数的方法 1 result ---> ${append("床前明月光")}")
    SLog.print("调用可变参数的方法 2 result ---> ${append("床前明月光", "疑是地上霜")}")
    SLog.print("调用可变参数的方法 3 result ---> ${append("床前明月光", "疑是地上霜", "举头望明月")}")
    SLog.print("调用可变参数的方法 4 result ---> ${append("床前明月光", "疑是地上霜", "举头望明月", "低头思故乡")}")
    SLog.print("调用可变参数的方法 5 result ---> ${append("床前明月光", "疑是地上霜", "举头望明月", "低头思故乡", "author：李白")}")

    //调用局部方法
    SLog.print("调用局部方法magic() value = ${magic()}")

    /**
     * lambda表达式与正常写法区别
     */
    //示例一 无参数无返回值
    test1_lambda()//无参数情况--lambda写法
    test1()//无参数情况--正常写法

    //示例二
    SLog.print("有参数有返回值情况--lambda写法 test2_lambda(1, 2) value = ${test2_lambda(1, 2)}")//有参数有返回值情况--lambda写法
    SLog.print("有参数有返回值情况--lambda写法(简化版) test2_lambda2(1,2) value = ${test2_lambda2(1,2)}")//有参数有返回值情况--lambda写法(简化版)
    SLog.print("有参数有返回值情况--正常写法 test2(1, 2) value = ${test2(1, 2)}")//有参数有返回值情况--正常写法

    //认识kotlin中的it
    test3()
}

/**
 * 创建一个有返回值的函数
 */
fun functionLear(days: Int): Boolean {
    return days > 100
}

/**
 * 创建一个普通的Person类
 */
class Person {
    /**
     * Kotlin中的成员方法
     */
    fun test1() {
        SLog.print("调用Person中的成员方法")
    }

    /**
     * 创建一个Kotlin的类方法
     * companion object也叫kotlin的伴生对象
     */
    companion object {
        /**
         * 这样创建的方法相当于java中通过static修饰的静态方法
         */
        fun test2() {
            SLog.print("companion object 调用类方法")
        }
    }
}

/**
 * 创建静态类 类中的方法为静态方法
 */
object NumUtil {
    fun double(int: Int): Int {
        return int * 2
    }
}

/**
 * 单表达式方法，当方法返回单个表达式时，可以省略花括号并且在"="符号之后指定代码体即可；
 */
//该方法实现功能与NumUtil.double一致
fun double(int: Int): Int = int * 2

/**
 * 默认值，方法参数可以有默认值，当省略相应的参数时使用默认值。与Java相比，这可以减少重载数量
 */
fun read(b: List<Int>, off: Int = 0, len: Int = b.size) {
    SLog.print("执行read方法（参数有默认值的方法） params2默认值为0 params3默认值为params1的size  params1 = $b  params2 = $off  params3 = $len")
}

/**
 * 可变数量的参数
 */
fun append(vararg str: String): String {
    val append = StringBuffer()
    for (char in str) {
        append.append(char)
    }
    return append.toString()
}

/**
 * 局部方法（方法中套方法）
 */
fun magic(): Int {
    fun foo(v: Int): Int {
        return v * v
    }

    val random = (0..100).random() //生成一个0——100的随机数
    return foo(random)
}

/**
 * lambda表达式在kotlin中的应用
 */

//示例一 无参数无返回值
fun test1() {
    SLog.print("无参数情况--正常写法")
}

val test1_lambda = { SLog.print("无参数情况--Lambda写法") }

//示例二 多个参数并且有返回值
//多个参数有返回值--正常写法
fun test2(params1: Int, params2: Int): Int {
    SLog.print("")
    return params1 + params2
}
//多个参数有返回值--lambda写法
val test2_lambda: (Int, Int) -> Int = { params1, params2 -> params1 + params2 }
//简化版
val test2_lambda2 = { params1: Int, params2: Int -> params1 + params2 }

/**
 * 认识kotlin中的it
 */
fun test3(){
    val array = arrayOf(1,2,3,4,5)
    //给array添加过滤 过滤出来小于五的元素
    SLog.print("认识kotlin中的it array.filter { it > 5 } value ${array.filter { it < 5 }}")
}

/**
 * kotlin中的下划线"_"的作用
 */
@RequiresApi(Build.VERSION_CODES.N)
fun test4(){
    val mapOf = mapOf("key1" to "value1","key2" to "value2","key3" to "value3")
    //这里通过遍历map 输出mao的value key是没有用的到 就可以使用下划线来将它代替
    mapOf.forEach { (key, value) -> SLog.print(value) }
    //使用下划线省略key
    mapOf.forEach { (_, value) -> SLog.print(value) }
}