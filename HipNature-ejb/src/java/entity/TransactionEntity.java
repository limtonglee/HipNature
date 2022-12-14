/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author User
 */
@Entity
public class TransactionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    
    @Column(nullable = false)
    private LocalDate transactionDate;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private CreditCardEntity creditCardEntity;
    
    @OneToOne(fetch = FetchType.LAZY)
    private CreditPlanEntity creditPlan;
    
    @OneToOne(fetch = FetchType.LAZY)
    private PurchasedPlanEntity purchasedPlan;

    public Long getTransactionId() {
        return transactionId;
    }
    

    
    

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transactionId != null ? transactionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the transactionId fields are not set
        if (!(object instanceof TransactionEntity)) {
            return false;
        }
        TransactionEntity other = (TransactionEntity) object;
        if ((this.transactionId == null && other.transactionId != null) || (this.transactionId != null && !this.transactionId.equals(other.transactionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TransactionEntity[ id=" + transactionId + " ]";
    }

    /**
     * @return the creditCardEntity
     */
    public CreditCardEntity getCreditCardEntity() {
        return creditCardEntity;
    }

    /**
     * @param creditCardEntity the creditCardEntity to set
     */
    public void setCreditCardEntity(CreditCardEntity creditCardEntity) {
        this.creditCardEntity = creditCardEntity;
    }

    /**
     * @return the creditPlan
     */
    public CreditPlanEntity getCreditPlan() {
        return creditPlan;
    }

    /**
     * @param creditPlan the creditPlan to set
     */
    public void setCreditPlan(CreditPlanEntity creditPlan) {
        this.creditPlan = creditPlan;
    }

    /**
     * @return the purchasedPlan
     */
    public PurchasedPlanEntity getPurchasedPlan() {
        return purchasedPlan;
    }

    /**
     * @param purchasedPlan the purchasedPlan to set
     */
    public void setPurchasedPlan(PurchasedPlanEntity purchasedPlan) {
        this.purchasedPlan = purchasedPlan;
    }

    /**
     * @return the transactionDate
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    
}
