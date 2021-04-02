/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

/**
 *
 * @author kelly
 */
public class CreateNewBookingRsp {

    private Long newBookingId;

    public CreateNewBookingRsp() {
    }

    public CreateNewBookingRsp(Long newBookingId) {
        this.newBookingId = newBookingId;
    }

    /**
     * @return the newBookingId
     */
    public Long getNewBookingId() {
        return newBookingId;
    }

    /**
     * @param newBookingId the newBookingId to set
     */
    public void setNewBookingId(Long newBookingId) {
        this.newBookingId = newBookingId;
    } 

}
