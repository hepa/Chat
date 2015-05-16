/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao.keypair;

import com.darkfalcon.java.dao.DAOBase;
import com.darkfalcon.java.entity.server.KeyRegistry;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Darkfalcon
 */
public interface KeyRegistryDao extends DAOBase<KeyRegistry> {

    KeyRegistry findKeyRegistryByServerId(String serverId) throws SQLException;

    List<KeyRegistry> getKeyRegistries() throws SQLException;
}
