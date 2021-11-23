package com.mylibrary.swslibrary.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.mylibrary.swslibrary.R
import com.mylibrary.swslibrary.utils.SLog
import kotlin.math.abs

class TestKotlinActivity : AppCompatActivity() {
    private val num1 = 1;
    private val num2 = -1.0;
    private val num3 = 2.0f;
    private val intNumber: Int = 1;
    private val string1: String = "ceceecee";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_kotlin)
        loadParamsType(num1)
        loadParamsType(num2)
        loadParamsType(num3)
        loadParamsType(intNumber)
        loadParamsType(string1)
        val abs = abs(num2)
        SLog.e("num2绝对值：$abs")
        val toInt = num2.toInt()
        SLog.e("num2toInt：$toInt")
        val toString = num2.toString()
        SLog.e("num2toString：$toString")

        /**
         * 测试扩展函数
         */
        val mTextView = find<TextView>(R.id.tv_test)
        R.id.tv_test.onCLick(this){
            mTextView.text = "测试扩展函数"
        }
    }

    /**
     * 返回当前参数类型
     */
    private fun loadParamsType(params: Any) {
        SLog.e("$params is ${params::class.simpleName} type ")
    }

    /**
     * 创建数组
     */
    private fun createArray(){
        //方式一
       val array =  arrayOf(1,2,3)
        //方式二
       val arrayOfNulls =  arrayOfNulls<Int>(3)
        arrayOfNulls[0] = 1;
        arrayOfNulls[1] = 2;
        arrayOfNulls[2] = 3;
        //方式三
        val array2 : Array<String> = Array(5){ i -> (i*i).toString()}

        //创建一个只能存放int类型的数据
        val intArrayOf = intArrayOf(1,2,3)
        SLog.print("intArrayOf[0] + intArrayOf[1] = ${intArrayOf[0] + intArrayOf[1]}")
    }
}
