package com.androidstudydata.act

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.LogUtils
import com.androidstudydata.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        LogUtils.d("调用onCreate")

        btn.setOnClickListener {
            val dialog=DialogFragment()

            dialog.show(supportFragmentManager,"tag")

            //val dialog2=AlertDialog(this)
        }
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d("调用onstart")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d("调用onResume")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d("调用onpause")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d("调用onstop")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d("调用onDestroy")
    }
}
