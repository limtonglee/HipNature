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
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Entity
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(nullable = false)
    @NotNull
    private String status;
    
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PurchasedPlan purchasedplan;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private SessionEntity sessionEntity;
    
    @OneToOne(mappedBy = "bookingEntity", fetch = FetchType.LAZY, optional = true)
    private RefundEntity refundEntity;
    
    
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingId != null ? bookingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the bookingId fields are not set
        if (!(object instanceof BookingEntity)) {
            return false;
        }
        BookingEntity other = (BookingEntity) object;
        if ((this.bookingId == null && other.bookingId != null) || (this.bookingId != null && !this.bookingId.equals(other.bookingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BookingEntity[ id=" + bookingId + " ]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the sessionEntity
     */
    public SessionEntity getSessionEntity() {
        return sessionEntity;
    }

    /**
     * @param sessionEntity the sessionEntity to set
     */
    public void setSessionEntity(SessionEntity sessionEntity) {
        this.sessionEntity = sessionEntity;
    }

    /**
     * @return the refundEntity
     */
    public RefundEntity getRefundEntity() {
        return refundEntity;
    }

    /**
     * @param refundEntity the refundEntity to set
     */
    public void setRefundEntity(RefundEntity refundEntity) {
        this.refundEntity = refundEntity;
    }

    /**
     * @return the purchasedplan
     */
    public PurchasedPlan getPurchasedplan() {
        return purchasedplan;
    }

    /**
     * @param purchasedplan the purchasedplan to set
     */
    public void setPurchasedplan(PurchasedPlan purchasedplan) {
        this.purchasedplan = purchasedplan;
    }
    
}
