/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CreditCardEntity;
import entity.PurchasedPlanEntity;
import entity.TransactionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import util.exception.PurchasedPlanNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class PurchasedPlanEntitySessionBean implements PurchasedPlanEntitySessionBeanLocal {

    @EJB
    private CustomerEntitySessionBeanLocal customerEntitySessionBean;

    @EJB(name = "TransactionEntitySessionBeanLocal")
    private TransactionEntitySessionBeanLocal transactionEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<PurchasedPlanEntity> retrieveAllPurchasedPlans() {
        Query query = em.createQuery("SELECT p FROM PurchasedPlanEntity P");

        return query.getResultList();
    }

    @Override
    public Long createNewPurchasedPlan(PurchasedPlanEntity newPurchasedPlan, Long ccId) {
        try {
            em.persist(newPurchasedPlan);
            em.flush();
            PurchasedPlanEntity addedPlan = retrievePurchasedPlanByPurchasedPlanId(newPurchasedPlan.getPurchasedPlanId());
            TransactionEntity te = new TransactionEntity();
            CreditCardEntity ce = customerEntitySessionBean.retrieveCreditCardById(ccId);
            te.setPurchasedPlan(newPurchasedPlan);
            te.setCreditCardEntity(ce);
            te.setTransactionDate(java.time.LocalDate.now());
            TransactionEntity te2Add = transactionEntitySessionBeanLocal.createNewTransaction(te);
            addedPlan.setTransactionEntity(te2Add);
            return newPurchasedPlan.getPurchasedPlanId();
        } catch (PurchasedPlanNotFoundException ex) {
            Logger.getLogger(PurchasedPlanEntitySessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public PurchasedPlanEntity retrievePurchasedPlanByPurchasedPlanId(Long purchasedPlanId) throws PurchasedPlanNotFoundException {
        PurchasedPlanEntity purchasedPlanEntity = em.find(PurchasedPlanEntity.class, purchasedPlanId);

        if (purchasedPlanEntity != null) {
            return purchasedPlanEntity;
        } else {
            throw new PurchasedPlanNotFoundException("Purchased Plan ID " + purchasedPlanId + " does not exist!");
        }
    }
    
    @Override
    public void updateCreditValueForPurchasedPlanByPurchasedPlanId(Long purchasedPlanId, Long creditValue) throws PurchasedPlanNotFoundException {
        PurchasedPlanEntity purchasedPlanEntity = retrievePurchasedPlanByPurchasedPlanId(purchasedPlanId);
        purchasedPlanEntity.setCreditValue(purchasedPlanEntity.getCreditValue() + creditValue);
    }
    
    @Override
    public PurchasedPlanEntity retrieveCurrentPlanByCusId(Long cusId) throws PurchasedPlanNotFoundException {
        try{
            Query query = em.createQuery("SELECT s FROM PurchasedPlanEntity s WHERE s.customer.customerId =:cusId ORDER BY s.planId");
            query.setParameter("cusId", cusId);
            return (PurchasedPlanEntity) query.getSingleResult();
        } catch(NoResultException ex){
            return null;
        }
    }

}
