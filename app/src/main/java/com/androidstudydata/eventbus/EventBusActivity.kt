package com.androidstudydata.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.EventLib
import com.androidstudydata.KLogUtil
import com.androidstudydata.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)

        val ft = supportFragmentManager.beginTransaction()
        var fragment = EventBusFragment()
        ft.add(R.id.fragment_container, fragment).show(fragment).commit()

        EventBus.getDefault().register(this)
    }

    @Subscribe
    fun onEvent(event: EventLib) {
        KLogUtil.i("接收到消息")
    }


}
