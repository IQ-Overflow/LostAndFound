package com.iqoverflow.lostandfound.domain;

public class Reason {
    private boolean flag;
    private String ID;
    private String Message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "Reason{" +
                "flag=" + flag +
                ", ID='" + ID + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}
