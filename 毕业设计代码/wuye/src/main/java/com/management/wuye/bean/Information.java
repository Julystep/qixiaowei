package com.management.wuye.bean;

import java.util.Date;

public class Information {
    private int id;
    private String head;
    private String content;
    private Date infotime;
    private String userId;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInfotime() {
        return infotime;
    }

    public void setInfotime(Date infotime) {
        this.infotime = infotime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", head='" + head + '\'' +
                ", content='" + content + '\'' +
                ", infotime=" + infotime +
                ", userId='" + userId + '\'' +
                ", type=" + type +
                '}';
    }
}
