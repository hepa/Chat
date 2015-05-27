/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao.user.impl;

import com.darkfalcon.java.dao.AbstractDAOBase;
import com.darkfalcon.java.dao.user.UserDao;
import com.darkfalcon.java.datasource.DatasourceLocator;
import com.darkfalcon.java.entity.user.User;
import com.darkfalcon.java.keys.AsymmetricKeyUtil;
import com.darkfalcon.java.messagediggest.MessageDigestUtil;
import com.darkfalcon.java.services.UserServiceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
public class UserDaoImpl extends AbstractDAOBase<User> implements UserDao {

    private final String INSERT_USER = "INSERT INTO client (username, password, rec_date, mod_date) values (?,?,?,?)";
    
    @Override
    protected PreparedStatement getInsertStatement(Connection connection, User entity) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setBytes(2, entity.getPassword());
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return preparedStatement;
    }

    @Override
    protected PreparedStatement getDeleteStatement(Connection connection, Long id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement getUpdateStatement(Connection connection, User entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private final String FIND_USER_BY_NAME = "SELECT * FROM client WHERE username = ?;";

    @Override
    public User findUserByName(String username) throws SQLException {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getBytes(3));
                user.setModDate(resultSet.getDate(4));
                user.setRecDate(resultSet.getDate(5));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, resultSet, preparedStatement);
        }
        return user;
    }
    
    private final String FIND_ALL = "SELECT * FROM client";

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            
            User user;
            
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getBytes(3));
                user.setModDate(resultSet.getDate(4));
                user.setRecDate(resultSet.getDate(5));
                users.add(user);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, resultSet, preparedStatement);
        }
        return users;
    }

     private static final String DELETE_USER = "delete from client where username = ? ";
    
    @Override
    public void deleteUserByName(String username) throws SQLException {
        
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
