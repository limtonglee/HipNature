/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.InstructorEntitySessionBeanLocal;
import entity.InstructorEntity;
import entity.PartnerEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import util.exception.InstructorNotFoundException;

/**
 *
 * @author kelly
 */
@Named(value = "getInstructorsForPartnerManagedBean")
@ViewScoped
public class GetInstructorsForPartnerManagedBean implements Serializable {

    @EJB
    private InstructorEntitySessionBeanLocal instructorEntitySessionBeanLocal;

    private PartnerEntity partnerToView;
    private List<InstructorEntity> instructorsForCompany;

    public GetInstructorsForPartnerManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {

        setPartnerToView((PartnerEntity) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("partnerToView"));
        setInstructorsForCompany((List<InstructorEntity>) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("instructorsForCompany"));

    }

    public void viewInstructorByPartner(ActionEvent event) {

        try {

            setPartnerToView((PartnerEntity) event.getComponent().getAttributes().get("partnerToView"));
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("partnerToView", getPartnerToView());

            instructorsForCompany = instructorEntitySessionBeanLocal.retrieveInstructorsByPartner(partnerToView.getPartnerEntityId());
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("instructorsForCompany", instructorsForCompany);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "resourceManagement.xhtml");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has ocurred while retrieving the instructors: " + ex.getMessage(), null));
        }
    }

    /**
     * @return the partnerToView
     */
    public PartnerEntity getPartnerToView() {
        return partnerToView;
    }

    /**
     * @param partnerToView the partnerToView to set
     */
    public void setPartnerToView(PartnerEntity partnerToView) {
        this.partnerToView = partnerToView;
    }

    /**
     * @return the instructorsForCompany
     */
    public List<InstructorEntity> getInstructorsForCompany() {
        return instructorsForCompany;
    }

    /**
     * @param instructorsForCompany the instructorsForCompany to set
     */
    public void setInstructorsForCompany(List<InstructorEntity> instructorsForCompany) {
        this.instructorsForCompany = instructorsForCompany;
    }

}
