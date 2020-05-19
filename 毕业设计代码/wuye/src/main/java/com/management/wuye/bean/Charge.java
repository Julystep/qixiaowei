package com.management.wuye.bean;

import java.util.Date;

public class Charge {
    private int id;
    private String waterFee;
    private String electricityFee;
    private String propertyCosts;
    private String userId;
    private int state;
    private Date date;
    private Date subtime;

    public Date getSubtime() {
        return subtime;
    }

    public void setSubtime(Date subtime) {
        this.subtime = subtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaterFee() {
        return waterFee;
    }

    public void setWaterFee(String waterFee) {
        this.waterFee = waterFee;
    }

    public String getElectricityFee() {
        return electricityFee;
    }

    public void setElectricityFee(String electricityFee) {
        this.electricityFee = electricityFee;
    }

    public String getPropertyCosts() {
        return propertyCosts;
    }

    public void setPropertyCosts(String propertyCosts) {
        this.propertyCosts = propertyCosts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
