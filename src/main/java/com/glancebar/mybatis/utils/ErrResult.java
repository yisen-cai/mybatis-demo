package com.glancebar.mybatis.utils;

import java.util.List;

/**
 * @author YISHEN CAI
 */
public class ErrResult {

    private String msg;
    private int code;
    private List<String> details;


    public ErrResult(String msg, int code, List<String> details) {
        this.msg = msg;
        this.code = code;
        this.details = details;
    }

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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
