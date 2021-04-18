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
import java.util.ArrayList;
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
import util.exception.DeleteClassEntityException;
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

    private Integer classRating;

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

    //EntityToDelete
    private ClassEntity classEntityToDelete;

    //Update
    private ClassEntity classEntityToUpdate;

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

    public ClassManagementManagedBean() {
        currentPartnerEntity = (PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity");
        newClassEntity = new ClassEntity();
        newClassEntity.setPartnerEntity(currentPartnerEntity);
    }

    @PostConstruct
    public void postConstruct() {
        classEntities = classEntitySessionBeanLocal.retrieveAllClassesByPartnerId(currentPartnerEntity.getPartnerEntityId());
        classTypeEntities = classTypeEntitySessionBeanLocal.retrieveAllClassTypeEntity();
        tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
    }

    public void createNewClass(ActionEvent event) {
        try {
            System.out.println("test");
            System.out.println("jsf: class to be persisted is: " + newClassEntity.getClassName());            
            ClassEntity ce = classEntitySessionBeanLocal.createNewClass(newClassEntity, classTypeIdNew, tagIdsNew);
            System.out.println("jsf: successfully created new classentity: " + ce.getClassId());
            currentPartnerEntity.getClassEntity().add(ce);
            if (classEntitiesFiltered != null) {
                classEntitiesFiltered.add(ce);
            }
            classEntities.add(ce);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Class Created", null));
            newClassEntity = new ClassEntity();
        } catch (InputDataValidationException | CreateNewClassException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating new class", null));
            System.out.print(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassManagementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doDelete(ActionEvent event) {
        try {
            classEntityToDelete = (ClassEntity) event.getComponent().getAttributes().get("classEntityToDelete");
            if (classEntityToDelete.getSessionEntities().isEmpty()) {
                classEntitySessionBeanLocal.deleteClass(classEntityToDelete.getClassId());
                classEntities.remove(classEntityToDelete);
                if (classEntitiesFiltered != null) {
                    classEntitiesFiltered.remove(classEntityToDelete);
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Class successfully deleted", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete class: There are sessions still running", null));
            }
        } catch (ClassNotFoundException | DeleteClassEntityException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to delete class", null));
        }
    }

    public void doUpdate(ActionEvent event) {
        classEntityToUpdate = (ClassEntity) event.getComponent().getAttributes().get("classEntityToUpdate");
        locationTypeEnumNew = null;
        tagIdsNew = new ArrayList<>();
        for (TagEntity tag : classEntityToUpdate.getTagEntities()) {
            tagIdsNew.add(tag.getTagId());
        }
    }

    public void updateClass(ActionEvent event) {
        try {
            classEntityToUpdate.setLocationTypeEnum(locationTypeEnumNew);
            classEntitySessionBeanLocal.updateClass(classEntityToUpdate, classTypeIdNew, tagIdsNew);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Class Updated", null));
        } catch (InputDataValidationException | CreateNewClassException | ClassNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to update class", null));
        }
    }

    /*public Integer retrieveClassRating() {
        
        
    }*/
    public LocationTypeEnum[] getEnumLocation() {
        return LocationTypeEnum.values();
    }

    /**
     * @return the classEntities
     */
    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public LocationTypeEnum[] getLocationTypeEnum() {
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

    /**
     * @return the classEntityToDelete
     */
    public ClassEntity getClassEntityToDelete() {
        return classEntityToDelete;
    }

    /**
     * @param classEntityToDelete the classEntityToDelete to set
     */
    public void setClassEntityToDelete(ClassEntity classEntityToDelete) {
        this.classEntityToDelete = classEntityToDelete;
    }

    /**
     * @return the classEntityToUpdate
     */
    public ClassEntity getClassEntityToUpdate() {
        return classEntityToUpdate;
    }

    /**
     * @param classEntityToUpdate the classEntityToUpdate to set
     */
    public void setClassEntityToUpdate(ClassEntity classEntityToUpdate) {
        this.classEntityToUpdate = classEntityToUpdate;
    }

    /**
     * @param classRating the classRating to set
     */
    public void setClassRating(Integer classRating) {
        this.classRating = classRating;
    }

    /**
     * @return the classRating
     */
    public Integer getClassRating() {
        return classRating;
    }

}
