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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import util.exception.PartnerNotFoundException;
import util.security.CryptographicHelper;

/**
 *
 * @author kelly
 */
@Named(value = "imageGalleryManagedBean")
@ViewScoped
public class ImageGalleryManagedBean implements Serializable {

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    private PartnerEntity currentPartnerEntity;

    private List<String> images;

    /**
     * Creates a new instance of PartnerImageGalleryManagedBean
     */
    public ImageGalleryManagedBean() {
        images = new ArrayList<String>();
    }

    @PostConstruct
    public void postConstruct() {

        setCurrentPartnerEntity((PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity"));
        System.out.println("can set currentpartnerentity");
        //if (!currentPartnerEntity.getImages().isEmpty()) {
            setImages(currentPartnerEntity.getImages());
        //}
        System.out.println("can't set images");
    }

    public void handleFileUpload(FileUploadEvent event) throws PartnerNotFoundException {
        
        try {
            //Long newCompanyId = companySessionBeanLocal.createNewCompany(newCompany);
            //Company newC = companySessionBeanLocal.retrieveCompanyByCompanyId(newCompanyId);

//           companyToUpdatePhoto  = (Company) event.getComponent().getAttributes().get("companyToUpdatePhoto");
//           ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("companyToUpdatePhoto", companyToUpdatePhoto); 
            String uploadedFileName = event.getFile().getFileName();

            String newFileName = CryptographicHelper.getInstance().generateUUID().toString() + "_imggallery_" + uploadedFileName;
            String newFilePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("alternatedocroot_1")
                    + System.getProperty("file.separator")
                    + newFileName;

            String docRootFilePath = newFileName;

            //List<String> newImages = new ArrayList<>();
            //newImages.add(docRootFilePath);
            System.out.println(newFileName);
            
            //currentPartnerEntity.getImages().add(newFileName);
            
            //images.add(docRootFilePath);
            
                  
            //System.out.println("can add to current partner images");

            partnerEntitySessionBeanLocal.setImage(currentPartnerEntity, docRootFilePath);
            images.add(docRootFilePath);

            for (String im : getImages()) {
                System.out.println("current image string: " + im);
            }
            
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

    public void updateImageGallery() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Images uploaded successfully", null));
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
     * @return the images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<String> images) {
        this.images = images;
    }
}
