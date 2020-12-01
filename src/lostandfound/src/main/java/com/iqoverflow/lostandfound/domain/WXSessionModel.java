package com.iqoverflow.lostandfound.domain;

public class WXSessionModel {
    private String session_key;
    private String openid;
    private String errcode;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return "WXSessionModel{" +
                "session_key='" + session_key + '\'' +
                ", openid='" + openid + '\'' +
                ", errcode='" + errcode + '\'' +
                '}';
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
