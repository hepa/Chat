/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao.user;

import com.darkfalcon.java.dao.DAOBase;
import com.darkfalcon.java.entity.user.User;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Darkfalcon
 */
public interface UserDao extends DAOBase<User> {

    User findUserByName(String username) throws SQLException;

    List<User> getUsers() throws SQLException;

    void deleteUserByName(String username) throws SQLException;
}
