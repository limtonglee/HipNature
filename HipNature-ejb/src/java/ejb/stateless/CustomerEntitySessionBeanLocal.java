/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CreditCardEntity;
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

    public CustomerEntity createNewCustomer(CustomerEntity newCustomer);

    public CustomerEntity retrieveCustomerByCustomerId(Long customerId) throws CustomerNotFoundException;

    public CustomerEntity retrieveCustomerByUsername(String username) throws CustomerNotFoundException;

    public CustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;

    public CreditCardEntity addCreditCardToCustomer(CreditCardEntity newCreditCard, CustomerEntity customerToBind) throws InvalidLoginCredentialException;

    public List<CreditCardEntity> getAllCreditCardsFromCustomer(CustomerEntity customerToget) throws InvalidLoginCredentialException;

    public void deleteCreditCard(Long creditCardId);

    public CreditCardEntity retrieveCreditCardById(Long creditCardId);
    
}
