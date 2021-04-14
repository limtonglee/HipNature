package ws.rest.model;


import entity.PlanEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class PurchasePlanReq {


    private Long planId;
    private String username;
    private String password;
    private Long ccId;

    public PurchasePlanReq() {
    }
    

    public PurchasePlanReq(String username, String password,Long planId, Long ccId) {
        this.username = username;
        this.password = password;
        this.planId = planId;
        this.ccId = ccId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the planId
     */
    public Long getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    /**
     * @return the ccId
     */
    public Long getCcId() {
        return ccId;
    }

    /**
     * @param ccId the ccId to set
     */
    public void setCcId(Long ccId) {
        this.ccId = ccId;
    }



}

