/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.RecordEntitySessionBeanLocal;
import entity.RecordEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author limtonglee
 */
@Named(value = "recordManagementManagedBean")
@ViewScoped
public class RecordManagementManagedBean implements Serializable{

    @EJB
    private RecordEntitySessionBeanLocal recordEntitySessionBeanLocal;

    private RecordEntity newRecord;
    private List<RecordEntity> records;
    
    /**
     * Creates a new instance of RecordManagementManagedBean
     */
    public RecordManagementManagedBean() {
        newRecord = new RecordEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setRecords(recordEntitySessionBeanLocal.retrieveAllRecords());
    }
    
     public void createNewRecord(ActionEvent event)
    {
        Long newRecordId = recordEntitySessionBeanLocal.createNewRecord(newRecord);
        
        newRecord.setRecordId(newRecordId); 
        records.add(newRecord);
        
        newRecord = new RecordEntity();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New record created successfully", "New Record ID: " + newRecordId));
    
        System.out.println("*********** CreateNewRecordManagedBean.createNewRecord: " + event.getComponent().getAttributes().get("test"));
    }

    public RecordEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(RecordEntity newRecord) {
        this.newRecord = newRecord;
    }

    public List<RecordEntity> getRecords() {
        return records;
    }

    public void setRecords(List<RecordEntity> records) {
        this.records = records;
    }
    
}
