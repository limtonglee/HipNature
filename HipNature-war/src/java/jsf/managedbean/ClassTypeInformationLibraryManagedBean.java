/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import entity.ClassTypeEntity;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "classTypeInformationLibraryManagedBean")
@ViewScoped
public class ClassTypeInformationLibraryManagedBean implements Serializable{

    @EJB
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBeanLocal;

    private List<ClassTypeEntity> classTypeEntities;

    //New
    private ClassTypeEntity classTypeEntityNew;
    @PostConstruct
    public void postConstruct(){
        classTypeEntities = classTypeEntitySessionBeanLocal.retrieveAllClassTypeEntity();
    }
    public ClassTypeInformationLibraryManagedBean() {
        classTypeEntityNew = new ClassTypeEntity();
    }

    public void createNewClassTypeEntity(ActionEvent event) {
        try{
            ClassTypeEntity cte = classTypeEntitySessionBeanLocal.createClassType(classTypeEntityNew);
            classTypeEntities.add(cte);
            classTypeEntityNew = new ClassTypeEntity();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Tag Created: " + cte.getClassTypeName(), null));
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating new class: " + ex, null));
        }
    }
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
     * @return the classTypeEntityNew
     */
    public ClassTypeEntity getClassTypeEntityNew() {
        return classTypeEntityNew;
    }

    /**
     * @param classTypeEntityNew the classTypeEntityNew to set
     */
    public void setClassTypeEntityNew(ClassTypeEntity classTypeEntityNew) {
        this.classTypeEntityNew = classTypeEntityNew;
    }
    
}
