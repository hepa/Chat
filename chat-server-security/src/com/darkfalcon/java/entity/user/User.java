/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.entity.user;

import com.darkfalcon.java.entity.BaseEntity;

/**
 *
 * @author Darkfalcon
 */
public class User extends BaseEntity {

    private String username;
    private byte[] password;

    public User() {}

    public User(String username, byte[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }
}
