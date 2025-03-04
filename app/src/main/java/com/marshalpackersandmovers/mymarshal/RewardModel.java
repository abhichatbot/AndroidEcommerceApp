package com.marshalpackersandmovers.mymarshal;

import com.google.firebase.Timestamp;

import java.util.Date;

public class RewardModel {
    private String type;
    private String lowerLimit;
    private String upperLimit;
    private String discOramt;
    private String couponBody;
    private Date timestamp;
    private Boolean alreadyUsed;
    private String couponId;

    public RewardModel(String couponId,String type, String lowerLimit, String upperLimit, String discOramt, String couponBody, Date timestamp, Boolean alreadyUsed) {
        this.couponId = couponId;
        this.type = type;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.discOramt = discOramt;
        this.couponBody = couponBody;
        this.timestamp = timestamp;
        this.alreadyUsed =  alreadyUsed;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Boolean getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(Boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getDiscOramt() {
        return discOramt;
    }

    public void setDiscOramt(String discount) {
        this.discOramt = discount;
    }

    public String getCouponBody() {
        return couponBody;
    }

    public void setCouponBody(String couponBody) {
        this.couponBody = couponBody;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
