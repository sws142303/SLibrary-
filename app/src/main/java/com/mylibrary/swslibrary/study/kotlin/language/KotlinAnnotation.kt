package com.mylibrary.swslibrary.study.kotlin.language

import com.mylibrary.swslibrary.study.java.SLog

/**
 *@author Sws
 *@Time 2021/11/22 23:07
 *@msg
 **/
fun main() {
    //案例测试
    fire(ApiGetArticles())

    val test1 = listOf<String>("1","2","3")
    val test2 = listOf<Int>(1,2,3)
    SLog.print("result = ${isArray(test1)}")
    SLog.print("result = ${isArray(test2)}")

}

//创建一个注解 名为ApiDoc 通过annotation class来修饰
@Target(AnnotationTarget.CLASS) //设置该注解只能用来修饰class
@Retention(AnnotationRetention.SOURCE)//设置该注解保留在源代码级别  还有两个（BINARY编译时期  RUNTIME运行时期）
annotation class ApiDoc(val value: String)

//使用注解
@ApiDoc("使用注解修饰class")
class Box {
    //    @ApiDoc("使用注解修饰变量")
    val size = 6

    //    @ApiDoc("使用注解修饰方法")
    fun test() {
    }
}

/**
 * 自定义注解 实现APi调用时的请求方法检查
 */
//定义枚举
public enum class Method {
    POST,
    GET
}

//自定义注解
@Target(AnnotationTarget.CLASS)//设置该注解只能用来修饰class
@Retention(AnnotationRetention.RUNTIME)//设置该注解保留在运行时期
annotation class HttpMethod(val method: Method)

//定义接口
interface Api {
    val name: String
    val version: String
        get() = "1.0"
}

//定义请求类
@HttpMethod(Method.GET)
class ApiGetArticles : Api {
    override val name: String
        get() = "/api.articles"
}

//通过反射来获取当前注解使用的请求类型
fun fire(api: Api){
   val annotations = api.javaClass.annotations
   val method =  annotations.find { it is HttpMethod } as? HttpMethod
    SLog.print("通过反射来获取当前注解使用的请求类型 method = ${method?.method}")
}

fun isArray(a:Any) : Boolean{
   return a is Array<*>
}


