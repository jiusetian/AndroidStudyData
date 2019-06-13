package com.androidstudydata.kotlin.aboutClass

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：测试改变git用户
 */
class Person {

    var lastName: String = "xingrong"
        get() = field.toUpperCase(); //重写了get方法
        set

    var no: Int = 100
        get() = field //后端变量
        //重写了set方法
        set(value) {
            field = if (value < 10) {
                value
            } else {
                -1
            }
        }

    var height: Float = 112.34f
        //将set设置为私有
        private set

    //成员方法
     fun method(){

    }

}

/**
 * 添加了这个类之后，就变成了kotlin文件了
 */
class Person3{

}