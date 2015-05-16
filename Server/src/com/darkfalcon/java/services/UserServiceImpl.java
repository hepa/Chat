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
    public UserVO getUserByName(String name) {
        UserVO userVO = new UserVO();
        try {
            UserDao userDao = (UserDao) GenericDaoFactory.getDao(UserDaoImpl.class);
            User user = userDao.findUserByName(name);
            userVO = Dozer.userToUserVO(user);
        } catch (InstantiationException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userVO;
    }

    @Override
    public List<UserVO> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
