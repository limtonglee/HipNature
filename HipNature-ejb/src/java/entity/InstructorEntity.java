/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class InstructorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;

    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    private String instructorName;
    
    @Column(nullable = false, unique = true, length = 8)
    @NotNull
    @Size(max = 8)
    private String phone;
    
    @Column(nullable = false, unique = true, length = 64)
    @NotNull
    @Size(max = 64)
    @Email
    private String email;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PartnerEntity partnerEntity;
    
    @OneToMany(mappedBy = "instructor", fetch = FetchType.LAZY)
    private List<SessionEntity> sessionEntity;
    
    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (instructorId != null ? instructorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the instructorId fields are not set
        if (!(object instanceof InstructorEntity)) {
            return false;
        }
        InstructorEntity other = (InstructorEntity) object;
        if ((this.instructorId == null && other.instructorId != null) || (this.instructorId != null && !this.instructorId.equals(other.instructorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EmployeeEntity[ id=" + instructorId + " ]";
    }

    /**
     * @return the instructorName
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * @param instructorName the instructorName to set
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the partnerEntity
     */
    public PartnerEntity getPartnerEntity() {
        return partnerEntity;
    }

    /**
     * @param partnerEntity the partnerEntity to set
     */
    public void setPartnerEntity(PartnerEntity partnerEntity) {
        this.partnerEntity = partnerEntity;
    }

    /**
     * @return the sessionEntity
     */
    public List<SessionEntity> getSessionEntity() {
        return sessionEntity;
    }

    /**
     * @param sessionEntity the sessionEntity to set
     */
    public void setSessionEntity(List<SessionEntity> sessionEntity) {
        this.sessionEntity = sessionEntity;
    }
    
}
