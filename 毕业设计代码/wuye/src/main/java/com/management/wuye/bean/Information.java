package com.management.wuye.bean;

import java.util.Date;

public class Information {
    private int id;
    private String head;
    private String content;
    private Date infotime;
    private String userId;
    private int type;
    private int value;
    private String chat;
    private String userName;
    private String avator;
    private Picture picture;
    private User user;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

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
