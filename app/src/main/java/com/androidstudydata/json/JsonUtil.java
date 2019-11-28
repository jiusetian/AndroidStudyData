package com.androidstudydata.json;

import com.google.gson.Gson;

/**
 * Author：Alex
 * Date：2019/5/31
 * Note：
 */
public class JsonUtil {

    String data="{\"ID\":1,\"Name\":\"hpy\",\"Age\":18}";

    public static String histroy011="{\"success\":true,\"data\":[],\"message\":null} ";

    public static String histroy="{\"success\":true,\"data\":[{\"id\":\"2481632389136465451\",\"moduleId\":\"869696042451109\",\"createDateTime\":\"2019-06-26 13:08:01\",\"posslnum\":1,\"latitude\":25.63,\"longitude\":28.11,\"speed\":1,\"cog\":0,\"pdop\":1,\"hdop\":0},{\"id\":\"2481632389136465452\",\"moduleId\":\"869696042451109\",\"createDateTime\":\"2019-06-26 13:10:27\",\"posslnum\":0,\"latitude\":1.0,\"longitude\":2.0,\"speed\":3,\"cog\":4,\"pdop\":5,\"hdop\":6}],\"message\":null} ";

    /**
     *
     * @param str
     * @param clss
     * @return
     */
    public static Object jsonStringToObject(String str, Class<?> clss) {
        Gson gson = new Gson();
        return gson.fromJson(str, clss);
    }

    public static String objectToJsonString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }



}
