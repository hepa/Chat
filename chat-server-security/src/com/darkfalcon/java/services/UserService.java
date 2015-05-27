/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.services;

import com.darkfalcon.java.vo.user.UserVO;
import java.util.List;

/**
 *
 * @author Darkfalcon
 */
public interface UserService {
    
    List<UserVO> getUsers();
    
    UserVO getUserByName(String username);
    
    UserVO createUser(String username, String password);
    
    boolean deleteUserByName(String username);
    
    boolean validateUser(String username, byte[] password);
}
