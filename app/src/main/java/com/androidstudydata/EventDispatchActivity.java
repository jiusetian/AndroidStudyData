package com.androidstudydata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * 事件分发机制相关测试
 * 结论：
 *  1.ViewGroup在分发事件的时候， 首先会遍历它所有的子view，看有没有接盘侠，如果有一个子view消费了事件，
 *  那么就不再遍历了，那么这个dispatchTouchEvent方法就会返回true。
 *
 *  2.如果当前ViewGroup所有的子view都没有消费事件，那么就会调用这个ViewGroup的父view即view类的dispatchTouchEvent
 *  方法，在这个方法里首先判断有没有touchListener去处理事件，然后才调用onTouchEvent方法
 *  看有没有onClickListener去处理事件，根据处理结果看是否返回true还是false
 *
 *  3.注意区分ViewGroup的dispatchTouchEvent方法和view的dispatchTouchEvent方法，ViewGroup的此方法主要
 *  作用是用来分发事件给他的子view，本身并不是用来消费事件，只有当没有子view消费事件的时候，ViewGroup就
 *  会调用继承的view的dispatchTouchEvent方法，只有view类才有onTouchEvent方法，view的dispatchTouchEvent
 *  方法不是用来分发事件的，因为view没有子view了，view根据自己的实际情况决定是否消费事件
 *
 *  4.如果一个ViewGroup拦截了一个事件，那么这个事件就不会往下传递了，而是调用ViewGroup的onTouchEvent方法，所以在这个方法里可以
 *  获取到事件，比如我们可以设计在什么情况下需要拦截事件，就可以重写ViewGroup的onInterceptTouchEvent方法去决定是否拦截某个事件，
 *  拦截了以后在onTouchEvent方法中去处理事件
 *
 *  5.一个事件传递到ViewGroup的时候，是通过dispatchTouchEvent方法来传递的，首先会调用onInterceptTouchEvent方法判断是否拦截当前
 *  事件，如果不拦截事件，就会去循环遍历ViewGroup的子view，从最后一个添加到ViewGroup的子view开始，看子view是否要消费事件，如果
 *  子view消费了事件，那么dispatchTouchEvent方法就会返回true代表这个事件消费了所以分发结束了，但是如果如果没有子view消费事件呢，
 *  就会调用ViewGroup的父类view的dispatchTouchEvent方法，将事件交给view处理，view的dispatchTouchEvent方法和ViewGroup的
 *  dispatchTouchEvent方法处理事件的方式不一样，view接收到事件以后，会根据view的相关设置，先判断有没有ontouchlistener，有的话
 *  就调用处理，如果消费了事件就返回true，否则调用onTouchEvent方法，在onTouchEvent方法中主要看onclicklistener设置，判断要不要
 *  消费事件。所以说当ViewGroup没有子view消费事件的时候，就会将事件传递给其父类的dispatchTouchEvent方法处理。如果ViewGroup有子
 *  view消费事件，而且这个子view不是ViewGroup就是一个view，那么处理逻辑跟ViewGroup父类view处理事件的逻辑是一样的。
 */
public class EventDispatchActivity extends AppCompatActivity {

    String TAG="tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        ViewServer.get(this).addWindow(this);
        
        findViewById(R.id.parent_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 父view接收了点击事件");
            }
        });

//        findViewById(R.id.parent_view).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d(TAG, "onTouch: 调用了父view的onTouch方法");
//                return true;
//            }
//        });


//        findViewById(R.id.child_view).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: 子view接收了点击事件");
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewServer.get(this).setFocusedWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }
}
