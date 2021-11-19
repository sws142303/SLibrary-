package com.mylibrary.swslibrary.kotlin

import android.view.View
import android.widget.TextView
import com.mylibrary.swslibrary.utils.SLog
import java.util.*

/**
 *@author Sws
 *@Time 2021/11/18 21:55
 *@msg
 **/
fun main() {

    /**
     * 类实例的创建方式
     */
    //调用KotlinClass的主构造方法
    val testKotlinClass1 = KotlinClass("通过主构造 创建KotlinClass实例")
    //调用KotlinClass的次构造方法一
    val testKotlinClass2 = KotlinClass(18, "通过次构造一 创建KotlinClass实例")
    //调用KotlinClass的次构造方法二
    val testKotlinClass3 = KotlinClass(18, "通过次构造二 创建KotlinClass实例", 30)
    SLog.print(
            "输出KotlinClass \r\n " +
                    "实例一 = $testKotlinClass1 \r\n " +
                    "实例二 = $testKotlinClass2 \r\n " +
                    "实例三 = $testKotlinClass3")

    /**
     * 继承
     */
    Dog(168)//继承Animal的主构造
    Dog2(168, "Sws-帅哥")//继承Animal的次构造

    /**
     * 方法与变量的覆盖
     */
    val dog3 = Dog3(168)
    dog3.isFalse = true
    dog3.logPrint(168, "Sws-1688888888")


    /**
     * 变量set get以及val修饰变量初始化
     */
    val mShop = Shop()
    SLog.print("Shop isClose = ${mShop.isClose} name = ${mShop.name} address = ${mShop.address} score = ${mShop.score}")
    mShop.score = 1.1f
    SLog.print("Shop score = ${mShop.score}")


    /**
     * 变量延时初始化
     */
    //这种情况 由于没有进行初始化 所以会打印变量未初始化
    Test().test()

    //先进行变量初始化赋值等操作 在进行判断是否初始化
    val mTest = Test()
    mTest.setup()
    mTest.test()

    /**
     * 抽象类
     */
    FilePrinter().print()
}

/**
 * 类的主构造方法 constructor可以省略
 */
//不省略constructor写法
//class KotlinClass constructor(name:String){}
//省略constructor写法
open class KotlinClass(name: String) {
    /**
     * 次构造方法，Kotlin中次构造方法必须调用主构造方法（ 通过this(name) 可以调用主构造方法 并将主构造需要的参数传递过去）
     * 次构造方法可以声明多个
     */
    constructor(age: Int, name: String) : this(name) {
        //次构造方法一
    }

    constructor(age: Int, name: String, index: Int) : this(name) {
        //次构造方法二
    }

    //初始化代码块
    init {
        log(name)
    }

    fun log(name: String) {
        SLog.print("执行KotlinClass name  = $name")
    }
}

/**
 * 类的继承
 * 1.在kotlin中所有的类默认都是final类型的 不允许被继承
 * 2.如果想要当前类可以被其他类继承，需要在被继承的类（父类）class前使用open进行修饰 修饰后的类可以被其他类继承
 * 3.kotlin中想要继承类 需要使用":"来进行继承
 * 4.子类继承父类时 可以通过父类的主构造或者次构造来完成继承操作
 */
open class Animal(age: Int) {
    init {
        //用来初始化代码块
        logPrint(age, "Animal - init 方法触发")
    }

    /**
     * 次构造
     */
    open var isFalse: Boolean = false

    constructor(age: Int, name: String) : this(age) {
        isFalse = true
        logPrint(age, "Animal - 次构造方法")
    }

    open fun logPrint(age: Int, message: String) {
        if (isFalse) {
            SLog.print("$message age  = $age")
        } else {
            SLog.print("$message age  = $age")
        }
    }
}

/**
 * 定义Dog类 进行继承Animal 需要实现Animal的主构造或者次构造来完成继承
 * 设置Dog类的主构造方法为一个age：Int类型的参数
 */
//通过Animal的主构造来完成继承
class Dog(age: Int) : Animal(age) {
    //Dog的次构造
    constructor(age: Int, name: String) : this(age) {

    }
}

//通过Animal的次构造来完成继承
class Dog2(age: Int, name: String) : Animal(age, name) {
    //Dog2的次构造
    constructor(age: Int, name: String, index: Int) : this(age, name) {

    }
}

/**
 * 覆盖父类（Animal）中的方法与变量
 * 覆盖方法：
 * 1.首先父类（Animal）中存在logPrint这个函数 如果子类（Dog3）想要覆盖这个方法 需要在父类（Animal）中 使用open来修饰需要覆盖的方法
 * 2.在子类（Dog3）中需要用override来修饰需要覆盖的函数
 *
 * 覆盖变量
 * 1.首先父类（Animal）中存在isFalse这个变量 如果子类（Dog3）想要覆盖这个变量 需要在父类（Animal）中 使用open来修饰需要覆盖的变量
 * 2.在子类（Dog3）中需要用override来修饰需要覆盖的变量
 */
class Dog3(age: Int) : Animal(age) {
    /**
     * 覆盖父类中的变量
     */
    override var isFalse: Boolean = false

    /**
     * 覆盖父类中的logPrint方法 通过override关键字
     */
    override fun logPrint(age: Int, message: String) {
        SLog.print("覆盖父类中的logPrint方法 age = $age message = $message")
        SLog.print("覆盖父类中的isFalse变量 isFalse = $isFalse")
    }


}

/**
 * val变量初始化的方式，val修饰的变量初始化后值不可被修改
 *
 */
//方式一
val a: Int get() {
    //通过get 返回该变量a的值 为168
    return 168
}

//方式二
//在变量类型后面加一个？ 表示该变量可以为null
val a1: Int? = null

/**
 * 方式三
 * 构造方法中初始化该变量
 */
class Dog4(age: Int, a1: Int?) : Animal(age)


/**
 * 属性的声明
 */
class Shop {
    val name: String = "Android"
    val address: String? = null

    //判断是否大于11点
    val isClose: Boolean
        get() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 11 //判断当前时间是否大于11点 返回true或者false


    var score: Float = 0.0f
        //定义score变量get方法
        //判断当前变量值是否小于0.2f 小于的话这个变量值为0.0f。大于或者等于的话 就讲该变量值 * 1.5f
        get() = if (field < 0.2f) 0.2f else field * 1.5f
        //定义score变量set方法
        //val修饰的变量不能有set方法 只有var修饰的才可以有set方法
        set(value) {
            SLog.print("score -- set newValue = $value")
            field = value
        }
}

/**
 * 变量的延时初始化(通过 lateinit var来进行变量的延时初始化)
 */
class Test {
    //定义一个延时初始化的变量
    lateinit var shop: Shop

    //初始化shop
    fun setup() {
        shop = Shop()
    }

    //判断shop是否被初始化
    fun test() {
        //::表示创建成员引用或类引用
        if (::shop.isInitialized) SLog.print("变量的延时初始化 shop.isInitialized = ${::shop.isInitialized}") else SLog.print("shop 未初始化")
    }
}

/**
 * 定义一个抽象类 （抽象类中的方法都是抽象的）
 */
abstract class Printer {
    //定义一个抽象方法
    abstract fun print()
}

//定义一个类 继承Printer这个抽象类
//1.普通类要继承抽象类 就必须实现类中的抽象方法
//2.如果不想实现抽象方法 但是还想继承抽象类 就需要将子类也改为抽象类
class FilePrinter : Printer() {
    override fun print() {
        SLog.print("FilePrinter继承Printer这个抽象类 实现print抽象方法")
    }

}