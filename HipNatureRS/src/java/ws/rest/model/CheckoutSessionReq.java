/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.SessionEntity;

/**
 *
 * @author leahj
 */
public class CheckoutSessionReq {
       private String username;
       private String password;
       private SessionEntity[] sessionEntities;
    public CheckoutSessionReq() {
    }

    public CheckoutSessionReq(String username, String password, SessionEntity[] sessionEntities) {
        this.username = username;
        this.password = password;
        this.sessionEntities = sessionEntities;
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
     * @return the sessionEntities
     */
    public SessionEntity[] getSessionEntities() {
        return sessionEntities;
    }

    /**
     * @param sessionEntities the sessionEntities to set
     */
    public void setSessionEntities(SessionEntity[] sessionEntities) {
        this.sessionEntities = sessionEntities;
    }
    
    
}
