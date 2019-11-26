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
public class TripDTO implements Serializable {

    private String tripId;
    private String tripName;
    private DriverDTO driver;
    private String startTime;
    private String endTime;
    private BigDecimal duration;
    private BigDecimal miles;

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getMiles() {
        return miles;
    }

    public void setMiles(BigDecimal miles) {
        this.miles = miles;
    }

}
