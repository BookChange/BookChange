package com.app.bookchange.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class MyBook extends BmobObject {
   private String accountId;
   private String bookname;
   private String bookstyle;
   private String bookautor;
   private boolean sign;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookstyle() {
        return bookstyle;
    }

    public void setBookstyle(String bookstyle) {
        this.bookstyle = bookstyle;
    }

    public String getBookautor() {
        return bookautor;
    }

    public void setBookautor(String bookautor) {
        this.bookautor = bookautor;
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }
}
