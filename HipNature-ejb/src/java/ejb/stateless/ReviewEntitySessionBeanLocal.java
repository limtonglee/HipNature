/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ReviewEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.ReviewNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface ReviewEntitySessionBeanLocal {

    public ReviewEntity retrieveReviewByReviewId(Long reviewId) throws ReviewNotFoundException;

    public Long createNewReview(ReviewEntity newReview);

    public List<ReviewEntity> retrieveAllReviews();

    public List<ReviewEntity> retrieveReviewsByClassId(Long classId);

    public List<ReviewEntity> retrieveReviewsByPartnerId(Long partnerId);

    public void updateAvgRating(Long classId) throws ClassNotFoundException;
    
}
