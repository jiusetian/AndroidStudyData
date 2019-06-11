package com.androidstudydata.json;

import com.google.gson.Gson;

/**
 * Author：Alex
 * Date：2019/5/31
 * Note：
 */
public class JsonUtil {

    String data="{\"ID\":1,\"Name\":\"hpy\",\"Age\":18}";

    public static Object jsonStringToObject(String str, Class<?> clss) {
        Gson gson = new Gson();
        return gson.fromJson(str, clss);
    }

    public static String objectToJsonString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

}
