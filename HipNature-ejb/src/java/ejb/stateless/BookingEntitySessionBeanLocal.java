/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.BookingEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.BookingNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface BookingEntitySessionBeanLocal {

    public BookingEntity retrieveClassByClassId(Long bookingId) throws BookingNotFoundException;

    public Long createNewBooking(BookingEntity newBooking);

    public List<BookingEntity> retrieveAllBookings();
    
}
