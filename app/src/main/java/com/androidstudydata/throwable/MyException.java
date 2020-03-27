package com.androidstudydata.throwable;

/**
 * Author：Alex
 * Date：2019/7/18
 * Note：
 */
public class MyException {

    //受检查异常
    public void checkExceptionTest() {

        String s = null;
        if (s == null) {
            try {
                throw new Exception("非运行时异常");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //运行时异常，抛出时程序会崩溃，可以不用捕获
    public void runtimeExceptionTest(){
        throw new RuntimeException("运行时异常");
    }


}
