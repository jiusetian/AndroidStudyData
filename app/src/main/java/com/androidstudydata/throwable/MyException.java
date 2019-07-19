package com.androidstudydata.throwable;

/**
 * Author：Alex
 * Date：2019/7/18
 * Note：
 */
public class MyException {

    public void exceptionTest() {

        String s = null;
        if (s == null) {
            try {
                throw new Exception("异常");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
