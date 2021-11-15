package com.mylibrary.swslibrary.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    }

    private fun loadParamsType(params: Any) {
        SLog.e("$params is ${params::class.simpleName} type ")
    }
}
