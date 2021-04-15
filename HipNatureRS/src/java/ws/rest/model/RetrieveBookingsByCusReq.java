/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author User
 */
public class RetrieveBookingsByCusReq {

    private Long bookingId;
    private String sessionName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    public RetrieveBookingsByCusReq() {
    }

    public RetrieveBookingsByCusReq(Long bookingId, String sessionName, LocalDateTime startTime, LocalDateTime endTime) {
        this.bookingId = bookingId;
        this.sessionName = sessionName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * @return the bookingId
     */
    public Long getBookingId() {
        return bookingId;
    }

    /**
     * @param bookingId the bookingId to set
     */
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * @return the sessionName
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * @param sessionName the sessionName to set
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    
    
}
