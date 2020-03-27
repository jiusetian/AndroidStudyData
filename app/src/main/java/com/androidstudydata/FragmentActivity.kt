package com.androidstudydata

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_fragment.*

/**
 * 测试fragment
 */
class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val blankFragment1 = BlankFragment()
        supportFragmentManager.beginTransaction().add(R.id.frg1, blankFragment1).commit()

        val blankFragment2 = BlankFragment()
        supportFragmentManager.beginTransaction().add(R.id.frg2, blankFragment2).commit()

        //supportFragmentManager.beginTransaction().hide(blankFragment2)
        frg2.visibility = View.GONE
    }
}
