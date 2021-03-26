/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
@Entity
public class SessionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;
    @Column(nullable = false)
    @NotNull
    private String venue;

    @Column(nullable = false)
    @NotNull
    private Date startTime;

    @Column(nullable = false)
    @NotNull
    private Date endTime;
    @Column(nullable = false)
    @NotNull
    @Positive
    @Min(1)
    private Integer Duration;
    @Column(nullable = false, length = 8)
    @NotNull
    @Size(max = 8)
    private String phone;
    @Column(nullable = false)
    @NotNull
    @Positive
    @Min(1)
    private Integer maxCapacity;
    @Column(nullable = false)
    @NotNull
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private LocationTypeEnum locationTypeEnum;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private InstructorEntity instructor;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private ClassEntity classEntity;


    @OneToMany(mappedBy = "sessionEntity", fetch = FetchType.LAZY)
    private List<BookingEntity> participants;

    public Long getSessionId() {
        return sessionId;
    }

    public SessionEntity() {
    }

    public SessionEntity(String venue, Date startTime, Integer duration, String phone, Integer maxCapacity, String status, LocationTypeEnum locationTypeEnum, InstructorEntity instructor, ClassEntity classEntity) {
        Calendar calendar = Calendar.getInstance();

        this.venue = venue;
        this.startTime = startTime;
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, duration);

        this.endTime = calendar.getTime();
        this.Duration = duration;
        this.phone = phone;
        this.maxCapacity = maxCapacity;
        this.status = status;
        this.locationTypeEnum = locationTypeEnum;
        this.instructor = instructor;
        this.classEntity = classEntity;
    }
    

    
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sessionId != null ? sessionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the sessionId fields are not set
        if (!(object instanceof SessionEntity)) {
            return false;
        }
        SessionEntity other = (SessionEntity) object;
        if ((this.sessionId == null && other.sessionId != null) || (this.sessionId != null && !this.sessionId.equals(other.sessionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SessionEntity[ id=" + sessionId + " ]";
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
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime() {
       Calendar calendar = Calendar.getInstance();

         calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, this.Duration);

        this.endTime = calendar.getTime();
        this.endTime = endTime;
    }

    /**
     * @return the instructor
     */
    public InstructorEntity getInstructor() {
        return instructor;
    }

    /**
     * @param instructor the instructor to set
     */
    public void setInstructor(InstructorEntity instructor) {
        this.instructor = instructor;
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
     * @return the participants
     */
    public List<BookingEntity> getParticipants() {
        return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(List<BookingEntity> participants) {
        this.participants = participants;
    }

    /**
     * @return the classEntity
     */

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    /**
     * @param classEntity the classEntity to set
     */
    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

}
