/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.ReviewEntitySessionBeanLocal;
import entity.ClassEntity;
import entity.CustomerEntity;
import entity.ReviewEntity;
import java.util.ArrayList;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.InvalidLoginCredentialException;
import ws.rest.model.CreateReviewReq;
import ws.rest.model.ErrorRsp;
import ws.rest.model.GetReviews;
import ws.rest.model.RetrieveClassReviewsRsp;

/**
 * REST Web Service
 *
 * @author kelly
 */
@Path("Review")
@RequestScoped
public class ReviewResource {

    ClassEntitySessionBeanLocal classEntitySessionBean = lookupClassEntitySessionBeanLocal();

    CustomerEntitySessionBeanLocal customerEntitySessionBean = lookupCustomerEntitySessionBeanLocal();

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
            GenericEntity<List<ReviewEntity>> genericEntity = new GenericEntity<List<ReviewEntity>>(reviews) {
            };
            
            return Response.status(Response.Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReview(CreateReviewReq createReviewReq)
    {
        System.out.println("Review");
        if(createReviewReq != null)
        {
            try
            {
                CustomerEntity customer = customerEntitySessionBean.customerLogin(createReviewReq.getUsername(), createReviewReq.getPassword());
                                    System.out.println("********** ProductResource.createProduct(): Staff " + customer.getUsername() + " login remotely via web service");

                                    System.out.println(createReviewReq.getPassword());
                                             System.out.println(createReviewReq.getUsername());
                                             System.out.println(createReviewReq.getDescription());
                                             System.out.println(createReviewReq.getReviewRating());

                System.out.println("********** ProductResource.createProduct(): Staff " + customer.getUsername() + " login remotely via web service");
                ClassEntity c = classEntitySessionBean.retrieveClassByClassId(createReviewReq.getClassId());
                ReviewEntity newR = new ReviewEntity(createReviewReq.getReviewRating(), createReviewReq.getDescription(), customer,c );
                 System.out.println(newR);
                Long reviewId  = reviewEntitySessionBean.createNewReview(newR);
                
                return Response.status(Response.Status.OK).entity(reviewId).build();
            }
            catch(InvalidLoginCredentialException ex)
            {
                return Response.status(Status.UNAUTHORIZED).entity(ex.getMessage()).build();
            }
            catch(Exception ex)
            {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid create new review request").build();
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

    private CustomerEntitySessionBeanLocal lookupCustomerEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/CustomerEntitySessionBean!ejb.stateless.CustomerEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ClassEntitySessionBeanLocal lookupClassEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ClassEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/ClassEntitySessionBean!ejb.stateless.ClassEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
