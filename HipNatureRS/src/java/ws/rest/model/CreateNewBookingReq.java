/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.SessionEntity;

/**
 *
 * @author kelly
 */
public class CreateNewBookingReq {
 
    private String username;
    private String password;
    private retrieveSessionByClassId[] sessionArray;
 
    
    public CreateNewBookingReq() {
    }

    public CreateNewBookingReq(String username, String password, retrieveSessionByClassId[] sessionArray) {
        this.username = username;
        this.password = password;
        this.sessionArray = sessionArray;
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
     * @return the sessionArray
     */
    public retrieveSessionByClassId[] getSessionArray() {
        return sessionArray;
    }

    /**
     * @param sessionArray the sessionArray to set
     */
    public void setSessionArray(retrieveSessionByClassId[] sessionArray) {
        this.sessionArray = sessionArray;
    }




    
}
