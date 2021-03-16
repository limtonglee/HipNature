/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.PurchasedPlanEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.PurchasedPlanNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface PurchasedPlanEntitySessionBeanLocal {

    public PurchasedPlanEntity retrievePurchasedPlanByPurchasedPlanId(Long purchasedPlanId) throws PurchasedPlanNotFoundException;

    public Long createNewPurchasedPlan(PurchasedPlanEntity newPurchasedPlan);

    public List<PurchasedPlanEntity> retrieveAllPurchasedPlans();
    
}
