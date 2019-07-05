package com.androidstudydata.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.androidstudydata.LogUtils

/**
 * Author：Alex
 * Date：2019/6/21
 * Note：
 */
class ServiceTest :Service(){

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate() {
        super.onCreate()
        LogUtils.d("开启服务")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }
}