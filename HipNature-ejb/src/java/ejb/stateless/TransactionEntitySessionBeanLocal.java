/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.TransactionEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.TransactionNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface TransactionEntitySessionBeanLocal {

    public void createNewTransaction(TransactionEntity newTransaction);

    public TransactionEntity retrieveSessionBySessionId(Long transactionId) throws TransactionNotFoundException;

    public List<TransactionEntity> retrieveAllTransactions();
    
}
