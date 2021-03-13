/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author User
 */
@Entity
public class PurchasedPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchasedPlanId;

    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Positive
    @Min(1)
    private Integer sessionLeft;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private PlanEntity planId;

    
    //Plan Type???  

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private CustomerEntity customer;
    
    public Long getPurchasedPlanId() {
        return purchasedPlanId;
    }

    public void setPurchasedPlanId(Long purchasedPlanId) {
        this.purchasedPlanId = purchasedPlanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (purchasedPlanId != null ? purchasedPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the purchasedPlanId fields are not set
        if (!(object instanceof PurchasedPlan)) {
            return false;
        }
        PurchasedPlan other = (PurchasedPlan) object;
        if ((this.purchasedPlanId == null && other.purchasedPlanId != null) || (this.purchasedPlanId != null && !this.purchasedPlanId.equals(other.purchasedPlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PurchasedPlan[ id=" + purchasedPlanId + " ]";
    }


    /**
     * @return the sessionLeft
     */
    public Integer getSessionLeft() {
        return sessionLeft;
    }

    /**
     * @param sessionLeft the sessionLeft to set
     */
    public void setSessionLeft(Integer sessionLeft) {
        this.sessionLeft = sessionLeft;
    }

    /**
     * @return the customer
     */
    public CustomerEntity getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
}
