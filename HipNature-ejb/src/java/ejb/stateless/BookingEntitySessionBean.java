/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.BookingEntity;
import entity.CustomerEntity;
import entity.PurchasedPlanEntity;
import entity.SessionEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import util.exception.BookingExistsException;
import util.exception.BookingNotFoundException;
import util.exception.PurchasedPlanNotFoundException;
import util.exception.SessionNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author kelly
 */
@Stateless
public class BookingEntitySessionBean implements BookingEntitySessionBeanLocal {

    @EJB(name = "PurchasedPlanEntitySessionBeanLocal")
    private PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBeanLocal;

    @EJB(name = "SessionEntitySessionBeanLocal")
    private SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<BookingEntity> retrieveAllBookings() {
        Query query = em.createQuery("SELECT b FROM BookingEntity B");

        return query.getResultList();
    }

    @Override
    public Long createNewBooking(BookingEntity newBooking, Long sessionEntityId, Long purchasePlanId) throws BookingExistsException, SessionNotFoundException, PurchasedPlanNotFoundException, UnknownPersistenceException {

        try {
            SessionEntity session = sessionEntitySessionBeanLocal.retrieveSessionBySessionId(sessionEntityId);
            PurchasedPlanEntity purchasedPlan = purchasedPlanEntitySessionBeanLocal.retrievePurchasedPlanByPurchasedPlanId(purchasePlanId);
            CustomerEntity customer = purchasedPlan.getCustomer();

            if (purchasedPlan.getBooking().contains(newBooking)) {
                throw new BookingExistsException("Booking has already been created for this session.");
            }

            newBooking.setPurchasedplan(purchasedPlan);
            newBooking.setSessionEntity(session);
            
            em.persist(newBooking);
            em.flush();
            
            purchasedPlan.setSessionLeft(purchasedPlan.getSessionLeft() - 1);
            session.getParticipants().add(newBooking);
            
            return newBooking.getBookingId();

        } catch (PersistenceException ex) {
            throw new UnknownPersistenceException(ex.getMessage());
        } catch (SessionNotFoundException ex) {
            throw new SessionNotFoundException("Session not found.");
        } catch (PurchasedPlanNotFoundException ex) {
            throw new PurchasedPlanNotFoundException("Purchased Plan not found.");
        }
    }
    @Override
    public List<BookingEntity> retrieveMyBookings(Long cusId){
        Query query = em.createQuery("SELECT s FROM BookingEntity s WHERE s.purchasedplan.customer.customerId =:cusId");
        query.setParameter("cusId", cusId);
        return query.getResultList();
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
