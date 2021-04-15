/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PurchasedPlanEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.BookingEntity;
import entity.CustomerEntity;
import entity.PurchasedPlanEntity;
import entity.SessionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.rest.model.CreateNewBookingReq;
import ws.rest.model.ErrorRsp;
import ws.rest.model.retrieveSessionByClassId;

/**
 * REST Web Service
 *
 * @author kelly
 */
@Path("Bookings")
@RequestScoped
public class BookingsResource {

    BookingEntitySessionBeanLocal bookingEntitySessionBeanLocal = lookupBookingEntitySessionBeanLocal();
    private final SessionBeanLookup sessionBeanLookup;
    SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;
    CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    private final PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBeanLocal;
    @Context
    private UriInfo context;

    public BookingsResource() {
        sessionBeanLookup = new SessionBeanLookup();
        sessionEntitySessionBeanLocal = sessionBeanLookup.lookupSessionEntitySessionBeanLocal();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
        purchasedPlanEntitySessionBeanLocal = sessionBeanLookup.lookupPurchasedPlanEntitySessionBeanLocal();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(CreateNewBookingReq createNewBookingReq) {
        if (createNewBookingReq != null) {
            try {
                System.out.println("Testing");
                CustomerEntity ce = customerEntitySessionBeanLocal.customerLogin(createNewBookingReq.getUsername(),createNewBookingReq.getPassword());
                PurchasedPlanEntity ppeToUse = purchasedPlanEntitySessionBeanLocal.retrieveCurrentPlanByCusId(ce.getCustomerId());
                for (retrieveSessionByClassId rsbci : createNewBookingReq.getSessionArray()) {
                    System.out.println("Session Id Selected: " + rsbci.getSessionId());
                    BookingEntity newBookingEntity = new BookingEntity();
                    bookingEntitySessionBeanLocal.createNewBooking(newBookingEntity, rsbci.getSessionId(), ppeToUse.getPurchasedPlanId());
                }
                return Response.status(Response.Status.OK).build();
            } catch (Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                System.out.println(ex.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid Request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

    /*Retrieve All booking associated to the Customer*/
    @Path("retrieveMyBookings")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMyBookings(Long cusId) {

        try {
            List<BookingEntity> BookingList = bookingEntitySessionBeanLocal.retrieveMyBookings(cusId);
            for (BookingEntity be : BookingList){
                be.setPurchasedplan(null);
                be.setSessionEntity(null);
                be.setRefundEntity(null);
            }
            GenericEntity<List<BookingEntity>> genericBookingList = new GenericEntity<List<BookingEntity>>(BookingList) {
            };
            return Response.status(Status.OK).entity(genericBookingList).build();
        } catch (Exception ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }

    }

    private BookingEntitySessionBeanLocal lookupBookingEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BookingEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/BookingEntitySessionBean!ejb.stateless.BookingEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
