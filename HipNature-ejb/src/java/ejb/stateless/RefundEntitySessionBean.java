/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.BookingEntity;
import entity.PurchasedPlanEntity;
import entity.RefundEntity;
import entity.SessionEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.BookingNotFoundException;
import util.exception.PurchasedPlanNotFoundException;
import util.exception.RefundNotFoundException;
import util.exception.RefundProcessException;
import util.exception.SessionNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class RefundEntitySessionBean implements RefundEntitySessionBeanLocal {

    @EJB(name = "SessionEntitySessionBeanLocal")
    private SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;

    @EJB(name = "BookingEntitySessionBeanLocal")
    private BookingEntitySessionBeanLocal bookingEntitySessionBeanLocal;

    @EJB(name = "PurchasedPlanEntitySessionBeanLocal")
    private PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<RefundEntity> retrieveAllRefunds() {
        Query query = em.createQuery("SELECT r FROM RefundEntity R");

        return query.getResultList();
    }

    @Override
    public Long createNewRefund(RefundEntity newRefund) throws RefundProcessException {

        try {
            BookingEntity booking = bookingEntitySessionBeanLocal.retrieveBookingByBookingId(newRefund.getBookingEntity().getBookingId());
            SessionEntity session = sessionEntitySessionBeanLocal.retrieveSessionBySessionId(booking.getSessionEntity().getSessionId());
            PurchasedPlanEntity currentPlan = purchasedPlanEntitySessionBeanLocal.retrievePurchasedPlanByPurchasedPlanId(booking.getPurchasedplan().getPurchasedPlanId());

            Calendar c = Calendar.getInstance();
            Calendar now = Calendar.getInstance();

            c.setTime(session.getStartTime());
            now.add(Calendar.DATE, 2);

            if (c.compareTo(now) == 1) {
                newRefund.setRefundValue(session.getClassEntity().getCredit());
                currentPlan.setCreditValue(currentPlan.getCreditValue() + session.getClassEntity().getCredit());
            } else {
                newRefund.setRefundValue(0);
            }

            currentPlan.setSessionLeft(currentPlan.getSessionLeft() + 1);

            em.persist(newRefund);
            em.flush();

            booking.setRefundEntity(newRefund);
            booking.setStatus("Refund");
            
            session.getParticipants().size();
            session.getParticipants().remove(booking);
            List<BookingEntity> toUpdate =  session.getParticipants();
            sessionEntitySessionBeanLocal.updateSessionList(toUpdate, session.getSessionId());

            return newRefund.getRefundId();
        } catch (BookingNotFoundException | SessionNotFoundException | PurchasedPlanNotFoundException ex) {
            throw new RefundProcessException("Unable to Process Refund. Please Try Again");
        }

    }

    @Override
    public RefundEntity retrieveRefundByRefundId(Long refundId) throws RefundNotFoundException {
        RefundEntity refundEntity = em.find(RefundEntity.class, refundId);

        if (refundEntity != null) {
            return refundEntity;
        } else {
            throw new RefundNotFoundException("Refund ID " + refundId + " does not exist!");
        }
    }

    @Override
    public List<RefundEntity> retrieveRefundsByCustomerId(Long cusId){
        Query query = em.createQuery("SELECT s FROM RefundEntity s WHERE s.bookingEntity.purchasedplan.customer.customerId = :cusId");
        query.setParameter("cusId", cusId);
        return query.getResultList();
    }
    // public List<RefundEntity> retrieveRefundsBySessionId 
    // public List<RefundEntity> retrieveRefundByBookingId
}
