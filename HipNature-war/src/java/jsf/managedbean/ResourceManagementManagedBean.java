/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.InstructorEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.ClassEntity;
import entity.InstructorEntity;
import entity.PartnerEntity;
import entity.SessionEntity;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import util.exception.InputDataValidationException;
import util.exception.InstructorExistsException;
import util.exception.InstructorNotFoundException;
import util.exception.PartnerNotFoundException;
import util.exception.SessionNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author kelly
 */
@Named(value = "resourceManagementManagedBean")
@ViewScoped
public class ResourceManagementManagedBean implements Serializable {

    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    @EJB
    private InstructorEntitySessionBeanLocal instructorEntitySessionBeanLocal;

    @Inject
    private LoginManagedBean loginManagedBean;

    @Inject
    private GetInstructorsForPartnerManagedBean getInstructorsForPartnerManagedBean;

    private InstructorEntity newInstructor;
    private InstructorEntity selectedInstructorToView;
    private InstructorEntity selectedInstructorToUpdate;
    private List<InstructorEntity> instructors;
    private List<InstructorEntity> filteredInstructors;
    private List<Long> sessionsIdsToAddToNewInstructor;
    private List<Long> sessionIdsToAddToSelectedInstructorToUpdate;

    private List<SessionEntity> sessions;
    private List<Long> sessionIdsUpdate;

    private PartnerEntity currentPartnerEntity;
    private List<SessionEntity> partnerListOfSessions;

    public ResourceManagementManagedBean() {
        newInstructor = new InstructorEntity();
        instructors = new ArrayList<>();
        sessionsIdsToAddToNewInstructor = new ArrayList<>();
        sessions = new ArrayList<>();
        partnerListOfSessions = new ArrayList<>();
        /*selectItemsGenreObject = new ArrayList<>();
        selectItemsGenreName = new ArrayList<>();*/
    }

    @PostConstruct
    public void postConstruct() {
        setInstructors(instructorEntitySessionBeanLocal.retrieveAllInstructors());
        currentPartnerEntity = (PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity");
        
        //setPartnerListOfSessions(partnerEntitySessionBeanLocal.retrievePartnerClassesSessions(currentPartnerEntity.getPartnerEntityId()));
        
        //setNewInstructorSessions(sessionEntitySessionBeanLocal.retrieveAllSessions());

        /*System.out.println("*********** In ResourceManagementManagedBean, does retrieving logged in partner work?");
        PartnerEntity currPartner = loginManagedBean.getLoggedInPartner();
        System.out.println("*********** Retrieving logged in partner works" + currPartner.getPartnerEntityId());*/

 /*FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentPartnerEntity", currPartner);*/
 /*setCurrentPartnerEntity((PartnerEntity) ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("currentPartnerEntity"));
        PartnerEntity p = getCurrentPartnerEntity();
        System.out.println("*********** Partner entity is null?");
        System.out.println("*********** PartnerEntity ID: " + p.getPartnerEntityId());
        newInstructor.setPartnerEntity(getCurrentPartnerEntity());
        System.out.println("*********** SUCCESSFULLY SET PARTNER TO INSTRUCTOR. newInstructor.PartnerEntity is not null" + newInstructor.getPartnerEntity().getPartnerEntityName());

        setSessions(sessionEntitySessionBeanLocal.retrieveAllSessions());*/
    }

    public void viewInstructorsByPartner(ActionEvent event) {

        try {

            setCurrentPartnerEntity((PartnerEntity) event.getComponent().getAttributes().get("currentPartnerEntity"));
            ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("currentPartnerEntity", getCurrentPartnerEntity());
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "resourceManagement.xhtml");

        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has ocurred while retrieving the instructors: " + ex.getMessage(), null));
        }
    }

    public void saveNewInstructor(ActionEvent event) throws InstructorExistsException, UnknownPersistenceException {

        try {
            System.out.println("test");
            //PartnerEntity partnerTagged = (PartnerEntity)event.getComponent().getAttributes().get("partnerTagged");
            //System.out.println("Id:" + partnerTagged.getPartnerEntityId());

            // Long newInstructorId = instructorEntitySessionBeanLocal.createNewInstructor(newInstructor, partnerTagged.getPartnerEntityId());
            System.out.println("partner: " + currentPartnerEntity);
            Long newInstructorId = instructorEntitySessionBeanLocal.createNewInstructor(newInstructor, currentPartnerEntity.getPartnerEntityId());
            //getNewInstructor().setInstructorId(newInstructorId);
            getInstructors().add(getNewInstructor());
            currentPartnerEntity.getInstructorEntity().add(newInstructor);

            setNewInstructor(new InstructorEntity());

            /*List<InstructorEntity> instructorsList = loginManagedBean.getInstructorsToDisplay();
            InstructorEntity ins = instructorEntitySessionBeanLocal.retrieveInstructorByInstructorId(newInstructorId);
            instructorsList.add(ins);
            loginManagedBean.setInstructorsToDisplay(instructorsList);*/
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New instructor " + newInstructorId + " added successfully", null));
        } catch (PartnerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new instructor: Partner not found", null));
            /*} catch (InstructorNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new instructor: Instructor not found", null));
        /*} catch (SessionNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new instructor: Sesion not found", null));*/
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has ocurred while creating the new instructor: " + ex.getMessage(), null));
        } catch (InstructorExistsException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has ocurred while creating the new instructor: The instructor email/phone already exists!", null));
        }
    }
    
    public List<SessionEntity> retrievePartnerSessions(ActionEvent event) {
        
        PartnerEntity selectedPartnerEntity = (PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedPartnerEntity");
        List<ClassEntity> partnerClasses = selectedPartnerEntity.getClassEntity();

        if (!partnerClasses.isEmpty()) {
            for (ClassEntity pclass : partnerClasses) {
                List<SessionEntity> classSessions = pclass.getSessionEntities();
                if (!classSessions.isEmpty()) {
                    for (SessionEntity sess : classSessions) {
                        partnerListOfSessions.add(sess);
                    }
                }
            }
        }
        setPartnerListOfSessions(partnerListOfSessions);
        return partnerListOfSessions;
    }

    public void doUpdateInstructor(ActionEvent event) {
        setSelectedInstructorToUpdate((InstructorEntity) event.getComponent().getAttributes().get("instructorToUpdate"));
        setSessionIdsUpdate(new ArrayList<>());

        for (SessionEntity sess : getSelectedInstructorToUpdate().getSessionEntity()) {
            getSessionIdsUpdate().add(sess.getSessionId());
        }
    }

    public void updateInstructor(ActionEvent event) {
        try {
            instructorEntitySessionBeanLocal.updateInstructor(getSelectedInstructorToUpdate(), sessionIdsToAddToSelectedInstructorToUpdate);
            selectedInstructorToUpdate.setInstructorName(selectedInstructorToUpdate.getInstructorName());
            getSelectedInstructorToUpdate().getSessionEntity().clear();
            for (SessionEntity s : getSessions()) {
                if (getSessionIdsUpdate().contains(s.getSessionId())) {
                    getSelectedInstructorToUpdate().getSessionEntity().add(s);
                }
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Instructor updated successfully", null));

        } catch (InstructorNotFoundException | SessionNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating project: " + ex.getMessage(), null));
        }
    }

    public void deleteInstructor(ActionEvent event) {

        try {
            InstructorEntity instructorToDelete = (InstructorEntity) event.getComponent().getAttributes().get("instructorToDelete");

            /*List<InstructorEntity> ins = getInstructorsForPartnerManagedBean.getInstructorsForCompany();
            ins.remove(instructorToDelete);
            getInstructorsForPartnerManagedBean.setInstructorsForCompany(ins);

            List<InstructorEntity> insList = loginManagedBean.getInstructorsToDisplay();
            insList.remove(instructorToDelete);
            loginManagedBean.setInstructorsToDisplay(insList);*/
 /*resourceManagementManagedBean.currentPartnerEntity.instructorEntity*/
            
            /*PartnerEntity partner = instructorToDelete.getPartnerEntity();
            List<InstructorEntity> partnerInstructors = partner.getInstructorEntity();
            partnerInstructors.remove(instructorToDelete);*/
            
            currentPartnerEntity.getInstructorEntity().remove(instructorToDelete);

            instructorEntitySessionBeanLocal.deleteInstructor(instructorToDelete.getInstructorId());
            getInstructors().remove(instructorToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Instructor deleted successfully", null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    /**
     * @return the newInstructor
     */
    public InstructorEntity getNewInstructor() {
        return newInstructor;
    }

    /**
     * @param newInstructor the newInstructor to set
     */
    public void setNewInstructor(InstructorEntity newInstructor) {
        this.newInstructor = newInstructor;
    }

    /**
     * @return the selectedInstructorToView
     */
    public InstructorEntity getSelectedInstructorToView() {
        return selectedInstructorToView;
    }

    /**
     * @param selectedInstructorToView the selectedInstructorToView to set
     */
    public void setSelectedInstructorToView(InstructorEntity selectedInstructorToView) {
        this.selectedInstructorToView = selectedInstructorToView;
    }

    /**
     * @return the selectedInstructorToUpdate
     */
    public InstructorEntity getSelectedInstructorToUpdate() {
        return selectedInstructorToUpdate;
    }

    /**
     * @param selectedInstructorToUpdate the selectedInstructorToUpdate to set
     */
    public void setSelectedInstructorToUpdate(InstructorEntity selectedInstructorToUpdate) {
        this.selectedInstructorToUpdate = selectedInstructorToUpdate;
    }

    /**
     * @return the instructors
     */
    public List<InstructorEntity> getInstructors() {
        return instructors;
    }

    /**
     * @param instructors the instructors to set
     */
    public void setInstructors(List<InstructorEntity> instructors) {
        this.instructors = instructors;
    }

    /**
     * @return the filteredInstructors
     */
    public List<InstructorEntity> getFilteredInstructors() {
        return filteredInstructors;
    }

    /**
     * @param filteredInstructors the filteredInstructors to set
     */
    public void setFilteredInstructors(List<InstructorEntity> filteredInstructors) {
        this.filteredInstructors = filteredInstructors;
    }

    /**
     * @return the sessionsIdsToAddToNewInstructor
     */
    public List<Long> getSessionsIdsToAddToNewInstructor() {
        return sessionsIdsToAddToNewInstructor;
    }

    /**
     * @param sessionsIdsToAddToNewInstructor the
     * sessionsIdsToAddToNewInstructor to set
     */
    public void setSessionsIdsToAddToNewInstructor(List<Long> sessionsIdsToAddToNewInstructor) {
        this.sessionsIdsToAddToNewInstructor = sessionsIdsToAddToNewInstructor;
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
     * @return the sessions
     */
    public List<SessionEntity> getSessions() {
        return sessions;
    }

    /**
     * @param sessions the sessions to set
     */
    public void setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
    }

    /**
     * @return the sessionIdsUpdate
     */
    public List<Long> getSessionIdsUpdate() {
        return sessionIdsUpdate;
    }

    /**
     * @param sessionIdsUpdate the sessionIdsUpdate to set
     */
    public void setSessionIdsUpdate(List<Long> sessionIdsUpdate) {
        this.sessionIdsUpdate = sessionIdsUpdate;
    }

    /**
     * @return the sessionIdsToAddToSelectedInstructorToUpdate
     */
    public List<Long> getSessionIdsToAddToSelectedInstructorToUpdate() {
        return sessionIdsToAddToSelectedInstructorToUpdate;
    }

    /**
     * @param sessionIdsToAddToSelectedInstructorToUpdate the
     * sessionIdsToAddToSelectedInstructorToUpdate to set
     */
    public void setSessionIdsToAddToSelectedInstructorToUpdate(List<Long> sessionIdsToAddToSelectedInstructorToUpdate) {
        this.sessionIdsToAddToSelectedInstructorToUpdate = sessionIdsToAddToSelectedInstructorToUpdate;
    }

    /**
     * @return the partnerListOfSessions
     */
    public List<SessionEntity> getPartnerListOfSessions() {
        return partnerListOfSessions;
    }

    /**
     * @param partnerListOfSessions the partnerListOfSessions to set
     */
    public void setPartnerListOfSessions(List<SessionEntity> partnerListOfSessions) {
        this.partnerListOfSessions = partnerListOfSessions;
    }

}
