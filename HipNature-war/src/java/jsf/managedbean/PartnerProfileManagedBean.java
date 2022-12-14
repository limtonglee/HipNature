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
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import util.exception.DeletePartnerException;
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

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    private PartnerEntity currentPartnerEntity;
    
    @Inject
    private LoginManagedBean loginManagedBean;
    
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

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
    
    public void handleFileUpload(FileUploadEvent event) throws PartnerNotFoundException {
       try {
            //Long newCompanyId = companySessionBeanLocal.createNewCompany(newCompany);
            //Company newC = companySessionBeanLocal.retrieveCompanyByCompanyId(newCompanyId);

//           companyToUpdatePhoto  = (Company) event.getComponent().getAttributes().get("companyToUpdatePhoto");
//           ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("companyToUpdatePhoto", companyToUpdatePhoto); 
            String uploadedFileName = event.getFile().getFileName();

            String newFileName = currentPartnerEntity.getPartnerEntityName() + "_logo_" + uploadedFileName;
//            String newFileName = currentPartnerEntity.getPartnerEntityName();
            String newFilePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("alternatedocroot_1")
                    + System.getProperty("file.separator")
                    + newFileName;

            String docRootFilePath = "/uploadedFiles/" + newFileName;
            
            System.out.println(newFileName);
            currentPartnerEntity.setProfilePicString(newFileName); 
            partnerEntitySessionBeanLocal.setProfilePicString(currentPartnerEntity, newFileName);
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
    
    public void deletePartner(ActionEvent event) {
        try {
            partnerEntitySessionBeanLocal.deletePartner(currentPartnerEntity.getPartnerEntityId());
            
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Partner Account deleted successfully", null));
            
            loginManagedBean.logout(event);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (PartnerNotFoundException | DeletePartnerException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting partner account: " + ex.getMessage(), null));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void doChangePassword(ActionEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/changePassword.xhtml");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }

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
    
    public void changePassword(ActionEvent event) {

      

        if (oldPassword != null || newPassword != null || confirmPassword != null) {
            oldPassword = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(oldPassword + currentPartnerEntity.getSalt()));
        } else {
     
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password cannot be null", null));
        }

        System.out.println(currentPartnerEntity.getPassword()); //in hash 
        System.out.println(oldPassword);
        if (!oldPassword.equals(currentPartnerEntity.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Old Password is invalid", null));
        } else if (!newPassword.equals(confirmPassword)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password validation Error: Passwords do not match", null));
        } else {

            try {
                partnerEntitySessionBeanLocal.updatePassword(currentPartnerEntity, newPassword);


                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Password changed successfully!", null));
            } catch (PartnerNotFoundException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating company: " + ex.getMessage(), null));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
            }

            //selectedCompanyToUpdate.setPassword(newPassword);
            System.out.println(newPassword);
            System.out.println(oldPassword);
            System.out.println(currentPartnerEntity.getPassword());
            System.out.println(currentPartnerEntity.getUsername());
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password changed successfully!", null));
        }

    }

}
