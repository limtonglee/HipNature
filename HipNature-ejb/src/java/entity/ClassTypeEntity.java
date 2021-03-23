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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author User
 */
@Entity
public class ClassTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classTypeId;
    @Column(nullable = false, unique = true, length =32)
    @NotNull
    @Size(max = 32)
    private String classTypeName;
    
    @OneToMany(mappedBy = "classTypeEntity", fetch = FetchType.LAZY)
    private List<ClassEntity> classEntities;


    public ClassTypeEntity() {
        classEntities = new ArrayList<>();
    }
    public ClassTypeEntity(String classTypeName) {
        this();
        this.classTypeName = classTypeName;
    }

    public Long getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Long classTypeId) {
        this.classTypeId = classTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classTypeId != null ? classTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the classTypeId fields are not set
        if (!(object instanceof ClassTypeEntity)) {
            return false;
        }
        ClassTypeEntity other = (ClassTypeEntity) object;
        if ((this.classTypeId == null && other.classTypeId != null) || (this.classTypeId != null && !this.classTypeId.equals(other.classTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClassTypeEntity[ id=" + classTypeId + " ]";
    }

    /**
     * @return the classTypeName
     */
    public String getClassTypeName() {
        return classTypeName;
    }

    /**
     * @param classTypeName the classTypeName to set
     */
    public void setClassTypeName(String classTypeName) {
        this.classTypeName = classTypeName;
    }

    /**
     * @return the classEntities
     */
    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    /**
     * @param classEntities the classEntities to set
     */
    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }
    
}
