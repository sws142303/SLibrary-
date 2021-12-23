package com.mylibrary.swslibrary.study.kotlin.language

import android.app.Activity
import androidx.annotation.IdRes
import com.mylibrary.swslibrary.study.java.SLog

/**
 *@author Sws
 *@Time 2021/11/23 21:53
 *@msg 扩展方法
 **/

fun main() {

    /**
     * 扩展方法
     */
    val mLIst = mutableListOf<Int>(1, 2, 3, 4, 5)
    //调用扩展函数
    mLIst.swap(0, 1)
    SLog.print("使用扩展函数 对集合前两个元素进行顺序互换 \r\n 改变前的集合 mList = [1,2,3,4,5] \r\n 改变后的集合 mList = $mLIst")
    //调用泛型扩展方法
    mLIst.swap2(0, 1)
    val mStringList = mutableListOf<String>("张三", "李四", "王五")
    mStringList.swap2(0, 1)
    SLog.print("使用泛型扩展函数 对集合前两个元素进行顺序互换 \r\n 改变前的集合 mList = [\"张三\",\"李四\",\"王五\"] \r\n 改变后的集合 mList = $mStringList")

    /**
     * 扩展属性
     */
    SLog.print("调用String的扩展属性LastChar = ${"Sws大帅哥呀".lastChar}")

    /**
     * 为伴生对象添加扩展函数。
     * 调用Jump的伴生对象的扩展函数。
     */
    //方式一
    Jump.print()
    //方式二
    Jump.print()

    /**
     * let扩展
     */
    testLet("Sws大帅哥！let测试")

    /**
     * run扩展
     */
    testRoom(Room("北京",1.0f,1.0f))

    /**
     * apply扩展
     */
    testApply()
}

/**
 * 扩展方法，泛型扩展方法
 * 方法实现的功能：改变集合中index1和index2的顺序
 */
//方式一 扩展方法 这样写的话只能针对MutableList<Int>这种类型 扩展性较差
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

//方式二 泛型扩展方法 这样写的话可以针对MutableList<*>全部类型 扩展性较好
fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

/**
 * 扩展属性
 * 为String类型的变量常量定义一个扩展属性 该属性对应的value为String字符串的最后一个字符。
 * 原理解析：其实在Java中 相当于定义了一个getLastChar(String s)静态方法 该方法返回String参数的最后一个字符
 */
// 写法一
//val String.lastChar : Char get() = this.get(this.length -1)
// 写法二
val String.lastChar: Char get() = this[this.length - 1]

/**
 * 为Jump的伴生对象添加一个扩展函数print
 */
fun Jump.Companion.print() {
    SLog.print(" 为Jump的伴生对象添加一个扩展函数")
}

//创建Jump类
class Jump {
    //创建Jump的伴生对象
    companion object {

    }
}

/**
 * let扩展方法
 * 定义一个参数为String类型 加？表示这个值有可能是空的
 */
fun testLet(str: String?) {

    //常见的使用方式一
    //通过?.的方式 确保不会出现空指针的异常,避免str为null的情况
    str?.let {
        SLog.print("let   it = $it")
        SLog.print("let   length = ${it.length}")
    }

    //常见的使用方式二
    //限制作用域
    str?.let {
        val let2 = "let作用域"
        SLog.print("let 方式二 限制作用域 value = ${it + let2}}")
    }

}

/**
 * run扩展方法
 */
//定义一个Room数据类
data class Room(val address: String, val price: Float, val size: Float)
fun testRoom(room: Room){
    room.run {
        //通过run扩展 可以直接访问Room类中的变量 不需要在通过类名.变量的方式来访问
        SLog.print("Room.run address = $address price = $price size = $size")
    }
}

/**
 * apply扩展方法
 */
fun testApply(){
    ArrayList<String>().apply {
        //可以通过apply来做一些初始化的操作
        add("1")
        add("2")
        add("3")
        add("4")
        add("5")
    }.let {
        SLog.print("apply扩展 it = $it")
    }
}

/**
 *案例：使用Kotlin扩展为控件绑定监听器减少模版代码
 */
//描述:为Activity添加find扩展方法，用于通过资源id获取控件
fun <T : android.view.View> Activity.find(@IdRes id:Int) : T{
    return findViewById(id)
}
//为View添加扩展onClick扩展方法，用于为View添加onClick监听
fun Int.onCLick(activity: Activity,click : () -> Unit){
    activity.find<android.view.View>(this).apply {
        setOnClickListener {
            click()
        }
    }
}