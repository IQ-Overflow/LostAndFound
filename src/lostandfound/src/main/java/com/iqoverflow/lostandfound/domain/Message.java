package com.iqoverflow.lostandfound.domain;

public class Message {
    private boolean flag;
    private Object msg;

    public Message() {
    }

    public Message(boolean flag, Object msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
