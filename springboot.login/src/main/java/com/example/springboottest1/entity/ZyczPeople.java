package com.example.springboottest1.entity;

import java.util.Date;

/**
 * 类ZyczPeople的实现描述：TODO 类实现描述 
 * @author DELL 2019/9/6 9:47
 */
public class ZyczPeople {

    private String id;
    private String pid;
    private Date   whenRegistered;
    private byte[] image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Date getWhenRegistered() {
        return whenRegistered;
    }

    public void setWhenRegistered(Date whenRegistered) {
        this.whenRegistered = whenRegistered;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
