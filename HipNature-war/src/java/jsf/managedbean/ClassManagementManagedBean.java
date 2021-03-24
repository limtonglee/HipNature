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
import entity.PartnerEntity;
import entity.TagEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FlowEvent;
import util.enumeration.LocationTypeEnum;
import util.exception.CreateNewClassException;
import util.exception.InputDataValidationException;

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
    
    private PartnerEntity currentPartnerEntity;
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
    
    //Filter
    private List<ClassEntity> classEntitiesFiltered;
    
    
    public ClassManagementManagedBean() {
        currentPartnerEntity = (PartnerEntity)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity");
        newClassEntity = new ClassEntity();
        newClassEntity.setPartnerEntity(currentPartnerEntity);
    }
    @PostConstruct
    public void postConstruct(){
        classEntities = classEntitySessionBeanLocal.retrieveAllClassesByPartnerId(currentPartnerEntity.getPartnerEntityId());
        classTypeEntities = classTypeEntitySessionBeanLocal.retrieveAllClassTypeEntity();
        tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
    }
    
    public void createNewClass(ActionEvent event){
        try{
            System.out.print("*******************************************testing*******************************************");
            System.out.print(newClassEntity.getClassName());
            //System.out.print(newClassEntity.getClassTypeEntity().getClassTypeName());
            System.out.print(newClassEntity.getCredit());
            System.out.print(newClassEntity.getLocationTypeEnum());
            ClassEntity ce = classEntitySessionBeanLocal.createNewClass(newClassEntity, classTypeIdNew, tagIdsNew);
            currentPartnerEntity.getClassEntity().add(ce);
            if (classEntitiesFiltered != null){
                classEntitiesFiltered.add(ce);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Account Created", null));
        } catch (InputDataValidationException | CreateNewClassException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating new class", null));
            System.out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassManagementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    public LocationTypeEnum[] getLocationTypeEnum(){
        return LocationTypeEnum.values();
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
        newClassEntity.setLocationTypeEnum(locationTypeEnumNew);
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

    /**
     * @return the currentPartnerEntity
     */
    public PartnerEntity getCurrentPartnerEntity() {
        return currentPartnerEntity;
    }

    /**
     * @param currentPartnerEntity the currentPartnerEntity to set
     */
    public void setCurrentPartnerEntity(PartnerEntity currentPartnerEntity) {
        this.currentPartnerEntity = currentPartnerEntity;
    }

    /**
     * @return the classEntitiesFiltered
     */
    public List<ClassEntity> getClassEntitiesFiltered() {
        return classEntitiesFiltered;
    }

    /**
     * @param classEntitiesFiltered the classEntitiesFiltered to set
     */
    public void setClassEntitiesFiltered(List<ClassEntity> classEntitiesFiltered) {
        this.classEntitiesFiltered = classEntitiesFiltered;
    }
    
}
