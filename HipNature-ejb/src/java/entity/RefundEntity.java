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
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author User
 */
@Entity
public class RefundEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refundId;
    @Column(nullable = false)
    @NotNull
    private String reason;
    @Column(nullable = false)
    @NotNull
    @Positive
    @Min(1)
    private Integer refundValue;
    @Column(nullable = false)
    private LocalDate refundDate;
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private BookingEntity bookingEntity;

    public RefundEntity() {
        this.refundDate = java.time.LocalDate.now();
    }
    
    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refundId != null ? refundId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the refundId fields are not set
        if (!(object instanceof RefundEntity)) {
            return false;
        }
        RefundEntity other = (RefundEntity) object;
        if ((this.refundId == null && other.refundId != null) || (this.refundId != null && !this.refundId.equals(other.refundId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RefundEntity[ id=" + refundId + " ]";
    }

    /**
     * @return the refundValue
     */
    public Integer getRefundValue() {
        return refundValue;
    }

    /**
     * @param refundValue the refundValue to set
     */
    public void setRefundValue(Integer refundValue) {
        this.refundValue = refundValue;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the bookingEntity
     */
    public BookingEntity getBookingEntity() {
        return bookingEntity;
    }

    /**
     * @param bookingEntity the bookingEntity to set
     */
    public void setBookingEntity(BookingEntity bookingEntity) {
        this.bookingEntity = bookingEntity;
    }

    /**
     * @return the refundDate
     */
    public LocalDate getRefundDate() {
        return refundDate;
    }

    /**
     * @param refundDate the refundDate to set
     */
    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }
    
}
