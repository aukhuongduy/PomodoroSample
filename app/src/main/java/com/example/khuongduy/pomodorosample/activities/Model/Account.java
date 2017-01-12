package com.example.khuongduy.pomodorosample.activities.Model;

import java.util.Vector;

/**
 * Created by Khuong Duy on 1/12/2017.
 */

public class Account {
    public static final Vector<Account> instance = new Vector<>();
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
