/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.entity.server;

import com.darkfalcon.java.entity.BaseEntity;
import java.security.KeyPair;

/**
 *
 * @author Darkfalcon
 */
public class KeyRegistry extends BaseEntity {

    private String serverId;
    private KeyPair keyPair;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public void setKeyPair(KeyPair keyPair) {
        this.keyPair = keyPair;
    }
}
