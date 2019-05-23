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
        var runoob: Runoob = Runoob("菜鸟教程", 1000)
        //调用了扩展函数
        var p: Person = Person();
        p.Print()
    }

    //创建Person类的扩展函数
    fun Person.Print() {
        println("我是person的扩展函数")
    }

    //为MutableList 扩展一个交换函数
    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val temp = this[index1] //this关键字代表接收者对象
        this[index1] = this[index2]
        this[index2] = temp
    }


}
