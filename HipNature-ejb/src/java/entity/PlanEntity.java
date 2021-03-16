/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author leahj
 */
@Entity
public class PlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    
    @Column(nullable = false,unique = true, length = 64)
    @NotNull
    private double price;
        
    @Column(nullable = false)
    @NotNull
    private Long creditValue;
    
    @Column(nullable = false,unique = true, length = 64)
    @NotNull
    private String planName;
         
    
    @Column(nullable = false)
    @NotNull
    private Long sessionLimit;
    
    @OneToMany(mappedBy = "planId",fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private List<PurchasedPlanEntity> purchasedPlans;    

    public PlanEntity() {
        this.purchasedPlans = new ArrayList<PurchasedPlanEntity>();
    }

    public PlanEntity(double price, Long creditValue, String planName, Long sessionLimit) {
        this.price = price;
        this.creditValue = creditValue;
        this.planName = planName;
        this.sessionLimit = sessionLimit;
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

    public Long getSessionLimit() {
        return sessionLimit;
    }

    public void setSessionLimit(Long sessionLimit) {
        this.sessionLimit = sessionLimit;
    }

    
    
    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planId != null ? planId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the planId fields are not set
        if (!(object instanceof PlanEntity)) {
            return false;
        }
        PlanEntity other = (PlanEntity) object;
        if ((this.planId == null && other.planId != null) || (this.planId != null && !this.planId.equals(other.planId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PlanEntity[ id=" + planId + " ]";
    }

    /**
     * @return the planName
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * @param planName the planName to set
     */
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    
}
