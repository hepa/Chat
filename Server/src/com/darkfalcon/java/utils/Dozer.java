/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.utils;

import com.darkfalcon.java.entity.user.User;
import com.darkfalcon.java.vo.user.UserVO;

/**
 *
 * @author Darkfalcon
 */
public class Dozer {

    public static UserVO userToUserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUsername(user.getUsername());
        userVO.setPassword(user.getPassword());
        return userVO;
    
    }
}
