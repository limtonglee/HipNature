package ws.datamodel;

import entity.ReviewEntity;
import java.util.List;



public class CreateReviewReq
{
    private String username;
    private String password;
    private ReviewEntity review;    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the review
     */
    public ReviewEntity getReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(ReviewEntity review) {
        this.review = review;
    }

}
