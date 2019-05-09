package com.androidstudydata.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.R
import com.androidstudydata.kotlin.aboutClass.Person
import com.androidstudydata.kotlin.aboutClass.Runoob

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

    }

    fun personTest() {

        var person: Person = Person()

        //给属性赋值
        person.lastName = "laowang"
        println("名字是：${person.lastName}")

        person.no = 200;
        println("no的大小：${person.no}")
        //因为height的set方法设置为private，所以不能赋值，但是可以访问get
        //person.height=333.3f
        println("高度=${person.height}")
    }

    fun runoobTest() {
        //这里调用的是次构造函数
        var runoob: Runoob = Runoob("菜鸟教程",1000)
    }
}
