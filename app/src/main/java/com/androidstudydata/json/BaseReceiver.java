package com.androidstudydata.json;

/**
 * Created by XR_liu on 2018/10/24.
 * 请求返回数据的基类
 */
public class BaseReceiver<T> {

    private boolean success;
    private T data;
    private String message;

    @Override
    public String toString() {
        return "BaseMessage{" +
                "success=" + success +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
