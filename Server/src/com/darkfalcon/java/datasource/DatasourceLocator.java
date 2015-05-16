/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
public class DatasourceLocator {

    private static final String CONNECTION_NAME = "jdbc:mysql://127.0.0.1:3306/chat";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "<int08lnxdx>";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(CONNECTION_NAME, USER_NAME, PASSWORD);
    }
    
    public static void closeConnection(Connection connection, ResultSet resultSet,PreparedStatement preparedStatement) {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatasourceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            resultSet.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatasourceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            preparedStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatasourceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
