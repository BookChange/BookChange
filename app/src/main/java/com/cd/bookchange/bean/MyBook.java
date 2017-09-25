package com.cd.bookchange.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class MyBook extends BmobObject {
    private Integer booknumber;
    private String bookkind;
    private String favbook;

    public Integer getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(Integer booknumber) {
        this.booknumber = booknumber;
    }

    public String getBookkind() {
        return bookkind;
    }

    public void setBookkind(String bookkind) {
        this.bookkind = bookkind;
    }

    public String getFavbook() {
        return favbook;
    }

    public void setFavbook(String favbook) {
        this.favbook = favbook;
    }


}
