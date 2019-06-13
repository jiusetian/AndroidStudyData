package com.androidstudydata.kotlin.aboutClass

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：
 */
class Runoob(name: String) {

    var url: String = "www.dddd"
    var contury: String = "china"
    //将主构造器的参数值赋予name
    var name = name

    //次构造函数,要直接或间接代理主构造函数，同一个类中代理主构造函数用this
    constructor(name: String, alex: Int) : this(name) {
        println("alex的排名：$alex")
    }

    /**
     * 另外一个次构造函数
     */
    constructor(name: String,alex: Int, weigth:Int) : this(name,alex) {

    }


    init {
        println("初始化了网站名$name")
    }

    fun printTest() {
        println("我是类函数")
    }

    /**
     * 测试函数
     */
    fun main(){
        //其实Runoob有两个构造函数，一个是主构造函数，一个是次构造函数
        val runoob=Runoob("我的名字",3333);
    }

}
