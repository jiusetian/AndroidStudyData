package com.androidstudydata.handler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.KLogUtil
import com.androidstudydata.R
import java.lang.ref.WeakReference

/**
 * 测试handler内存泄漏
 */
class HandlerActivity : AppCompatActivity() {



    //handler测试
    private val handler = Handler {
        when (it.what) {
            10 -> {
                KLogUtil.i("处理消息")
            }
        }
        false
    }

    private val myHandler = MyHandler(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        //handler.sendEmptyMessage(10)
        myHandler.sendEmptyMessage(11)
    }




    /**
     * 自定义handler
     */
    private class MyHandler(val actWeakReference: WeakReference<HandlerActivity>) : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            //弱引用不为null才执行
            val act = actWeakReference.get()
            act?.let {
                when (msg?.what) {
                    11 -> KLogUtil.i("打印11")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
