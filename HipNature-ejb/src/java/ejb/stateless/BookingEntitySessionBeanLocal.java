/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.BookingEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.BookingExistsException;
import util.exception.BookingNotFoundException;
import util.exception.PurchasedPlanNotFoundException;
import util.exception.SessionNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author kelly
 */
@Local
public interface BookingEntitySessionBeanLocal {

    public BookingEntity retrieveClassByClassId(Long bookingId) throws BookingNotFoundException;

    public List<BookingEntity> retrieveAllBookings();

    public Long createNewBooking(BookingEntity newBooking, Long sessionEntityId, Long purchasePlanId) throws BookingExistsException, SessionNotFoundException, PurchasedPlanNotFoundException, UnknownPersistenceException;
}
