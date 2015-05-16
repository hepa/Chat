/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.services;

/**
 *
 * @author Darkfalcon
 */
public class ServiceProvider {
    
    public static SecurityService getSecurityService() {
        return new SecurityServiceImpl();
    }
}
