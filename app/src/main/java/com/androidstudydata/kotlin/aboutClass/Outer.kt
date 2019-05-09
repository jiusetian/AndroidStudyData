package com.androidstudydata.kotlin.aboutClass

/**
 * Author：Alex
 * Date：2019/5/9
 * Note：
 */
class Outer { //外部类

    private var bar: Int = 33
    var v = "成员属性"
    inner class Inner {
        fun foo() = bar
        fun innerTest() {
            var o: Outer = this@Outer;
            println("内部类引用外部类成员：${o.v}")
        }
    }

}