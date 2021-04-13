/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.CreditCardEntity;

/**
 *
 * @author User
 */
public class CreditCardReq {
    private CreditCardEntity creditCard;
    private String username;
    private String password;

    public CreditCardReq() {
    }

    public CreditCardReq(CreditCardEntity creditCard, String username, String password) {
        this.creditCard = creditCard;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the creditCard
     */
    public CreditCardEntity getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCardEntity creditCard) {
        this.creditCard = creditCard;
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
    
    
    
    
}
