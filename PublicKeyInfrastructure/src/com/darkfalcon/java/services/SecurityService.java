/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.services;

import com.darkfalcon.java.entity.server.KeyRegistry;
import java.util.List;

/**
 *
 * @author Darkfalcon
 */
public interface SecurityService {

    KeyRegistry getKeyRegistryByServerId(String serverId) throws RuntimeException;
    
    boolean createKeyRegistry(KeyRegistry keyRegistry) throws RuntimeException;
    
    List<KeyRegistry> getKeyRegistries() throws RuntimeException;
    
    boolean deleteKeyRegistry(KeyRegistry keyRegistry) throws RuntimeException;
}
