package com.android;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Author：Alex
 * Date：2019/7/23
 * Note：
 */
public class EventBusTest {

    public EventBusTest(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(EventLib event){
        Log.d("tag", "onEvent: 测试接收到信息");
    }

    public void pushEvent(){
        EventBus.getDefault().post(new EventLib());
    }
}
