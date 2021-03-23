/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author kelly
 */
@Local
public interface CustomerEntitySessionBeanLocal {

    public List<CustomerEntity> retrieveAllCustomers();

    public Long createNewCustomer(CustomerEntity newCustomer);

    public CustomerEntity retrieveCustomerByCustomerId(Long customerId) throws CustomerNotFoundException;

    public CustomerEntity retrieveCustomerByUsername(String username) throws CustomerNotFoundException;

    public CustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;
    
}