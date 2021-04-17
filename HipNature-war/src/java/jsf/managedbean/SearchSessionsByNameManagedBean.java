package jsf.managedbean;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.InstructorEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.ClassEntity;
import entity.ClassTypeEntity;
import entity.InstructorEntity;
import entity.PartnerEntity;
import entity.SessionEntity;
import entity.TagEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.TreeNode;
import util.enumeration.LocationTypeEnum;
import util.exception.CreateNewClassException;
import util.exception.DeleteClassEntityException;
import util.exception.DeleteSessionEntityException;
import util.exception.InputDataValidationException;
import util.exception.InstructorNotFoundException;
import util.exception.SessionNotFoundException;
import util.exception.TagNotFoundException;
import util.exception.UpdateSessionException;

@Named(value = "searchSessionsByNameManagedBean")
@ViewScoped
public class SearchSessionsByNameManagedBean implements Serializable {

    /**
     * @return the sessionEntityToDelete
     */
    public SessionEntity getSessionEntityToDelete() {
        return sessionEntityToDelete;
    }

    /**
     * @param sessionEntityToDelete the sessionEntityToDelete to set
     */
    public void setSessionEntityToDelete(SessionEntity sessionEntityToDelete) {
        this.sessionEntityToDelete = sessionEntityToDelete;
    }

    public List<InstructorEntity> getInstructorEntities() {
        return instructorEntities;
    }

    /**
     * @param instructorEntities the instructorEntities to set
     */
    public void setInstructorEntities(List<InstructorEntity> instructorEntities) {
        this.instructorEntities = instructorEntities;
    }

    @EJB
    private InstructorEntitySessionBeanLocal instructorEntitySessionBean;

    @EJB
    private ClassEntitySessionBeanLocal classEntitySessionBeanLocal;
    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBean;
    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    private String searchString;
    private List<SessionEntity> sessionEntities;
    private List<Long> selectedTagIds;
    private List<SelectItem> selectItems;
    private String location;

    private SessionEntity newSessionEntity;
    private Long newInstructorId;
    private Long newClassId;
    private List<ClassEntity> classEntities;
    private List<InstructorEntity> instructorEntities;

    private LocationTypeEnum locationTypeEnumNew;
    private PartnerEntity currentPartnerEntity;
    private SessionEntity sessionEntityToUpdate;
    private SessionEntity sessionEntityToDelete;

    public SearchSessionsByNameManagedBean() {
        location = "ALL";
        currentPartnerEntity = (PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity");
        System.out.println("Here");
        newSessionEntity = new SessionEntity();
        sessionEntityToUpdate = new SessionEntity();

    }

    @PostConstruct
    public void postConstruct() {
        classEntities = classEntitySessionBeanLocal.retrieveAllClassesByPartnerId(currentPartnerEntity.getPartnerEntityId());
        System.out.println("PartnerEntiy" + currentPartnerEntity.getPartnerEntityId());
        instructorEntities = instructorEntitySessionBean.retrieveInstructorsByPartner(currentPartnerEntity.getPartnerEntityId());

        if (location == null) {
            location = "ALL";
        }
        searchString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionSearchString");

        if (searchString == null || searchString.trim().length() == 0) {
            sessionEntities = sessionEntitySessionBean.retrieveAllSessions();
            System.out.println(sessionEntities.size());

        } else {
            sessionEntities = sessionEntitySessionBean.searchSessionByName(searchString);
            System.out.println(sessionEntities.size());
        }

        List<TagEntity> tagEntities = tagEntitySessionBeanLocal.retrieveAllTags();
        selectItems = new ArrayList<>();

        for (TagEntity tagEntity : tagEntities) {
            selectItems.add(new SelectItem(tagEntity.getTagId(), tagEntity.getTagName(), tagEntity.getTagName()));
        }

        // Optional demonstration of the use of custom converter
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TagEntityConverter_tagEntities", tagEntities);
        location = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionLocation");
        selectedTagIds = (List<Long>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionFilterTags");

        searchSession();
    }

    public void searchSession() {
        if (searchString == null || searchString.trim().length() == 0) {
            sessionEntities = sessionEntitySessionBean.retrieveAllSessions();
            System.out.println(sessionEntities.size());

        } else {
            sessionEntities = sessionEntitySessionBean.searchSessionByName(searchString);
            System.out.println(sessionEntities.size());

        }

        if (location == null) {
            System.out.println("Location" + location);

            location = "ALL";
            System.out.println("Location" + location);

        }
        System.out.println("Location" + location);
        List<SessionEntity> sessionEntities2 = sessionEntitySessionBean.filterSessionsByTags(selectedTagIds, location);

//               List<SessionEntity> listTwoCopy = new ArrayList<>(sessionEntities2);
//        listTwoCopy.removeAll(sessionEntities);
//        sessionEntities.addAll(listTwoCopy);
        sessionEntities.retainAll(sessionEntities2);

    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionSearchString", searchString);
    }

    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    public void setSessionEntities(List<SessionEntity> productEntities) {
        this.sessionEntities = productEntities;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionLocation", location);

    }

    public List<Long> getSelectedTagIds() {
        return selectedTagIds;
    }

    public void setSelectedTagIds(List<Long> selectedTagIds) {
        this.selectedTagIds = selectedTagIds;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionFilterTags", selectedTagIds);
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public void doUpdate(ActionEvent event) {
        try {
            sessionEntityToUpdate = (SessionEntity) event.getComponent().getAttributes().get("sessionEntityToUpdate");
            sessionEntityToUpdate = sessionEntitySessionBean.retrieveSessionBySessionId(sessionEntityToUpdate.getSessionId());
            locationTypeEnumNew = null;
            newInstructorId = null;
        } catch (SessionNotFoundException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateSession(ActionEvent event) throws IOException {
        try {
            sessionEntityToUpdate.setLocationTypeEnum(locationTypeEnumNew);
            sessionEntityToUpdate.setInstructor(instructorEntitySessionBean.retrieveInstructorByInstructorId(newInstructorId));
            System.out.println(sessionEntityToUpdate.getLocationTypeEnum());
            System.out.println(sessionEntityToUpdate);
            sessionEntities.remove(sessionEntityToUpdate);
            sessionEntitySessionBean.updateSession(sessionEntityToUpdate, selectedTagIds);
            sessionEntities.add(sessionEntityToUpdate);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Session Updated", null));
            searchSession();

        } catch (SessionNotFoundException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagNotFoundException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UpdateSessionException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputDataValidationException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstructorNotFoundException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void doDelete(ActionEvent event) {
        try {
            sessionEntityToDelete = (SessionEntity) event.getComponent().getAttributes().get("sessionEntityToDelete");
            sessionEntitySessionBean.deleteSession(sessionEntityToDelete.getSessionId());
            sessionEntities.remove(sessionEntityToDelete);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Session successfully deleted", null));
        } catch (DeleteSessionEntityException ex) {
            Logger.getLogger(SearchSessionsByNameManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InstructorEntitySessionBeanLocal getInstructorEntitySessionBean() {
        return instructorEntitySessionBean;
    }

    public void setInstructorEntitySessionBean(InstructorEntitySessionBeanLocal instructorEntitySessionBean) {
        this.instructorEntitySessionBean = instructorEntitySessionBean;
    }

    public SessionEntity getNewSessionEntity() {
        return newSessionEntity;
    }

    public void setNewSessionEntity(SessionEntity newSessionEntity) {
        this.newSessionEntity = newSessionEntity;
    }

    public Long getNewInstructorId() {
        return newInstructorId;
    }

    public void setNewInstructorId(Long newInstructorId) {
        this.newInstructorId = newInstructorId;
    }

    public Long getNewClassId() {
        return newClassId;
    }

    public void setNewClassId(Long newClassId) {
        this.newClassId = newClassId;
    }

    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    public LocationTypeEnum[] getLocationTypeEnum() {
        return LocationTypeEnum.values();
    }

    public LocationTypeEnum getLocationTypeEnumNew() {
        return locationTypeEnumNew;
    }

    public void setLocationTypeEnumNew(LocationTypeEnum locationTypeEnumNew) {
        this.locationTypeEnumNew = locationTypeEnumNew;
    }

    public PartnerEntity getCurrentPartnerEntity() {
        return currentPartnerEntity;
    }

    public void setCurrentPartnerEntity(PartnerEntity currentPartnerEntity) {
        this.currentPartnerEntity = currentPartnerEntity;
    }

    public SessionEntity getSessionEntityToUpdate() {
        System.out.println("Get Session" + sessionEntityToUpdate.getSessionId());
        return sessionEntityToUpdate;
    }

    public void setSessionEntityToUpdate(SessionEntity sessionEntityToUpdate) {
        this.sessionEntityToUpdate = sessionEntityToUpdate;

        System.out.println("Set Session" + this.sessionEntityToUpdate.getSessionId());
        System.out.println("Set Session" + this.sessionEntityToUpdate.getMaxCapacity());

    }

    public void createNewSession(ActionEvent event) throws InstructorNotFoundException {
        try {
            newSessionEntity.setInstructor(instructorEntitySessionBean.retrieveInstructorByInstructorId(newInstructorId));
            newSessionEntity.setLocationTypeEnum(locationTypeEnumNew);
            newSessionEntity.setClassEntity(classEntitySessionBeanLocal.retrieveClassByClassId(newClassId));
            newSessionEntity.setEndTime();
            newSessionEntity.setStatus("ACTIVE");
            Long ce = sessionEntitySessionBean.createNewSession(newSessionEntity);
            System.out.println("Created" + ce);
            sessionEntities.add(newSessionEntity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Session Created", null));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClassManagementManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
