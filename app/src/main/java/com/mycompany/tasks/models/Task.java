package com.mycompany.tasks.models;

import java.util.Date;

public class Task {
    private int id;
    private String name;
    private String desc;
    private Date date;
    private User creator;
    private boolean done;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Date getDate() {
        return date;
    }

    public User getCreator() {
        return creator;
    }

    public boolean isDone() {
        return done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
