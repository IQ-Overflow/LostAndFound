package com.iqoverflow.lostandfound.domain;

import java.sql.Timestamp;

public class Card {
    private String stuID;
    private String college;
    private String stuName;
    private String uID;
    private boolean flag;
    private Timestamp time;

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Card{" +
                "stuID='" + stuID + '\'' +
                ", college='" + college + '\'' +
                ", stuName='" + stuName + '\'' +
                ", uID='" + uID + '\'' +
                ", flag=" + flag +
                ", time=" + time +
                '}';
    }
}
