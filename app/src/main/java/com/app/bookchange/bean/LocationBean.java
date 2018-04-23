package com.app.bookchange.bean;

import com.baidu.mapapi.model.LatLng;

import java.io.Serializable;

public class LocationBean implements Serializable{
    private int id;
    private int userid;
    private double lng,lat;
    private LatLng latlng;
    private String myloctionmessage;

    private MyBook book;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
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
