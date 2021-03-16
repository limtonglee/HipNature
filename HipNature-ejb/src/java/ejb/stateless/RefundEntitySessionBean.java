/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.RefundEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.RefundNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class RefundEntitySessionBean implements RefundEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<RefundEntity> retrieveAllRefunds() {
        Query query = em.createQuery("SELECT r FROM RefundEntity R");
        
        return query.getResultList();
    }

    @Override
    public Long createNewRefund(RefundEntity newRefund) {
        
        em.persist(newRefund);
        em.flush();

        return newRefund.getRefundId();
        
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

    // public List<RefundEntity> retrieveRefundsByCustomerId
    // public List<RefundEntity> retrieveRefundsBySessionId 
    // public List<RefundEntity> retrieveRefundByBookingId

}
