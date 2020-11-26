package com.iqoverflow.lostandfound.domain;

public class Reason {
    private String pID;//卡或物品的ID(stuID or oID)
    private String fID;//申请人的uID
    private String tID;//被申请人的uID
    private String message;//申请理由
    private Integer state;//申请状态.0：进行中  1：已同意  2：已拒绝
    private Boolean type;//物品类型，true 为卡，false 为物品

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    private Object object;//物品


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
