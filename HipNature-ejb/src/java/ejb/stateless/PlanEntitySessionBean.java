/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import static entity.CreditPlanEntity_.creditPlanId;
import entity.PlanEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.PlanNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class PlanEntitySessionBean implements PlanEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<PlanEntity> retrieveAllPlans() {
        Query query = em.createQuery("SELECT p FROM PlanEntity P");
        
        return query.getResultList();
    }

    @Override
    public Long createNewPlan(PlanEntity newPlan) {
        
        em.persist(newPlan);
        em.flush();

        return newPlan.getPlanId();
        
    }
        
    @Override
    public PlanEntity retrievePlanByPlanId(Long planId) throws PlanNotFoundException {
        PlanEntity planEntity = em.find(PlanEntity.class, planId);
        
        if (planEntity != null) {
            return planEntity;
        } else {
            throw new PlanNotFoundException("Plan ID " + planId + " does not exist!");
        }
    }
    
    @Override
    public List<PlanEntity> retrievePlanByCustomerType(String type) throws PlanNotFoundException {
         Query query = em.createQuery("SELECT p FROM PlanEntity P WHERE P.type = :type");
         query.setParameter("type", type);
         
         return query.getResultList();
        
    }
    
    
}
