package com.gdin.analyse.entity;

import android.support.annotation.Nullable;

public class HttpResult<T> {

    private int code;
    private String message;

    //返回的数据
    @Nullable
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("code=" + code + " message=" + message);
        if (null != data) {
            sb.append(" data:" + data.toString());
        }else{
            sb.append(" data: null");

        }
        return sb.toString();
    }
}
