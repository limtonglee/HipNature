/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.PartnerEntitySessionBean;
import entity.PartnerEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import util.exception.InputDataValidationException;
import util.exception.PartnerNotFoundException;
import util.security.CryptographicHelper;

/**
 *
 * @author kelly
 */
@Named(value = "partnerProfileManagedBean")
@ViewScoped
public class PartnerProfileManagedBean implements Serializable {

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    private PartnerEntity currentPartnerEntity;

    /**
     * Creates a new instance of PartnerProfileManagedBean
     */
    public PartnerProfileManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {

        setCurrentPartnerEntity((PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity"));

    }
    
    public void updatePartner(ActionEvent event) throws InputDataValidationException {

        try {
            partnerEntitySessionBeanLocal.updatePartner(currentPartnerEntity);
            setCurrentPartnerEntity(currentPartnerEntity);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partner Details updated successfully", null));

        } catch (PartnerNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating project: " + ex.getMessage(), null));
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
       try {
            //Long newCompanyId = companySessionBeanLocal.createNewCompany(newCompany);
            //Company newC = companySessionBeanLocal.retrieveCompanyByCompanyId(newCompanyId);

//           companyToUpdatePhoto  = (Company) event.getComponent().getAttributes().get("companyToUpdatePhoto");
//           ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("companyToUpdatePhoto", companyToUpdatePhoto); 
            String uploadedFileName = event.getFile().getFileName();

            String newFileName = CryptographicHelper.getInstance().generateUUID().toString() + "_profile_" + uploadedFileName;
            String newFilePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("alternatedocroot_1")
                    + System.getProperty("file.separator")
                    + newFileName;

            String docRootFilePath = "/uploadedFiles/" + newFileName;
            
            System.out.println(newFileName);
            currentPartnerEntity.setProfilePicString(newFileName); 
            //companySessionBeanLocal.updateCompany(companyToUpdatePhoto);

            File file = new File(newFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];

            InputStream inputStream = event.getFile().getInputStream();

            while (true) {
                a = inputStream.read(buffer);

                if (a < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, a);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        } //catch (UpdateCompanyException | CompanyNotFoundException ex) {
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        //}
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

}
