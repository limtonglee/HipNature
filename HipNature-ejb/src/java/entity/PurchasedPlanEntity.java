/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author User
 */
@Entity
public class PurchasedPlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchasedPlanId;

    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Positive
    @Min(1)
    private Integer sessionLeft;
    
    @Column(nullable = false)
    private LocalDate purchaseDate;
    
        
    @Column(nullable = false)
    private LocalDate expiryDate;
    
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private PlanEntity planId;

    @OneToMany(mappedBy = "purchasedplan" )
    private List<BookingEntity> booking;
    
    @OneToOne (fetch = FetchType.LAZY) 
    private TransactionEntity transactionEntity;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private CustomerEntity customer;

    public PurchasedPlanEntity() {
        this.purchaseDate = java.time.LocalDate.now();
        this.expiryDate = LocalDate.now().plusMonths(1);
    }
    
    
    
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
        if (!(object instanceof PurchasedPlanEntity)) {
            return false;
        }
        PurchasedPlanEntity other = (PurchasedPlanEntity) object;
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

    /**
     * @return the planId
     */
    public PlanEntity getPlanId() {
        return planId;
    }

    /**
     * @param planId the planId to set
     */
    public void setPlanId(PlanEntity planId) {
        this.planId = planId;
    }

    /**
     * @return the booking
     */
    public List<BookingEntity> getBooking() {
        return booking;
    }

    /**
     * @param booking the booking to set
     */
    public void setBooking(List<BookingEntity> booking) {
        this.booking = booking;
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
     * @return the purchaseDate
     */
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * @param purchaseDate the purchaseDate to set
     */
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return the expiryDate
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
}
