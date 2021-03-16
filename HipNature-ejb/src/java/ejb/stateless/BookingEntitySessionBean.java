/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.BookingEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.BookingNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class BookingEntitySessionBean implements BookingEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<BookingEntity> retrieveAllBookings() {
        Query query = em.createQuery("SELECT b FROM BookingEntity B");
        
        return query.getResultList();
    }

    @Override
    public Long createNewBooking(BookingEntity newBooking) {
        
        em.persist(newBooking);
        em.flush();

        return newBooking.getBookingId();
        
    }
        
    @Override
    public BookingEntity retrieveClassByClassId(Long bookingId) throws BookingNotFoundException {
        BookingEntity bookingEntity = em.find(BookingEntity.class, bookingId);
        
        if (bookingEntity != null) {
            return bookingEntity;
        } else {
            throw new BookingNotFoundException("Booking ID " + bookingId + " does not exist!");
        }
    }

    
    
}
