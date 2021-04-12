/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import java.util.Date;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
public class retrieveSessionByClassId {

    private Long sessionId;

    private String venue;

    private Date startTime;

    private Date endTime;

    private Integer Duration;

    private String phone;

    private Integer maxCapacity;

    private String status;

    private LocationTypeEnum locationTypeEnum;

    private String instructor;

    public retrieveSessionByClassId() {
    }

    public retrieveSessionByClassId(Long sessionId, String venue, Date startTime, Date endTime, Integer Duration, String phone, Integer maxCapacity, String status, LocationTypeEnum locationTypeEnum, String instructor) {
        this.sessionId = sessionId;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
        this.Duration = Duration;
        this.phone = phone;
        this.maxCapacity = maxCapacity;
        this.status = status;
        this.locationTypeEnum = locationTypeEnum;
        this.instructor = instructor;
    }

    /**
     * @return the sessionId
     */
    public Long getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the Duration
     */
    public Integer getDuration() {
        return Duration;
    }

    /**
     * @param Duration the Duration to set
     */
    public void setDuration(Integer Duration) {
        this.Duration = Duration;
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
     * @return the maxCapacity
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
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
     * @return the locationTypeEnum
     */
    public LocationTypeEnum getLocationTypeEnum() {
        return locationTypeEnum;
    }

    /**
     * @param locationTypeEnum the locationTypeEnum to set
     */
    public void setLocationTypeEnum(LocationTypeEnum locationTypeEnum) {
        this.locationTypeEnum = locationTypeEnum;
    }

    /**
     * @return the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    
}