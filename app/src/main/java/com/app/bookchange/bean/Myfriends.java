package com.app.bookchange.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class Myfriends extends BmobObject {

    private String account;
    private String location;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
