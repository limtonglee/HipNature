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
    public Long createNewTransaction(TransactionEntity newTransaction) {
        
        em.persist(newTransaction);
        em.flush();

        return newTransaction.getTransactionId();
        
    }
        
    @Override
    public TransactionEntity retrieveSessionBySessionId(Long transactionId) throws TransactionNotFoundException {
        TransactionEntity transactionEntity = em.find(TransactionEntity.class, transactionId);
        
        if (transactionEntity != null) {
            return transactionEntity;
        } else {
            throw new TransactionNotFoundException("Transaction ID " + transactionId + " does not exist!");
        }
    }

    
    
}