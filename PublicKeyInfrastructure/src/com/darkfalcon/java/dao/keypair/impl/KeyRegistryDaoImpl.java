/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao.keypair.impl;

import com.darkfalcon.java.dao.AbstractDAOBase;
import com.darkfalcon.java.dao.keypair.KeyRegistryDao;
import com.darkfalcon.java.datasource.DatasourceLocator;
import com.darkfalcon.java.entity.server.KeyRegistry;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;

/**
 *
 * @author Darkfalcon
 */
public class KeyRegistryDaoImpl extends AbstractDAOBase<KeyRegistry> implements KeyRegistryDao {

    private static final String FIND_SERVER_BY_NAME = "select * from keystorage where serverId = ?";

    @Override
    public KeyRegistry findKeyRegistryByServerId(String serverId) throws SQLException {
        KeyRegistry keyRegistry = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_SERVER_BY_NAME);
            preparedStatement.setString(1, serverId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                keyRegistry = new KeyRegistry();
                keyRegistry.setId(resultSet.getLong(1));
                keyRegistry.setServerId(resultSet.getString(2));

                byte[] encodedPublicKey = resultSet.getBytes(3);
                byte[] encodedPrivateKey = resultSet.getBytes(4);

                KeyPair keyPair = new KeyPair(AsymmetricKeyUtil.decodePublicKey(encodedPublicKey, AsymmetricKeyUtil.ALGORITHM_RSA),
                        AsymmetricKeyUtil.decodePrivateKey(encodedPrivateKey, AsymmetricKeyUtil.ALGORITHM_RSA));

                keyRegistry.setKeyPair(keyPair);

                keyRegistry.setModDate(resultSet.getDate(5));
                keyRegistry.setRecDate(resultSet.getDate(6));
            }
        } catch (ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(KeyRegistryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, resultSet, preparedStatement);
        }
        return keyRegistry;
    }

    private static final String FIND_KEY_REGISTRIES = "select * from keystorage";

    @Override
    public List<KeyRegistry> getKeyRegistries() throws SQLException {

        List<KeyRegistry> registries = new ArrayList<KeyRegistry>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_KEY_REGISTRIES);
            resultSet = preparedStatement.executeQuery();

            KeyRegistry keyRegistry;

            while (resultSet.next()) {
                keyRegistry = new KeyRegistry();
                keyRegistry.setId(resultSet.getLong(1));
                keyRegistry.setServerId(resultSet.getString(2));

                byte[] encodedPublicKey = resultSet.getBytes(3);
                byte[] encodedPrivateKey = resultSet.getBytes(4);

                KeyPair keyPair = new KeyPair(AsymmetricKeyUtil.decodePublicKey(encodedPublicKey, AsymmetricKeyUtil.ALGORITHM_RSA),
                        AsymmetricKeyUtil.decodePrivateKey(encodedPrivateKey, AsymmetricKeyUtil.ALGORITHM_RSA));

                keyRegistry.setKeyPair(keyPair);

                keyRegistry.setModDate(resultSet.getDate(5));
                keyRegistry.setRecDate(resultSet.getDate(6));
                registries.add(keyRegistry);
            }
        } catch (ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(KeyRegistryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, resultSet, preparedStatement);
        }
        return registries;
    }

    private static final String INSERT_KEY_REGISTRY = "insert into keystorage (serverId, public, private, rec_date, mod_date) values(?, ?, ?, ?, ?)";

    @Override
    protected PreparedStatement getInsertStatement(Connection connection, KeyRegistry entity) throws SQLException {

        PreparedStatement preparedStatement = null;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_KEY_REGISTRY);
            preparedStatement.setString(1, entity.getServerId());
            preparedStatement.setBytes(2, AsymmetricKeyUtil.encodePublicKey(entity.getKeyPair().getPublic()));
            preparedStatement.setBytes(3, AsymmetricKeyUtil.encodePrivateKey(entity.getKeyPair().getPrivate()));
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyRegistryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStatement;
    }
    
    private static final String DELETE_KEY_REGISTRY = "delete from keystorage where id = ? ";

    @Override
    protected PreparedStatement getDeleteStatement(Connection connection, Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_KEY_REGISTRY);
            preparedStatement.setLong(1, id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KeyRegistryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, KeyRegistry entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
