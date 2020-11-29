package com.iqoverflow.lostandfound.domain;

public class Reason {
    private String pID;//卡或物品的ID(stuID or oID)
    private String fID;//申请人的uID
    private String tID;//被申请人的uID
    private String message;//申请理由
    private Integer state;//申请状态.0：进行中  1：已同意  2：已拒绝
    private Integer type;//物品类型，0 为卡，1 为物品
    private String msg;//申请的返回消息，若 state 为 1，返回联系方式。 若 state 为2，返回拒绝理由。
    private Object object;//物品
    private User reasonPoster; // 联系申请人;

    public User getReasonPoster() {
        return reasonPoster;
    }

    public void setReasonPoster(User reasonPoster) {
        this.reasonPoster = reasonPoster;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }




    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Reason{" +
                "pID='" + pID + '\'' +
                ", fID='" + fID + '\'' +
                ", tID='" + tID + '\'' +
                ", message='" + message + '\'' +
                ", state=" + state +
                '}';
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getfID() {
        return fID;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }

    public String gettID() {
        return tID;
    }

    public void settID(String tID) {
        this.tID = tID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
