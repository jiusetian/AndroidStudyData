package com.androidstudydata.kotlin.aboutClass

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：
 */
class Person2 {

    //非空属性必须在定义的时候初始化，但是可以延迟初始化
    lateinit var person:Person

    fun setUp(){
        person= Person()
    }

    fun test(){
        person.method()
    }

    //扩展函数
}