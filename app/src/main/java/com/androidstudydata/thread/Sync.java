package com.androidstudydata.thread;

/**
 * Created by XR_liu on 2018/11/27.
 */
public class Sync {

    private static int i = 0;

    private void add() {
        synchronized (this) {
            i++;
        }

    }
}
