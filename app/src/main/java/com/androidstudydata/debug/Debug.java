package com.androidstudydata.debug;

import com.androidstudydata.LogUtils;

/**
 * Created by Administrator on 2019/1/2.
 */

public class Debug {

    private int i = 0;

    //开始调试
    public void startDebug() {
        add();
    }

    private void add() {
        for (; i <= 99; i++) {
            LogUtils.d("add方法当前数据=" + i);
        }
    }

    private void sub() {
        for (; i >= 0; i--) {
            LogUtils.d("sub方法当前数据=" + i);
        }
    }
}
