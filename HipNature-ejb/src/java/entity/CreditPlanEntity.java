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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class CreditPlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditPlanId;
    @Column(nullable = false,unique = true, length = 64)
    @NotNull
    private double price;
    
    @Column(nullable = false,unique = true)
    @NotNull
    private Long creditValue;
    
    @OneToOne(fetch = FetchType.LAZY)
    private TransactionEntity transactionEntity;

    public CreditPlanEntity() {
    }

    public CreditPlanEntity(double planPrice, Long creditValue) {
        this.price = planPrice;
        this.creditValue = creditValue;
    }

    
    public Long getCreditPlanId() {
        return creditPlanId;
    }

    public void setCreditPlanId(Long creditPlanId) {
        this.creditPlanId = creditPlanId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditPlanId != null ? creditPlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the creditPlanId fields are not set
        if (!(object instanceof CreditPlanEntity)) {
            return false;
        }
        CreditPlanEntity other = (CreditPlanEntity) object;
        if ((this.creditPlanId == null && other.creditPlanId != null) || (this.creditPlanId != null && !this.creditPlanId.equals(other.creditPlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CreditPlan[ id=" + creditPlanId + " ]";
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the transactionEntity
     */
    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    /**
     * @param transactionEntity the transactionEntity to set
     */
    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    /**
     * @return the creditValue
     */
    public Long getCreditValue() {
        return creditValue;
    }

    /**
     * @param creditValue the creditValue to set
     */
    public void setCreditValue(Long creditValue) {
        this.creditValue = creditValue;
    }


    
}
