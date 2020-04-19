package com.management.wuye.bean;

import java.util.Date;

public class Buildings {
    private int bid;
    private String bname;
    private Date createTime;
    private Date endTime;
    private int bHouseSum;
    private int bPersonHSum;
    private String bug;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getbHouseSum() {
        return bHouseSum;
    }

    public void setbHouseSum(int bHouseSum) {
        this.bHouseSum = bHouseSum;
    }

    public int getbPersonHSum() {
        return bPersonHSum;
    }

    public void setbPersonHSum(int bPersonHSum) {
        this.bPersonHSum = bPersonHSum;
    }

    public String getBug() {
        return bug;
    }

    public void setBug(String bug) {
        this.bug = bug;
    }

    @Override
    public String toString() {
        return "Buildings{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", createTime=" + createTime +
                ", endTime=" + endTime +
                ", bHouseSum=" + bHouseSum +
                ", bPersonHSum=" + bPersonHSum +
                ", bug='" + bug + '\'' +
                '}';
    }
}
