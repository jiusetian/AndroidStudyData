package com.androidstudydata.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.LogUtils
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

    @Subscribe(priority = 1)
    fun onEvent(event: MyEvent) {
        LogUtils.d("低优先级事件="+event.s+"...."+Thread.currentThread().name)
    }

    @Subscribe(priority = 2)
    fun onEvent2(event: MyEvent){
        LogUtils.d("高优先级事件")
        event.s="修改了事件"
    }

}
