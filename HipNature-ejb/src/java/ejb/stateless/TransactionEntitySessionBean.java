/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.TransactionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.TransactionNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class TransactionEntitySessionBean implements TransactionEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<TransactionEntity> retrieveAllTransactions() {
        Query query = em.createQuery("SELECT t FROM TransactionEntity T");
        
        return query.getResultList();
    }

    @Override
    public TransactionEntity createNewTransaction(TransactionEntity newTransaction) {
        em.persist(newTransaction);
        em.flush();
        return newTransaction;
    }
        
    @Override
    public TransactionEntity retrieveTransactionByTransactionId(Long transactionId) throws TransactionNotFoundException {
        TransactionEntity transactionEntity = em.find(TransactionEntity.class, transactionId);
        
        if (transactionEntity != null) {
            return transactionEntity;
        } else {
            throw new TransactionNotFoundException("Transaction ID " + transactionId + " does not exist!");
        }
    }
    
    @Override
    public List<TransactionEntity> getAllTransactionsFromCustomerId(Long cusId){
       
            Query query = em.createQuery("SELECT s from TransactionEntity s WHERE s.creditCardEntity.customerEntity.customerId = :cusId");
            query.setParameter("cusId", cusId);
            List<TransactionEntity> results = query.getResultList();
            return results;
    }

    
    
}
