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
        var v="内部类成员属性"
        fun foo() = bar
        fun innerTest() {
            //this@Outer是指外部类Outer对象，这个是为了消除歧义，如果
            var o: Outer = this@Outer;
            println("内部类引用外部类成员：${o.v}")
            //虽然可以直接使用外部类的成员属性，但是如果内部类也有相同名字成员变量，那么调用内部类的优先，所以要先获取到外部类的实例，再调用就不会有歧义了
            println("${v}")
        }
    }

}