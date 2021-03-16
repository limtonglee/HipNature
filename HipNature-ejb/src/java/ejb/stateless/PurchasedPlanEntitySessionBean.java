/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.PurchasedPlanEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.PurchasedPlanNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class PurchasedPlanEntitySessionBean implements PurchasedPlanEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<PurchasedPlanEntity> retrieveAllPurchasedPlans() {
        Query query = em.createQuery("SELECT p FROM PurchasedPlanEntity P");
        
        return query.getResultList();
    }

    @Override
    public Long createNewPurchasedPlan(PurchasedPlanEntity newPurchasedPlan) {
        
        em.persist(newPurchasedPlan);
        em.flush();

        return newPurchasedPlan.getPurchasedPlanId();
        
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

    

}
