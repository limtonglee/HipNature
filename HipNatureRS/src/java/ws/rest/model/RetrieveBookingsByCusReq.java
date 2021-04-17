/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import java.time.LocalDate;
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
    private String phone;
    private String Instructor;
    private String venue;
    private Long purchasedplanId;
    private LocalDate expiryDate;
    private LocalDate bookingDate;
    
    
    public RetrieveBookingsByCusReq() {
    }

    public RetrieveBookingsByCusReq(Long bookingId, String sessionName, LocalDateTime startTime, LocalDateTime endTime, String phone, String Instructor, String venue, Long purchasedplanId, LocalDate expiryDate, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.sessionName = sessionName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.phone = phone;
        this.Instructor = Instructor;
        this.venue = venue;
        this.purchasedplanId = purchasedplanId;
        this.expiryDate = expiryDate;
        this.bookingDate = bookingDate;
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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the Instructor
     */
    public String getInstructor() {
        return Instructor;
    }

    /**
     * @param Instructor the Instructor to set
     */
    public void setInstructor(String Instructor) {
        this.Instructor = Instructor;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the purchasedplanId
     */
    public Long getPurchasedplanId() {
        return purchasedplanId;
    }

    /**
     * @param purchasedplanId the purchasedplanId to set
     */
    public void setPurchasedplanId(Long purchasedplanId) {
        this.purchasedplanId = purchasedplanId;
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

    /**
     * @return the bookingDate
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * @param bookingDate the bookingDate to set
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    
    
}
