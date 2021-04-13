package ws.rest.model;

import entity.ReviewEntity;
import java.util.List;



public class CreateReviewReq
{
    private String username;
    private String password;
        private Integer reviewRating;
    private String description;
    private Long classId;

    public CreateReviewReq() {
    }

    public CreateReviewReq(String username, String password,   Integer reviewRating, String description,Long classId) {
        this.username = username;
        this.password = password;
        this.reviewRating= reviewRating;
        this.description= description;   
        this.classId=classId;
    }

    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the reviewRating
     */
    public Integer getReviewRating() {
        return reviewRating;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param reviewRating the reviewRating to set
     */
    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the classId
     */
    public Long getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(Long classId) {
        this.classId = classId;
    }
    
    
    
   

    /**
     * @return the review
     */
    

}
