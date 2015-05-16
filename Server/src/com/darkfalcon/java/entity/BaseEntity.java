/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Darkfalcon
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date recDate;
    private Date modDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRecDate() {
        return recDate;
    }

    public void setRecDate(Date recDate) {
        this.recDate = recDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
