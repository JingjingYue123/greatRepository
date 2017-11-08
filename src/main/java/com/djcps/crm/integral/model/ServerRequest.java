package com.djcps.crm.integral.model;

/**
 * Created by TruthBean on 2017-08-09 14:19.
 */
public class ServerRequest<T> {
    private String msg;

    private int code;

    private boolean success;

    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "{" + "\"msg\":\'" + msg + '\'' + "," + "\"code\":" + code + "," + "\"success\":" + success + "," + "\"data\":" + data + '}';
    }
}
