/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import entity.ClassEntity;
import entity.ClassTypeEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import util.enumeration.LocationTypeEnum;

/**
 *
 * @author User
 */
@Named(value = "competitorLibraryModelManagedBean")
@ViewScoped
public class competitorLibraryModelManagedBean implements Serializable {

    @EJB
    private ClassEntitySessionBeanLocal classEntitySessionBean;

    @EJB
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBean;

    

    /**
     * Creates a new instance of CompetitorLibraryManagedBean
     */
    
    private List<ClassTypeEntity> allClassType;
    
    private PieChartModel pieModel;
    private PieChartModel pieModelClassLocation;


    
    public competitorLibraryModelManagedBean() {
    }

    @PostConstruct
    public void postConstruct(){   
        allClassType = classTypeEntitySessionBean.retrieveAllClassTypeEntity();
        for (ClassTypeEntity cte : allClassType){
            cte.getClassEntities().size();
        }
        createClassTypeModel();
        createClassByLocationModel();
    }
    private void createClassByLocationModel(){
        pieModelClassLocation = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        LocationTypeEnum[] temp = LocationTypeEnum.values();
        for (LocationTypeEnum lte : temp){
            values.add(classEntitySessionBean.retrieveAllClassEntityByLocation(lte).size());
            labels.add(lte.toString());
            bgColors.add("rgb(" + String.valueOf((int)(Math.random() * 256)) + ", " + String.valueOf((int)(Math.random() * 256)) + ", " + String.valueOf((int)(Math.random() * 256)) + ")");
        }
        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        pieModelClassLocation.setData(data);
    }
    
    private void createClassTypeModel(){
        
        pieModel = new PieChartModel();
        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> bgColors = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        System.out.println("testing:" + allClassType.size());
        for (ClassTypeEntity cte : allClassType) {
            values.add(cte.getClassEntities().size());
            bgColors.add("rgb(" + String.valueOf((int)(Math.random() * 256)) + ", " + String.valueOf((int)(Math.random() * 256)) + ", " + String.valueOf((int)(Math.random() * 256)) + ")");
            labels.add(cte.getClassTypeName());
        }
        dataSet.setData(values);
        dataSet.setBackgroundColor(bgColors);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        pieModel.setData(data);
    }

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

    /**
     * @return the pieModelClassLocation
     */
    public PieChartModel getPieModelClassLocation() {
        return pieModelClassLocation;
    }

    /**
     * @param pieModelClassLocation the pieModelClassLocation to set
     */
    public void setPieModelClassLocation(PieChartModel pieModelClassLocation) {
        this.pieModelClassLocation = pieModelClassLocation;
    }
    
}
    