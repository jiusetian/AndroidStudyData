package com.androidstudydata.eventbus


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.EventBusTest
import com.androidstudydata.R
import kotlinx.android.synthetic.main.fragment_event_bus.*
import org.greenrobot.eventbus.EventBus
import kotlin.concurrent.thread


class EventBusFragment : Fragment() {

    companion object{
        const val EVENT="事件"
    }
    lateinit var eventBusTest:EventBusTest

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_bus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thread {
            send_btn.setOnClickListener{
                eventBusTest= EventBusTest()
                EventBus.getDefault().post(MyEvent())
            }
        }
    }

}
