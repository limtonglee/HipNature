/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.CreditPlanEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.CreditPlanNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface CreditPlanSessionBeanLocal {

    public List<CreditPlanEntity> retrieveAllCreditPlans();

    public Long createNewCreditPlan(CreditPlanEntity newCreditPlan);

    public CreditPlanEntity retrieveCreditPlanByCreditPlanId(Long creditPlanId) throws CreditPlanNotFoundException;
    
}
