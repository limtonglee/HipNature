/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import entity.ClassTypeEntity;
import entity.PartnerEntity;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author User
 */
@Named(value = "competitorLibraryManagedBean")
@ViewScoped
public class CompetitorLibraryManagedBean implements Serializable {

    @EJB
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBean;

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    /**
     * Creates a new instance of CompetitorLibraryManagedBean
     */
    
    
    private List<PartnerEntity> competitorPartner;
    
    private List<ClassTypeEntity> allClassType;
    
    private PieChartModel pieModel;

    
    public CompetitorLibraryManagedBean() {
    }

    @PostConstruct
    public void postConstruct(){   
        allClassType = classTypeEntitySessionBean.retrieveAllClassTypeEntity();
        competitorPartner = partnerEntitySessionBeanLocal.retrieveAllPartners();
    }
    /**
     * @return the competitorPartner
     */
    public List<PartnerEntity> getCompetitorPartner() {
        return competitorPartner;
    }

    /**
     * @param competitorPartner the competitorPartner to set
     */
    public void setCompetitorPartner(List<PartnerEntity> competitorPartner) {
        this.competitorPartner = competitorPartner;
    }

    /**
     * @return the allClassType
     */
    public List<ClassTypeEntity> getAllClassType() {
        return allClassType;
    }

    /**
     * @param allClassType the allClassType to set
     */
    public void setAllClassType(List<ClassTypeEntity> allClassType) {
        this.allClassType = allClassType;
    }

    /**
     * @return the pieModel
     */
    public PieChartModel getPieModel() {
        return pieModel;
    }

    /**
     * @param pieModel the pieModel to set
     */
    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
}
