/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
@Entity
public class ClassEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @Column(nullable = false)
    @NotNull
    private String className;
    @Column(nullable = false)
    @Positive
    @Min(1)
    @NotNull
    private Integer credit;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private LocationTypeEnum locationTypeEnum;

    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviewEntities;

    @ManyToMany(mappedBy = "classEntities", fetch = FetchType.LAZY)
    private List<TagEntity> tagEntities;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClassTypeEntity classTypeEntity;
    
    @OneToMany(mappedBy = "classEntity", fetch = FetchType.LAZY)
    private List<SessionEntity> sessionEntities;
    
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private PartnerEntity partnerEntity;
        
    public ClassEntity() {
        tagEntities = new ArrayList<>();
        reviewEntities = new ArrayList<>();
        sessionEntities = new ArrayList<>();
    }

    public ClassEntity(ClassTypeEntity classTypeEntity, String className, Integer credit, LocationTypeEnum locationTypeEnum) {
        this.className = className;
        this.credit = credit;
        this.locationTypeEnum = locationTypeEnum;
        this.classTypeEntity = classTypeEntity;
    }

    

    public void addTag(TagEntity tagEntity) {
        if (tagEntity != null) {
            if (!this.tagEntities.contains(tagEntity)) {
                this.tagEntities.add(tagEntity);
                if (!tagEntity.getClassEntities().contains(this)) {
                    tagEntity.getClassEntities().add(this);
                }
            }
        }
    }

    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    public void setSessionEntities(List<SessionEntity> sessionEntities) {
        this.sessionEntities = sessionEntities;
    }

    
    public void removeTag(TagEntity tagEntity) {
        if (tagEntity != null) {
            if (this.tagEntities.contains(tagEntity)) {
                this.tagEntities.remove(tagEntity);
                if (tagEntity.getClassEntities().contains(this)) {
                    tagEntity.getClassEntities().remove(this);
                }
            }
        }
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classId != null ? classId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the classId fields are not set
        if (!(object instanceof ClassEntity)) {
            return false;
        }
        ClassEntity other = (ClassEntity) object;
        if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassEntity[ id=" + classId + " ]";
    }


    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the credit
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
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
     * @return the reviewEntities
     */
    public List<ReviewEntity> getReviewEntities() {
        return reviewEntities;
    }

    /**
     * @param reviewEntities the reviewEntities to set
     */
    public void setReviewEntities(List<ReviewEntity> reviewEntities) {
        this.reviewEntities = reviewEntities;
    }

    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    /**
     * @return the classTypeEntity
     */
    public ClassTypeEntity getClassTypeEntity() {
        return classTypeEntity;
    }

    /**
     * @param classTypeEntity the classTypeEntity to set
     */
    public void setClassTypeEntity(ClassTypeEntity classTypeEntity) {
        this.classTypeEntity = classTypeEntity;
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

}
