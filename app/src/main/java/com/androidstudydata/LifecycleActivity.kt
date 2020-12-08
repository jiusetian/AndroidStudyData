package com.androidstudydata

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

/**
 * 生命周期测试activity
 */
class LifecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        LogUtils.e("onCreate")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.e("onStart")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.e("onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.e("onPause")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.e("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.e("onConfigurationChanged:${newConfig.orientation}")
    }

}