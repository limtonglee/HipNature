/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.TagEntitySessionBeanLocal;
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
import util.exception.InputDataValidationException;

/**
 *
 * @author User
 */
@Named(value = "tagInformationLibraryManagedBean")
@ViewScoped
public class TagInformationLibraryManagedBean implements Serializable{

    /**
     * @return the tagEntitiesFiltered
     */
    public List<TagEntity> getTagEntitiesFiltered() {
        return tagEntitiesFiltered;
    }

    /**
     * @param tagEntitiesFiltered the tagEntitiesFiltered to set
     */
    public void setTagEntitiesFiltered(List<TagEntity> tagEntitiesFiltered) {
        this.tagEntitiesFiltered = tagEntitiesFiltered;
    }

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    //New Tag Entity
    private TagEntity tagEntityNew;
    //Filter
    private List<TagEntity> tagEntitiesFiltered;
    
    private List<TagEntity> tagEntities;
    /**
     * Creates a new instance of TagInformationLibraryManagedBean
     */
    public TagInformationLibraryManagedBean() {
        tagEntityNew = new TagEntity();
    }

    @PostConstruct
    public void postConstruct(){
        tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
        tagEntities.size();
    }
    
    public void createNewTagEntity(ActionEvent event) {
        try{
            TagEntity te = tagEntitySessionBeanLocal.createNewTag(tagEntityNew);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Tag Created: " + te.getTagName(), null));
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating new class: " + ex, null));
        }
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
     * @return the tagEntityNew
     */
    public TagEntity getTagEntityNew() {
        return tagEntityNew;
    }

    /**
     * @param tagEntityNew the tagEntityNew to set
     */
    public void setTagEntityNew(TagEntity tagEntityNew) {
        this.tagEntityNew = tagEntityNew;
    }
    
}
