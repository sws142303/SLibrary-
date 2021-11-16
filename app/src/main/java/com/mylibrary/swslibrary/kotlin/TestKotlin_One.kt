package com.mylibrary.swslibrary.kotlin

import android.os.Build
import androidx.annotation.RequiresApi
import com.mylibrary.swslibrary.lambda.TestLambda
import com.mylibrary.swslibrary.utils.SLog
import kotlin.math.abs

/**
 *@author Sws
 *@Time 2021/11/15 21:37
 *@msg
 **/
//@RequiresApi的作用：仅仅是让编译通过，而并没有避免低版本的系统运行高版本的api的问题，在使用时我们需要自己判断版本号来使用不同的api。
@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    //kotlin会根据值类型 自动将变量设置为该类型
    //Int类型变量（kotlin自动转换）
    val num1 = 1;
    //Int类型变量（代码制定）
    val intNumber: Int = 1;
    //Double类型变量
    val num2 = -1.0;
    //Float类型变量
    val num3 = 2.0f;
    //String类型变量
    val string1: String = "ceceecee";
    //打印该变量对应类型
    loadParamsType(num1)
    loadParamsType(num2)
    loadParamsType(num3)
    loadParamsType(intNumber)
    loadParamsType(string1)
    //求num2的绝对值
    val abs = abs(num2)
    SLog.e("num2绝对值：$abs")
    //将num2转换为Int变量
    val toInt = num2.toInt()
    SLog.e("num2toInt：$toInt")
    //将num2转换为String变量
    val toString = num2.toString()
    SLog.e("num2toString：$toString")

    createArray()
    collectionType()
    collectionSort()
    wtsk()

    /**
     * 练习Lambda表达式
     */
    val testLambda = TestLambda { a, b, c ->
        SLog.print("a = $a b = $b c = $c")
    }

    testLambda.testLambda{
        SLog.print(it)
    }
}

/**
 * 问题思考
 */
fun wtsk(){

    /**
     * Q1 两个具有相同键值对，但顺序不相同的Map相等吗？为什么
     */
    val mapOf1 = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4)
    val mapOf2 = mapOf("key2" to 2, "key1" to 1, "key4" to 4, "key3" to 3)
    SLog.print("mapOf1 == mapOf2 : ${mapOf1 == mapOf2}")
    SLog.print("mapOf1 == mapOf2 : ${mapOf1.equals(mapOf2)}")
    /**
     * Q1问题解析 mapOf1 == mapOf2 相当于mapOf1.equals(mapOf2)
     * Map.equals源码中
     * 1.会先判断所指向的地址是否相同 如果相同返回true 否则返回false
     * 2.通过（mapB instanceOf Map）判断如果不是map实例 返回false
     * 3.判断两个map的长度是否相同 如果不相等 返回false
     * 4.接下来会遍历MapA 判断MapA中的keyMapB是否包含 不包含的话返回false
     * 如果包含的话会将key在MapA和MapB中对应的value取出来判断value是否相同 如果不相同则返回false
     * 总结：整个判断过程与存储顺序没有关系 所以两个具有相同键值对，但顺序不相同的Map相等
     */

    /**
     * Q2 两个具有相同元素 但顺序不同的list 相等吗？
     */
    var listOf1 = listOf(0, 1, 2, 3, 4, 5)
    var listOf2 = listOf(0, 2, 1, 5, 4, 3)
    SLog.print("排序前 listOf1 == listOf2 : ${listOf1 == listOf2}")
    SLog.print("排序前 listOf1 == listOf2 : ${listOf1.equals(listOf2)}")
    /**
     * Q1问题解析 listOf1 == listOf2 listOf1.equals(listOf2)
     * list.equals源码中
     * 1.会先判断所指向的地址是否相同 如果相同返回true 否则返回false
     * 2.通过（listOf2 instanceOf list）判断如果不是List实例 返回false
     * 3.判断两个List的长度是否相同 如果不相等 返回false
     * 4.接下来会遍历listOf2 根据索引进行判断元素是否相同 如果不相同则返回false
     * 总结：判断过程与元素存储顺序存在关系 所以两个具有相同元素 但顺序不同的list不相等
     *      可在进行判断前先对集合进行排序 排序后进行比较 会得到相等的结果
     */
    listOf1 = listOf1.sorted();
    listOf2 = listOf2.sorted();
    SLog.print("排序后 listOf1 == listOf2 : ${listOf1 == listOf2}")
    SLog.print("排序后 listOf1 == listOf2 : ${listOf1.equals(listOf2)}")
}

/**
 * 集合排序
 */
fun collectionSort(){
    SLog.print("=====================================我是分割线===========================================")
    //创建一个可变集合
    val numberSList = mutableListOf(0, 1, 2, 3, 4, 5, 6)
    SLog.print("原集合 numberSList = $numberSList")
    //对集合进行随机排序
    numberSList.shuffle()
    SLog.print("随机排序后的集合 numberSList = $numberSList")
    //对集合进行从小到大排序
    numberSList.sort()
    SLog.print("从小到大排序后的集合 numberSList = $numberSList")
    //对集合进行从大到小排序
    numberSList.sortDescending()
    SLog.print("从大到小排序后的集合 numberSList = $numberSList")

    SLog.print("=====================================条件排序===========================================")
    //条件排序

    //创建一个数据类
    data class Language(var name: String, var score: Int)
    //创建一个用来存储Language数据类的可变集合
    val languageList = mutableListOf<Language>()
    languageList.add(Language("张三", 13))
    languageList.add(Language("李四", 14))
    languageList.add(Language("王五", 15))
    languageList.add(Language("马六", 16))
    languageList.add(Language("赵七", 17))
    //通过score字段来进行由小到大的排序 适合单条件排序
    languageList.sortBy { it.score }
    SLog.print("通过score字段来进行由小到大的排序 适合单条件排序 \r\nlanguageList = $languageList")
    //通过score字段来进行由大到小的排序 适合单条件排序
    languageList.sortByDescending { it.score }
    SLog.print("通过score字段来进行由大到小的排序 适合单条件排序 \r\nlanguageList = $languageList")
    //通过sortWith进行由小到大排序 适合多条件排序 score为第一条件 name为第二条件(按照英文字母顺序来排序)
    languageList.sortWith(compareBy({ it.score }, { it.name }))
    SLog.print("通过sortWith进行由小到大排序 适合多条件排序 \r\nlanguageList = $languageList")

    SLog.print("=====================================Map集合===========================================")
    //创建可变的set集合
    val hello = mutableSetOf("h", "e", "l", "l", "o")//自动过滤重复元素
    hello.remove("o")//移除元素值为"o"的元素
    SLog.print("创建可变的set集合 并且移除元素值为\"o\"的元素 初始内容(\"h\",\"e\",\"l\",\"l\",\"o\") \r\nresult = $hello")
    //集合的加减操作(注意 接受加减结果的集合必须是一个可变集合)
    hello += setOf("w", "o", "r", "d", "l")
    SLog.print("集合加减操作 $hello + setOf(\"w\",\"o\",\"r\",\"d\",\"l\") \r\nresult = $hello")

    //Map<K,V>不是collection接口的继承者，但是他也是Kotlin中的一种集合类型
    val mapOf = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 4)
    SLog.print("创建Map集合 mapOf = $mapOf")
    SLog.print("获取Map中的全部的Key keys = ${mapOf.keys}")
    SLog.print("获取Map中的全部的value values = ${mapOf.values}")

    //判断当前Map中是否包含这个key(两种方式)
    //方式一
    if ("key1" in mapOf){
        SLog.print("方式一 判断当前Map的key中是否包含key1 true 对应的value = ${mapOf["key1"]}")
    }else{
        SLog.print("方式一 判断当前Map的key中是否包含key1 false")
    }
    //方式二
    if (mapOf.containsKey("key1")){
        SLog.print("方式二 判断当前Map的key中是否包含key1 true 对应的value = ${mapOf["key1"]}")
    }else{
        SLog.print("方式二 判断当前Map的key中是否包含key1 false")
    }

    //判断当前Map中是否包含这个value(两种方式)
    //方式一
    if (1 in mapOf.values){
        SLog.print("方式一 判断当前Map中是否包含这个value 1 true")
    }else{
        SLog.print("方式一 判断当前Map中是否包含这个value 1 false")
    }

    //方式二
    if (mapOf.containsValue(1)){
        SLog.print("方式二 判断当前Map中是否包含这个value 1 true")
    }else{
        SLog.print("方式二 判断当前Map中是否包含这个value 1 false")
    }
}

/**
 * 集合
 */
fun collectionType(){

    SLog.print("=====================================我是分割线===========================================")

    /**
     * 创建不可变集合
     * listOf创建的集合 元素值可重复
     * setOf创建的集合  元素值不可重复(如果检测到元素重复 会删除后面添加的元素)
     */
    val strListOf = listOf("one", "two", "three", "one")
    SLog.print("元素值可重复集合 listOf =  $strListOf")
    val strSetOf = setOf("one", "two", "three", "one")
    SLog.print("元素值不可重复集合 strSetOf = $strSetOf")

    /**
     * 创建可变集合
     */
    val numberList = mutableListOf(0, 1, 2, 3, 4)
    numberList.add(5)
    numberList.removeAt(0)
    numberList[0] = 0
    SLog.print("原数组为：[0,1,2,3,4] 操作流程 ---》添加元素5 删除元素0 将元素1修改为元素0 \r\n修改后的集合数据 = $numberList")
}
/**
 * 返回当前参数类型
 */
private fun loadParamsType(params: Any) {
    SLog.e("$params is ${params::class.simpleName} type ")
}

/**
 * 数组相关
 */
private fun createArray() {
    SLog.print("=====================================我是分割线===========================================")
    //创建数组五种方式
    //方式一
    val array = arrayOf(1, 2, 3)

    //方式二
    val arrayOfNulls = arrayOfNulls<Int>(3)
    arrayOfNulls[0] = 1;
    arrayOfNulls[1] = 2;
    arrayOfNulls[2] = 3;

    //方式三
    val array2: Array<String> = Array(5) { i -> (i * i).toString() }
    array2[0] = "l"
    array2[1] = "o"
    array2[2] = "v"
    array2[3] = "e"
    array2[4] = "Y"

    //方式四 创建一个只能存放int类型的数据
    val intArrayOf = intArrayOf(1, 2, 3)

    //方式五 创建一个指定大小并且指定存放类型的数组
    val intArrayOfSupport = IntArray(5) //长度为5 值为[0,0,0,0,0]
    val intArrayOfSupport2 = IntArray(5){8} //长度为5 值为[2,2,2,2,2]

    SLog.print("intArrayOf[0] + intArrayOf[1] = ${intArrayOf[0] + intArrayOf[1]}")
    SLog.print("intArrayOfSupport[0]=${intArrayOfSupport[0]} intArrayOfSupport[1]=${intArrayOfSupport[1]}")
    SLog.print("intArrayOfSupport2[0]=${intArrayOfSupport2[0]} intArrayOfSupport2[1]=${intArrayOfSupport2[1]}")

    /**
     * 数组遍历 五种方式
     */
    //方式一 遍历元素
    for (item:String in array2){
        setDate("   方式一遍历", array2)
        SLog.print(item)
    }
    //方式二 带索引遍历
    for (position : Int in array2.indices){
        setDate("       方式二遍历", array2)
        SLog.print(array2[position])
    }
    //方式三 带索引及元素遍历
    for((index: Int, item: String) in array2.withIndex()){
        setDate("           方式三遍历", array2)
        SLog.print(item + " index=$index")
    }
    //方式四 forEach
    setDate("                   方式四遍历", array2)
    array2.forEach { SLog.print(it) }

    //方式五 forEach增强版
    setDate("                       方式五遍历", array2)
    array2.forEachIndexed{ index, item -> SLog.print(item + "  index = $index") }
}

fun setDate(params: String, string: Array<String>){
    for (position : Int in string.indices){
        string[position] = params + position
    }
}

