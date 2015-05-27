/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.services;

import com.darkfalcon.java.dao.GenericDaoFactory;
import com.darkfalcon.java.dao.user.UserDao;
import com.darkfalcon.java.dao.user.impl.UserDaoImpl;
import com.darkfalcon.java.entity.user.User;
import com.darkfalcon.java.messagediggest.MessageDigestUtil;
import com.darkfalcon.java.utils.Dozer;
import com.darkfalcon.java.vo.user.UserVO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkfalcon
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserVO getUserByName(String username) {
        UserVO userVO = new UserVO();
        try {
            UserDao userDao = (UserDao) GenericDaoFactory.getDao(UserDaoImpl.class);
            User user = userDao.findUserByName(username);
            userVO = Dozer.userToUserVO(user);
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userVO;
    }

    @Override
    public List<UserVO> getUsers() {
        try {
            UserDao userDao = (UserDao) GenericDaoFactory.getDao(UserDaoImpl.class);
            return Dozer.usersToUserVOs(userDao.getUsers());
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public UserVO createUser(String username, String password) {
        try {
            UserDao userDao = (UserDao) GenericDaoFactory.getDao(UserDaoImpl.class);
            byte[] digestPassword = MessageDigestUtil.getMessageDigest(password);
            User user = new User(username, MessageDigestUtil.getMessageDigest(password));
            userDao.insert(user);
            return new UserVO(username, digestPassword);
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean deleteUserByName(String username) {
        try {
            UserDao userDao = (UserDao) GenericDaoFactory.getDao(UserDaoImpl.class);
            userDao.deleteUserByName(username);
            return true;
        } catch (InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean validateUser(String username, byte[] password) {
        UserVO user = getUserByName(username);
        return MessageDigestUtil.isDigestEquals(password, user.getPassword());
    }
}
