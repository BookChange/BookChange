package com.app.bookchange.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class Location extends BmobObject {
    private String accountId;
    private String location;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
