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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private String classType;
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
    private List<ReviewEntity> reviewEntity;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TagEntity> tagEntity;
        
        
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
     * @return the classType
     */
    public String getClassType() {
        return classType;
    }

    /**
     * @param classType the classType to set
     */
    public void setClassType(String classType) {
        this.classType = classType;
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
     * @return the reviewEntity
     */
    public List<ReviewEntity> getReviewEntity() {
        return reviewEntity;
    }

    /**
     * @param reviewEntity the reviewEntity to set
     */
    public void setReviewEntity(List<ReviewEntity> reviewEntity) {
        this.reviewEntity = reviewEntity;
    }

    /**
     * @return the tagEntity
     */
    public List<TagEntity> getTagEntity() {
        return tagEntity;
    }

    /**
     * @param tagEntity the tagEntity to set
     */
    public void setTagEntity(List<TagEntity> tagEntity) {
        this.tagEntity = tagEntity;
    }
    
}
