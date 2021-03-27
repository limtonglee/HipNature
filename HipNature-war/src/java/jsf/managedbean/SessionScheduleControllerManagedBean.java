/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.SessionEntity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author User
 */
@Named(value = "sessionScheduleControllerManagedBean")
@ViewScoped
public class SessionScheduleControllerManagedBean implements Serializable{

    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;

    @Inject
    private SearchSessionsByNameManagedBean searchSessionsByNameManagedBean;
    
    private List<SessionEntity> sessionEntities;
    
    private ScheduleModel eventModel;
    
    /**
     * Creates a new instance of SessionScheduleControllerManagedBean
     */
    public SessionScheduleControllerManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct(){
        System.out.println("partnet Id: " + searchSessionsByNameManagedBean.getCurrentPartnerEntity().getPartnerEntityId());
        sessionEntities = sessionEntitySessionBeanLocal.retrieveSessionsByPartnerId(searchSessionsByNameManagedBean.getCurrentPartnerEntity().getPartnerEntityId());
        System.out.print("size " + sessionEntities.size());
        eventModel = new DefaultScheduleModel();
        for (SessionEntity se : sessionEntities){
            LocalDateTime sldt = LocalDateTime.ofInstant(se.getStartTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime eldt = LocalDateTime.ofInstant(se.getEndTime().toInstant(), ZoneId.systemDefault());
            DefaultScheduleEvent event =  DefaultScheduleEvent.builder()
                .title(se.getClassEntity().getClassName())
                .startDate(sldt)
                .endDate(eldt)
                .build();
            eventModel.addEvent(event);
        }
    }

    /**
     * @return the sessionEntities
     */
    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    /**
     * @param sessionEntities the sessionEntities to set
     */
    public void setSessionEntities(List<SessionEntity> sessionEntities) {
        this.sessionEntities = sessionEntities;
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
    
}
