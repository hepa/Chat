/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao;

import com.darkfalcon.java.datasource.DatasourceLocator;
import com.darkfalcon.java.entity.BaseEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
public abstract class AbstractDAOBase<T extends BaseEntity> implements
        DAOBase<T> {

    @Override
    public void insert(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = getInsertStatement(connection, entity);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, null, preparedStatement);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = getDeleteStatement(connection, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, null, preparedStatement);
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatasourceLocator.getConnection();
            preparedStatement = getUpdateStatement(connection, entity);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AbstractDAOBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DatasourceLocator.closeConnection(connection, null, preparedStatement);
        }
    }

    protected abstract PreparedStatement getInsertStatement(
            Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement getDeleteStatement(
            Connection connection, Long id) throws SQLException;

    protected abstract PreparedStatement getUpdateStatement(
            Connection connection, T entity) throws SQLException;
}
