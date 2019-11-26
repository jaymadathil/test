/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myroot.driverhistory.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Jay Madathil
 */
public class DriverHistoryDTO implements Serializable {

    private DriverDTO driverName;
    private BigDecimal miles;
    private BigDecimal avgSpeed;

    public DriverHistoryDTO() {
    }

    public DriverHistoryDTO(DriverDTO driverName, BigDecimal miles, BigDecimal avgSpeed) {
        this.driverName = driverName;
        this.miles = miles;
        this.avgSpeed = avgSpeed;
    }

    public DriverDTO getDriverName() {
        return driverName;
    }

    public void setDriverName(DriverDTO driverName) {
        this.driverName = driverName;
    }

    public BigDecimal getMiles() {
        return miles;
    }

    public void setMiles(BigDecimal miles) {
        this.miles = miles;
    }

    public BigDecimal getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(BigDecimal avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

}
