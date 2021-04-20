/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.PartnerEntitySessionBean;
import entity.PartnerEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.Facelet;
import util.exception.InputDataValidationException;

/**
 *
 * @author User
 */
@Named(value = "partnerManagedBean")
@ViewScoped
public class PartnerManagedBean implements Serializable{

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBean;
    
    private List<PartnerEntity> partnerEntities;
    
    private PartnerEntity newPartnerEntity;
    /**
     * Creates a new instance of PartnerManagedBean
     */
    public PartnerManagedBean() {
        System.out.println("constructor");
        newPartnerEntity = new PartnerEntity();
    }
    
    public void createNewPartner(ActionEvent event){
        System.out.println("In createNewPArtner");
        try  {
            System.out.println("In createNewPArtner2");
            System.out.println(newPartnerEntity.getAddress());
            System.out.println(newPartnerEntity.getEmail());
            System.out.println(newPartnerEntity.getPhone());
            System.out.println(newPartnerEntity.getPassword());
            System.out.println(newPartnerEntity.getPartnerEntityName());
            PartnerEntity pe = partnerEntitySessionBean.createNewPartner(newPartnerEntity);
            partnerEntities.add(pe);
            newPartnerEntity = new PartnerEntity();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Account Created", null));
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating an account", null));
        }
    }
    
    public void updatePartnerClass(PartnerEntity partnerEntityToUpdate){
    }

    /**
     * @return the newPartnerEntity
     */
    public PartnerEntity getNewPartnerEntity() {
        return newPartnerEntity;
    }

    /**
     * @param newPartnerEntity the newPartnerEntity to set
     */
    public void setNewPartnerEntity(PartnerEntity newPartnerEntity) {
        this.newPartnerEntity = newPartnerEntity;
    }

    /**
     * @return the partnerEntitySessionBean
     */
    public PartnerEntitySessionBean getPartnerEntitySessionBean() {
        return partnerEntitySessionBean;
    }

    /**
     * @param partnerEntitySessionBean the partnerEntitySessionBean to set
     */
    public void setPartnerEntitySessionBean(PartnerEntitySessionBean partnerEntitySessionBean) {
        this.partnerEntitySessionBean = partnerEntitySessionBean;
    }

    /**
     * @return the partnerEntities
     */
    public List<PartnerEntity> getPartnerEntities() {
        return partnerEntities;
    }

    /**
     * @param partnerEntities the partnerEntities to set
     */
    public void setPartnerEntities(List<PartnerEntity> partnerEntities) {
        this.partnerEntities = partnerEntities;
    }
    
}
