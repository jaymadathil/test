/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myroot.driverhistory.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Jay Madathil
 */
public class DriverDTO implements Serializable {

    private String driverName;

    public DriverDTO(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.driverName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DriverDTO other = (DriverDTO) obj;
        if (!Objects.equals(this.driverName, other.driverName)) {
            return false;
        }
        return true;
    }

}
