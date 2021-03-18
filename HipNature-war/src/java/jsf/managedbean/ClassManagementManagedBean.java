/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.ClassEntity;
import entity.ClassTypeEntity;
import entity.TagEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FlowEvent;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
@Named(value = "classManagementManagedBean")
@ViewScoped
public class ClassManagementManagedBean implements Serializable {

    @EJB
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBeanLocal;

    @EJB
    private ClassEntitySessionBeanLocal classEntitySessionBeanLocal;

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;
    
    /**
     * @return the newClassEntity
     */
    public ClassEntity getNewClassEntity() {
        return newClassEntity;
    }

    /**
     * @param newClassEntity the newClassEntity to set
     */
    public void setNewClassEntity(ClassEntity newClassEntity) {
        this.newClassEntity = newClassEntity;
    }

    
    //Use for creating new class Entity
    private ClassEntity newClassEntity;
    private List<Long> tagIdsNew;
    private Long classTypeIdNew;
    private LocationTypeEnum locationTypeEnumNew;
    
    //Use for PostConstruct 
    private List<ClassEntity> classEntities;
    private List<TagEntity> tagEntities;
    private List<ClassTypeEntity> classTypeEntities;
    
    
    public ClassManagementManagedBean() {
        newClassEntity = new ClassEntity();
    }
    @PostConstruct
    public void postConstruct(){
        classEntities = classEntitySessionBeanLocal.retrieveAllClasses();
        classTypeEntities = classTypeEntitySessionBeanLocal.retrieveAllClassTypeEntity();
        tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
    }
    
    public LocationTypeEnum[] getEnumLocation(){
        return LocationTypeEnum.values();
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

    /**
     * @return the tagEntities
     */
    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    /**
     * @param tagEntities the tagEntities to set
     */
    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    /**
     * @return the tagIdsNew
     */
    public List<Long> getTagIdsNew() {
        return tagIdsNew;
    }

    /**
     * @param tagIdsNew the tagIdsNew to set
     */
    public void setTagIdsNew(List<Long> tagIdsNew) {
        this.tagIdsNew = tagIdsNew;
    }   

    /**
     * @return the classTypeIdNew
     */

    /**
     * @return the classTypeEntities
     */
    public List<ClassTypeEntity> getClassTypeEntities() {
        return classTypeEntities;
    }

    /**
     * @param classTypeEntities the classTypeEntities to set
     */
    public void setClassTypeEntities(List<ClassTypeEntity> classTypeEntities) {
        this.classTypeEntities = classTypeEntities;
    }

    /**
     * @return the locationTypeEnumNew
     */
    public LocationTypeEnum getLocationTypeEnumNew() {
        return locationTypeEnumNew;
    }

    /**
     * @param locationTypeEnumNew the locationTypeEnumNew to set
     */
    public void setLocationTypeEnumNew(LocationTypeEnum locationTypeEnumNew) {
        this.locationTypeEnumNew = locationTypeEnumNew;
    }

    /**
     * @return the classTypeIdNew
     */
    public Long getClassTypeIdNew() {
        return classTypeIdNew;
    }

    /**
     * @param classTypeIdNew the classTypeIdNew to set
     */
    public void setClassTypeIdNew(Long classTypeIdNew) {
        this.classTypeIdNew = classTypeIdNew;
    }
    
}
