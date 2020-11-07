package com.iqoverflow.lostandfound.domain;

public class User {
    private String uID ;
    private String userName;
    private String wxAccount;
    private String sex;

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxAccount() {
        return wxAccount;
    }

    public void setWxAccount(String wxAccount) {
        this.wxAccount = wxAccount;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uID='" + uID + '\'' +
                ", userName='" + userName + '\'' +
                ", wxAccount='" + wxAccount + '\'' +
                ", sex=" + sex +
                '}';
    }
}
