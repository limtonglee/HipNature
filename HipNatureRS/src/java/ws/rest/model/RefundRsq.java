/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author User
 */
public class RefundRsq {
    
    private Long refundId;
    private LocalDate refundDate;
    private RetrieveBookingsByCusReq bookingRefunded;
    private Integer refundValue;
    private String reason;
    
    
    public RefundRsq() {
    }

    public RefundRsq(Long refundId, LocalDate refundDate, RetrieveBookingsByCusReq bookingRefunded, Integer refundValue, String reason) {
        this.refundId = refundId;
        this.refundDate = refundDate;
        this.bookingRefunded = bookingRefunded;
        this.refundValue = refundValue;
        this.reason = reason;
    }


    
    

    /**
     * @return the refundId
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * @param refundId the refundId to set
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
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

    /**
     * @return the bookingRefunded
     */
    public RetrieveBookingsByCusReq getBookingRefunded() {
        return bookingRefunded;
    }

    /**
     * @param bookingRefunded the bookingRefunded to set
     */
    public void setBookingRefunded(RetrieveBookingsByCusReq bookingRefunded) {
        this.bookingRefunded = bookingRefunded;
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
    
    
}
