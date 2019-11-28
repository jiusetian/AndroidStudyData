package com.androidstudydata.commontest;

import com.androidstudydata.KLogUtil;
import com.androidstudydata.LogUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Author：Alex
 * Date：2019/7/24
 * Note：一些普通的测试
 */
public class ComTest {

    /**
     * 1.字符串中没有要分割的符号，就会返回源字符串
     * 2.如果分割符在最前面，会分割一个空字符串出来，如果分隔符在最后面，则忽略而不会分割一个空字符串出来
     */
    public static void splitTest() {
        String s = ",dfajsogf,asd,daogho,";
        List<String> list = Arrays.asList(s.split(","));
        for (int i = 0; i < list.size(); i++) {
            LogUtils.d("分割的->" + list.get(i));
        }
    }

    /**
     * 位移测试，比如0001左移三位，变成1000，二进制1000就是8
     * 其实1<<3，相当于2的3次方
     */
    public static void weiyiTest() {
        int i = 1 << 3;
        KLogUtil.INSTANCE.d("位移=" + i);

    }
}
