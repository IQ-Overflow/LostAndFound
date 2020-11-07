package com.iqoverflow.lostandfound.domain;

import java.sql.Timestamp;

public class Others {
    private int oID;
    private String title;
    private String content;
    private String pic;
    private String uID;
    private boolean flag;
    private String contact;
    private Timestamp time;

    public int getoID() {
        return oID;
    }

    public void setoID(int oID) {
        this.oID = oID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Others{" +
                "oID=" + oID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", uID='" + uID + '\'' +
                ", flag=" + flag +
                ", contact='" + contact + '\'' +
                ", time=" + time +
                '}';
    }
}