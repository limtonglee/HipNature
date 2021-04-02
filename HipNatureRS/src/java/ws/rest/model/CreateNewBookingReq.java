/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.BookingEntity;

/**
 *
 * @author kelly
 */
public class CreateNewBookingReq {
 
    private BookingEntity newBooking;
    private Long purchasedPlanId;
    private Long sessionEntityId;
    private Long customerId;
    
    public CreateNewBookingReq() {
    }

    public CreateNewBookingReq(BookingEntity newBooking, Long purchasedPlanId, Long sessionEntityId, Long customerId) {
        this.newBooking = newBooking;
        this.purchasedPlanId = purchasedPlanId;
        this.sessionEntityId = sessionEntityId;
        this.customerId = customerId;
    }

    
    
    public BookingEntity getNewBooking() {
        return newBooking;
    }

    public void setNewBooking(BookingEntity newBooking) {
        this.newBooking = newBooking;
    }

    public Long getPurchasedPlanId() {
        return purchasedPlanId;
    }

    public void setPurchasedPlanId(Long purchasedPlanId) {
        this.purchasedPlanId = purchasedPlanId;
    }

    public Long getSessionEntityId() {
        return sessionEntityId;
    }


    public void setSessionEntityId(Long sessionEntityId) {
        this.sessionEntityId = sessionEntityId;
    }

    /**
     * @return the customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    
}
