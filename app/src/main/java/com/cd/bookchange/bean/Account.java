package com.cd.bookchange.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Account extends BmobObject{

    private String account;
    private String password;
    private String id;





    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
