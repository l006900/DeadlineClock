package com.example.deadlineclock.bean;

public class DateBean {

    private Integer id;
    private String title;
    private String startdate;
    private String starttime;
    private String enddate;
    private String endtime;

    public DateBean(Integer id, String title, String startdate, String starttime, String enddate, String endtime) {
        this.id = id;
        this.title = title;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startdate='" + startdate + '\'' +
                ", starttime='" + starttime + '\'' +
                ", enddate='" + enddate + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
