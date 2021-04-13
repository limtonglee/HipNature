/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

import entity.ReviewEntity;
import java.util.List;

/**
 *
 * @author kelly
 */
public class RetrieveClassReviewsRsp {
    
    private List<ReviewEntity> reviews;

    public RetrieveClassReviewsRsp() {
    }

    public RetrieveClassReviewsRsp(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return the reviews
     */
    
    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }   
    
}
