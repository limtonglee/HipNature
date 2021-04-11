/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.PurchasedPlanEntity;
import entity.PlanEntity;
import java.util.List;

/**
 *
 * @author limtonglee
 */
public class createPlanReq {
    
    private double price;
    private Long creditValue;
    private String planName;
    private Long sessionLimit;
    private List<PurchasedPlanEntity> purchasedPlans; 

    public createPlanReq() {
    }

    public createPlanReq(double price, Long creditValue, String planName, Long sessionLimit, List<PurchasedPlanEntity> purchasedPlans) {
        this.price = price;
        this.creditValue = creditValue;
        this.planName = planName;
        this.sessionLimit = sessionLimit;
        this.purchasedPlans = purchasedPlans;
    }
    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(Long creditValue) {
        this.creditValue = creditValue;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getSessionLimit() {
        return sessionLimit;
    }

    public void setSessionLimit(Long sessionLimit) {
        this.sessionLimit = sessionLimit;
    }

    public List<PurchasedPlanEntity> getPurchasedPlans() {
        return purchasedPlans;
    }

    public void setPurchasedPlans(List<PurchasedPlanEntity> purchasedPlans) {
        this.purchasedPlans = purchasedPlans;
    }
       
    
}

