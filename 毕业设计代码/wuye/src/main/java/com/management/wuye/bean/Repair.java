package com.management.wuye.bean;

import java.util.Date;

public class Repair {
    private int id;
    private String detail;
    private Date time;
    private String userid;
    private int type;
    private boolean status;
    private String userName;
    private String telephone;
    private String hname;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", time=" + time +
                ", userid='" + userid + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", hname='" + hname + '\'' +
                '}';
    }
}
