/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ReviewEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class ReviewEntitySessionBean implements ReviewEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<ReviewEntity> retrieveAllReviews() {
        Query query = em.createQuery("SELECT r FROM ReviewEntity R");
        
        return query.getResultList();
    }

    @Override
    public Long createNewReview(ReviewEntity newReview) {
        
        em.persist(newReview);
        
        newReview.getCustomerEntity().getReviewEntity().add(newReview);
        newReview.getClassEntity().getReviewEntities().add(newReview);
        em.flush();
        
        return newReview.getReviewId();
        
    }
        
    @Override
    public ReviewEntity retrieveReviewByReviewId(Long reviewId) throws ReviewNotFoundException {
        ReviewEntity reviewEntity = em.find(ReviewEntity.class, reviewId);
        
        if (reviewEntity != null) {
            return reviewEntity;
        } else {
            throw new ReviewNotFoundException("Review ID " + reviewId + " does not exist!");
        }
    }
    
    @Override
    public List<ReviewEntity> retrieveReviewsByClassId(Long classId) {
        Query query = em.createQuery("SELECT r FROM ReviewEntity r WHERE r.classEntity.classId =:cid");
        query.setParameter("cid", classId);
        
        return query.getResultList(); 
    }

    // public List<ReviewEntity> retrieveAllReviewsForPartner(Long partnerId) 
    // public List<ReviewEntity retrieveAllReviewsForClass(Long classId)
    // public void updateReview(Review review)
    // public void deleteReview(Long viewId)

}
