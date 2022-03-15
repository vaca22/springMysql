package com.example.demo.requestback;

import com.google.gson.JsonObject;

public class RegisterReturn {
    int code;
    String msg;
    JsonObject data;

    public RegisterReturn(int code, String msg, JsonObject data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JsonObject all = new JsonObject();
        all.addProperty("code", code);
        all.addProperty("msg", msg);
        all.add("data", data);
        return all.toString();
    }
}
