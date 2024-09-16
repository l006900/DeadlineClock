package com.example.deadlineclock.bean;

public class ListModel {

    private String list_title;
    private String list_time;

    public ListModel(String list_title, String list_time) {
        this.list_title = list_title;
        this.list_time = list_time;
    }

    public String getList_title() {
        return list_title;
    }

    public String getList_time() {
        return list_time;
    }
}
