/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.dao;

/**
 *
 * @author Darkfalcon
 */
public class GenericDaoFactory {
    
    public static Object getDao(Class clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
}
