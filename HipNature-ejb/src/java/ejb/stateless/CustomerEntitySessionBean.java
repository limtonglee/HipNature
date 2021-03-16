/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CustomerEntity;
import static entity.ReviewEntity_.customerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.security.CryptographicHelper;

/**
 *
 * @author kelly
 */
@Stateless
public class CustomerEntitySessionBean implements CustomerEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<CustomerEntity> retrieveAllCustomers() {
        Query query = em.createQuery("SELECT c FROM CustomerEntity C");
        
        return query.getResultList();
    }

    @Override
    public Long createNewCustomer(CustomerEntity newCustomer) {
        
        em.persist(newCustomer);
        em.flush();

        return newCustomer.getCustomerId();
        
    }
    
    @Override
    public CustomerEntity retrieveCustomerByCustomerId(Long customerId) throws CustomerNotFoundException {
        CustomerEntity cust = em.find(CustomerEntity.class, customerId);
        
        if (cust != null) {
            return cust;
        } else {
            throw new CustomerNotFoundException("Customer ID " + customerId + " does not exist!");
        }
    }
    
    @Override
    public CustomerEntity retrieveCustomerByUsername(String username) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM CustomerEntity WHERE c.username =:inUsername");
        query.setParameter("inUsername", username);
        
        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (NoResultException ex) {
            throw new CustomerNotFoundException("Customer username " + username + " does not exist!");
        }
    }
    
    @Override
    public CustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException {
        
        try {
            CustomerEntity customerEntity = retrieveCustomerByUsername(username);
            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + customerEntity.getSalt()));
            
            if (customerEntity.getPassword().equals(passwordHash)) {
                return customerEntity;
            } else {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        } catch (CustomerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }
}
