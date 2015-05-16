/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.dao;

import com.darkfalcon.java.entity.BaseEntity;
import java.sql.SQLException;

/**
 *
 * @author Darkfalcon
 */
public interface DAOBase<T extends BaseEntity> {

    void insert(T entity) throws SQLException;

    void delete(Long id) throws SQLException;

    void update(T entity) throws SQLException;

}
