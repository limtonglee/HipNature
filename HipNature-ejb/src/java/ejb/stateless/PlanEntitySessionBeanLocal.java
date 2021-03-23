/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.PlanEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.PlanNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface PlanEntitySessionBeanLocal {

    public List<PlanEntity> retrieveAllPlans();

    public Long createNewPlan(PlanEntity newPlan);

    public PlanEntity retrievePlanByPlanId(Long planId) throws PlanNotFoundException;
    
}
