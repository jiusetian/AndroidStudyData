package com.androidstudydata.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidstudydata.LogUtils
import com.androidstudydata.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 结论：
 * 1.eventbus正常情况下，在那个线程发射的消息，接收消息就在哪个线程，但是如果在接收方法中设置了threadMode = ThreadMode.MAIN，那么
 * 不管在那个线程发送的消息，接收消息都在主线程
 * 2.通过注解接收消息优先级的高低，可以在高优先级的方法中接收到消息并对消息进行修改，那么低优先级接收到的将是修改过的消息
 */
class EventBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)

        val ft = supportFragmentManager.beginTransaction()
        var fragment = EventBusFragment()
        ft.add(R.id.fragment_container, fragment).show(fragment).commit()

        EventBus.getDefault().register(this)
    }

    @Subscribe(priority = 1,threadMode = ThreadMode.MAIN)
    fun onEvent(event: MyEvent) {
        LogUtils.d("接收线程："+Thread.currentThread().name)
        LogUtils.d("低优先级事件="+event.s+"...."+Thread.currentThread().name)
    }

    @Subscribe(priority = 2)
    fun onEvent2(event: MyEvent){
        LogUtils.d("高优先级事件")
        event.s="修改了事件"
    }

}
