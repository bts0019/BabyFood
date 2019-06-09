package com.qfedu.babyfood.vo;

import lombok.Data;

public class R {
    private int code;
    private String msg;
    private Object data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public static R setOK(){
        R r = new R();
        r.setCode(1);
        r.setMsg("OK");
        r.setData(null);
        return r;
    }
    public static R setERROR(){
        R r = new R();
        r.setCode(0);
        r.setMsg("ERROR");
        r.setData(null);
        return r;
    }
    public static R setERROR(String msg,Object data){
        R r = new R();
        r.setCode(0);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    public static R setOK(String msg,Object data){
        R r = new R();
        r.setCode(1);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}
