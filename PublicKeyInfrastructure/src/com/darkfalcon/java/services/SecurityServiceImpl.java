/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.services;

import com.darkfalcon.java.dao.GenericDaoFactory;
import com.darkfalcon.java.dao.keypair.KeyRegistryDao;
import com.darkfalcon.java.dao.keypair.impl.KeyRegistryDaoImpl;
import com.darkfalcon.java.entity.server.KeyRegistry;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
class SecurityServiceImpl implements SecurityService {

    @Override
    public KeyRegistry getKeyRegistryByServerId(String serverId) throws RuntimeException {

        KeyRegistry keyRegistry = null;

        try {
            KeyRegistryDao keyRegistryDao = (KeyRegistryDao) GenericDaoFactory.getDao(KeyRegistryDaoImpl.class);
            keyRegistry = keyRegistryDao.findKeyRegistryByServerId(serverId);
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SecurityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

        return keyRegistry;
    }

    @Override
    public boolean createKeyRegistry(KeyRegistry keyRegistry) throws RuntimeException {
        try {
            KeyRegistryDao keyRegistryDao = (KeyRegistryDao) GenericDaoFactory.getDao(KeyRegistryDaoImpl.class);
            keyRegistryDao.insert(keyRegistry);
            return true;
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SecurityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<KeyRegistry> getKeyRegistries() throws RuntimeException {

        List<KeyRegistry> registries = null;

        try {
            KeyRegistryDao keyRegistryDao = (KeyRegistryDao) GenericDaoFactory.getDao(KeyRegistryDaoImpl.class);
            registries = keyRegistryDao.getKeyRegistries();
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SecurityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return registries;
    }

    @Override
    public boolean deleteKeyRegistry(KeyRegistry keyRegistry) throws RuntimeException {
        try {
            KeyRegistryDao keyRegistryDao = (KeyRegistryDao) GenericDaoFactory.getDao(KeyRegistryDaoImpl.class);
            keyRegistryDao.delete(keyRegistry.getId());
            return true;
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(SecurityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
