/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CreditPlanEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CreditPlanNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class CreditPlanSessionBean implements CreditPlanSessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<CreditPlanEntity> retrieveAllCreditPlans() {
        Query query = em.createQuery("SELECT p FROM CreditPlanEntity P");
        
        return query.getResultList();
    }

    @Override
    public Long createNewCreditPlan(CreditPlanEntity newCreditPlan) {
        
        em.persist(newCreditPlan);
        em.flush();

        return newCreditPlan.getCreditPlanId();
        
    }
        
    @Override
    public CreditPlanEntity retrieveCreditPlanByCreditPlanId(Long creditPlanId) throws CreditPlanNotFoundException {
        CreditPlanEntity creditPlanEntity = em.find(CreditPlanEntity.class, creditPlanId);
        
        if (creditPlanEntity != null) {
            return creditPlanEntity;
        } else {
            throw new CreditPlanNotFoundException("Credit Plan ID " + creditPlanId + " does not exist!");
        }
    }

    

}
