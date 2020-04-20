package com.management.wuye.bean;

import java.util.Date;

public class Repair {
    private int repairId;
    private String repairInfo;
    private Date repairTime;
    private String devicename;
    private String username;
    private String houseid;
    private String phone;
    private Boolean status;

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getRepairInfo() {
        return repairInfo;
    }

    public void setRepairInfo(String repairInfo) {
        this.repairInfo = repairInfo;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId=" + repairId +
                ", repairInfo='" + repairInfo + '\'' +
                ", repairTime=" + repairTime +
                ", devicename='" + devicename + '\'' +
                ", username='" + username + '\'' +
                ", houseid='" + houseid + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
