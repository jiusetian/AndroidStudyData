package com.androidstudydata.kotlin

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：
 */

interface A {
    //接口中已实现的方法
    fun ff() {
        println("A")
    }

    fun bar() //未实现方法，是抽象的
}

interface B {
    //接口中已实现的方法
    fun ff() {
        println("B")
    }

    fun bar() {
        println("bar_B")
    }
}

/**
 * 类C必须要实现接口的抽象方法
 */
class C : A {
    override fun bar() {
        println("bar_A")
    }
}

/**
 * 同时继承了两个接口，必须要实现两个接口中共同的方法，还有他们的抽象方法
 */
class jiekou : A, B {
    override fun ff() {

    }

    override fun bar() {

    }
}