package com.iqoverflow.lostandfound.domain;

public class User {
    private String uID ;
    private String userName;
    private String contact;

    private Integer sex;

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
        return contact;
    }

    public void setWxAccount(String wxAccount) {
        this.contact = wxAccount;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uID='" + uID + '\'' +
                ", userName='" + userName + '\'' +
                ", wxAccount='" + contact + '\'' +
                ", sex=" + sex +
                '}';
    }
}
