/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.ReviewEntitySessionBeanLocal;
import entity.ReviewEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.rest.model.ErrorRsp;
import ws.rest.model.RetrieveClassReviewsRsp;

/**
 * REST Web Service
 *
 * @author kelly
 */
@Path("Review")
@RequestScoped
public class ReviewResource {

    ReviewEntitySessionBeanLocal reviewEntitySessionBean = lookupReviewEntitySessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReviewResource
     */
    public ReviewResource() {
    }

    @GET
    @Path("retrieveReviewsByClassId/{classId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveReviewsByClassId(@PathParam("classId") Long classId) {
        try {
            List<ReviewEntity> reviews = reviewEntitySessionBean.retrieveReviewsByClassId(classId);
            for (ReviewEntity review : reviews) {
                review.setClassEntity(null);
                review.setCustomerEntity(null);
            }
                RetrieveClassReviewsRsp retrieveClassReviewsRsp = new RetrieveClassReviewsRsp(reviews);
                return Response.status(Response.Status.OK).entity(retrieveClassReviewsRsp).build();
            }catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
        }
    
    

    

    private ReviewEntitySessionBeanLocal lookupReviewEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ReviewEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/ReviewEntitySessionBean!ejb.stateless.ReviewEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
