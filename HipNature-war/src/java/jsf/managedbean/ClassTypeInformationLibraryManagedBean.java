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
import javax.inject.Named;
import javax.faces.view.ViewScoped;

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

    @PostConstruct
    public void postConstruct(){
        classTypeEntities = classTypeEntitySessionBeanLocal.retrieveAllClassTypeEntity();
    }
    public ClassTypeInformationLibraryManagedBean() {
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
    
}
