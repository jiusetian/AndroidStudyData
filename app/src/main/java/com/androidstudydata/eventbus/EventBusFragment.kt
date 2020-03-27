package com.androidstudydata.eventbus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.EventBusTest
import com.androidstudydata.LogUtils
import com.androidstudydata.R
import org.greenrobot.eventbus.EventBus
import kotlin.concurrent.thread


class EventBusFragment : Fragment() {

    companion object {
        const val EVENT = "事件"
    }

    lateinit var eventBusTest: EventBusTest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_bus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thread(start = true) {
            LogUtils.d("发射线程：" + Thread.currentThread().name)
            eventBusTest = EventBusTest()
            EventBus.getDefault().post(MyEvent())
        }
    }

}
