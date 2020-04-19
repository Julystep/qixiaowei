package com.management.wuye.bean;

public class Houses {
    private String hid;
    private String hname;
    private double hArea;
    private boolean status;
    private int bid;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public double gethArea() {
        return hArea;
    }

    public void sethArea(double hArea) {
        this.hArea = hArea;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "House{" +
                "hid='" + hid + '\'' +
                ", hname='" + hname + '\'' +
                ", hArea=" + hArea +
                ", status=" + status +
                ", bid=" + bid +
                '}';
    }
}
