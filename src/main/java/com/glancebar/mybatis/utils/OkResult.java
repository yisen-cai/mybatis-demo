package com.glancebar.mybatis.utils;

/**
 * @author YISHEN CAI
 */
public class OkResult {

    private String msg;


    public OkResult(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
