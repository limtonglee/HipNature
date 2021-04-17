/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.CustomerEntity;
import java.util.List;

/**
 *
 * @author leahj
 */
public class UpdateCustomer {
    private String username;
    private String password;
    private CustomerEntity customer;


    
    
    public UpdateCustomer()
    {        
    }

    public UpdateCustomer(String username, String password, CustomerEntity customer) {
        this.username = username;
        this.password = password;
        this.customer = customer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    
    

}
