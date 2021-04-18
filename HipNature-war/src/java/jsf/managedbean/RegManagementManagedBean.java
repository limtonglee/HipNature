/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.ClassEntitySessionBeanLocal;
import entity.BookingEntity;
import entity.ClassEntity;
import entity.PartnerEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author kelly
 */
@Named(value = "regManagementManagedBean")
@ViewScoped
public class RegManagementManagedBean implements Serializable {

    /**
     * @return the filteredBookings
     */
    public List<BookingEntity> getFilteredBookings() {
        return filteredBookings;
    }

    /**
     * @param filteredBookings the filteredBookings to set
     */
    public void setFilteredBookings(List<BookingEntity> filteredBookings) {
        this.filteredBookings = filteredBookings;
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

    @EJB(name = "TransactionEntitySessionBeanLocal")
    private BookingEntitySessionBeanLocal bookingEntitySessionBeanLocal;

    @EJB(name = "ClassEntitySessionBeanLocal")
    private ClassEntitySessionBeanLocal classEntitySessionBeanLocal;

    private List<BookingEntity> bookings;

    private List<ClassEntity> classEntities;

    private PartnerEntity currentPartnerEntity;
    
    private List<BookingEntity> filteredBookings;

    /**
     * Creates a new instance of regManagementManagedBean
     */
    public RegManagementManagedBean() {
        classEntities = new ArrayList<>();
    }

    @PostConstruct
    public void postConstruct() {

        //setBookings(bookingEntitySessionBeanLocal.retrieveBookingsByPartnerId(currentPartnerEntity.getPartnerEntityId()));

    }

    /**
     * @return the classEntities
     */
    public List<ClassEntity> getClassEntities() {
        return classEntities;
    }

    /**
     * @param classEntities the classEntities to set
     */
    public void setClassEntities(List<ClassEntity> classEntities) {
        this.classEntities = classEntities;
    }

    /**
     * @return the bookings
     */
    public List<BookingEntity> getBookings() {
        return bookings;
    }

    /**
     * @param bookings the bookings to set
     */
    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

}
