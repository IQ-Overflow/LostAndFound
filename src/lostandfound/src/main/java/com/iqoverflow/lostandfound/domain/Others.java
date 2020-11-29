package com.iqoverflow.lostandfound.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public class Others {
    private int oID;
    private String title;
    private String content;
    private String pic;
    private String uID;
    private boolean flag;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
    private Integer state;//状态。 0：正在进行  1：已结束  2：已删除
    private User poster;//发布人
    private MultipartFile imgFile = null;//接收前端的图片文件
    private String contact = null;//联系方式
    private String imgStr = null;//返回前端的图片base64编码字符串

    // 数据库表里面没有这个字段
    //private String contact;

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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImgStr() {
        return imgStr;
    }

    public void setImgStr(String imgStr) {
        this.imgStr = imgStr;
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
                ", time=" + time +
                ", state=" + state +
                ", poster=" + poster +
                ", imgFile=" + imgFile +
                ", contact=" + contact +
                ", imgStr=" + imgStr +
                '}';
    }
}
