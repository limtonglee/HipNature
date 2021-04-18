/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.model;

/**
 *
 * @author leahj
 */
public class GetReviews {
    
    Long reviewId;
    Integer reviewRating;
    String  description;
    String customerName ;

    public GetReviews(Long reviewId, Integer reviewRating, String description, String customerName) {
        this.reviewId = reviewId;
        this.reviewRating = reviewRating;
        this.description = description;
        this.customerName = customerName;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    
}
