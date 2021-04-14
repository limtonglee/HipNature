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

    public List<PurchasedPlanEntity> retrieveAllPurchasedPlans();

    public Long createNewPurchasedPlan(PurchasedPlanEntity newPurchasedPlan, Long ccId);

    public PurchasedPlanEntity retrieveCurrentPlanByCusId(Long cusId) throws PurchasedPlanNotFoundException;
    
}
