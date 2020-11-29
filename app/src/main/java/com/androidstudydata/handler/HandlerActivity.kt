package com.androidstudydata.handler

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.LogUtils
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
                LogUtils.i("处理消息")
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
     * 自定义handler，这里用到了弱引用，就是说如果当前activity的强引用已经不存在的时候，这个弱引用的对象会被回收
     * 如果handler不用静态内部类的话，在创建对象的时候就会变成当前activity的匿名内部类，所以它会持有外部类activity的对象，从而造成内存泄漏
     */
    private class MyHandler(val actWeakReference: WeakReference<HandlerActivity>) : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            //弱引用不为null才执行
            val act = actWeakReference.get()
            act?.let {
                when (msg?.what) {
                    11 -> LogUtils.i("打印11")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
