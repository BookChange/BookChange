package com.app.bookchange.bean;


import cn.bmob.v3.BmobObject;

public class LocationBean extends BmobObject{
    private String userid;
    private double lng,lat;
    private String myloctionmessage;
    private MyBook book;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public String getMyloctionmessage() {
        return myloctionmessage;
    }

    public void setMyloctionmessage(String myloctionmessage) {
        this.myloctionmessage = myloctionmessage;
    }

    public MyBook getBook() {
        return book;
    }

    public void setBook(MyBook book) {
        this.book = book;
    }
}
