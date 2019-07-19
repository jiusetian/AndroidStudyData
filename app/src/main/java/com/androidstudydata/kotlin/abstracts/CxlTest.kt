package com.androidstudydata.kotlin.abstracts

/**
 * Author：Alex
 * Date：2019/7/19
 * Note：kotlin抽象类测试
 */
class CxlTest {

    private lateinit var cxla: CxlA<String>

    fun test() {

        cxla = object : CxlA<String>(3) {
            override fun a() {

            }

        }
    }


}