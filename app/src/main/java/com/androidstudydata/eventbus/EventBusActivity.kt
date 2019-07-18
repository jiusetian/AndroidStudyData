package com.androidstudydata.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.R

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)

        val ft=supportFragmentManager.beginTransaction()
        var fragment=EventBusFragment()
        ft.add(R.id.fragment_container,fragment).show(fragment).commit()
    }
}
