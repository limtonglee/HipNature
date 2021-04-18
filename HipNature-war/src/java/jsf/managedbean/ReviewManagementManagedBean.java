/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.ReviewEntitySessionBeanLocal;
import entity.ClassEntity;
import static entity.ClassEntity_.classId;
import entity.PartnerEntity;
import entity.ReviewEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author kelly
 */
@Named(value = "reviewManagementManagedBean")
@ViewScoped
public class ReviewManagementManagedBean implements Serializable {

    @EJB(name = "ClassEntitySessionBeanLocal")
    private ClassEntitySessionBeanLocal classEntitySessionBeanLocal;

    @EJB(name = "ReviewEntitySessionBeanLocal")
    private ReviewEntitySessionBeanLocal reviewEntitySessionBeanLocal;

    private List<ReviewEntity> reviews;

    private PartnerEntity currentPartnerEntity;

    private List<ReviewEntity> filteredReviews;

    private List<ClassEntity> partnerClasses;

    /**
     * Creates a new instance of ReviewManagementManagedBean
     */
    public ReviewManagementManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        setCurrentPartnerEntity((PartnerEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentPartnerEntity"));
        setReviews(reviewEntitySessionBeanLocal.retrieveReviewsByPartnerId(currentPartnerEntity.getPartnerEntityId()));
        setPartnerClasses(classEntitySessionBeanLocal.retrieveAllClassesByPartnerId(currentPartnerEntity.getPartnerEntityId()));

    }

    public void updateAllRatings() throws ClassNotFoundException {
        try {
            for (ClassEntity cls : partnerClasses) {
                Long classId = cls.getClassId();
                reviewEntitySessionBeanLocal.updateAvgRating(classId);
            }
        } catch (ClassNotFoundException ex) {
            throw new ClassNotFoundException("Class ID " + classId + " does not exist!");
        }
    }

    /**
     * @return the reviews
     */
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
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
     * @return the filteredReviews
     */
    public List<ReviewEntity> getFilteredReviews() {
        return filteredReviews;
    }

    /**
     * @param filteredReviews the filteredReviews to set
     */
    public void setFilteredReviews(List<ReviewEntity> filteredReviews) {
        this.filteredReviews = filteredReviews;
    }

    /**
     * @return the partnerClasses
     */
    public List<ClassEntity> getPartnerClasses() {
        return partnerClasses;
    }

    /**
     * @param partnerClasses the partnerClasses to set
     */
    public void setPartnerClasses(List<ClassEntity> partnerClasses) {
        this.partnerClasses = partnerClasses;
    }

}
