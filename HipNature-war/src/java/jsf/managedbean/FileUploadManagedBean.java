/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import entity.PartnerEntity;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author kelly
 */
@Named(value = "fileUploadManagedBean")
@RequestScoped
public class FileUploadManagedBean {

   // PartnerEntitySessionBean partnerEntitySessionBean = lookupPartnerEntitySessionBeanBean();

    private PartnerEntity currentPartner; 
   
    private UploadedFile file;


    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    public void upload() {
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null,message);
        }
    }
     
    /*public void handleFileUpload(FileUploadEvent event) {
        
       
        
       currentCompany = (Company)event.getComponent().getAttributes().get("photoToUpdate");

        
    } 
    
     public void doUpdateCompany(ActionEvent event)
    {
        currentCompany = (Company)event.getComponent().getAttributes().get("photoToUpdate");
    }

    private PartnerEntitySessionBean lookupPartnerEntitySessionBeanBean() {
        try {
            Context c = new InitialContext();
            return (PartnerEntitySessionBean) c.lookup("java:global/HipNature/HipNature-ejb/PartnerEntitySessionBean!ejb.stateless.PartnerEntitySessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }*/

    /**
     * Creates a new instance of FileUploadManagedBean
     */
    
    
}