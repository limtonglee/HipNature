/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.CustomerEntity;
import entity.SessionEntity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.rest.model.CheckoutSessionReq;

/**
 * REST Web Service
 *
 * @author leahj
 */
@Path("Booking")
@RequestScoped
public class BookingResource {

    SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;
    CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    BookingEntitySessionBeanLocal bookingEntitySessionBeanLocal;
    @Context
    private UriInfo context;
    private final SessionBeanLookup sessionBeanLookup;

    /**
     * Creates a new instance of BookingResource
     */
    public BookingResource() {
        sessionBeanLookup = new SessionBeanLookup();
        sessionEntitySessionBeanLocal = sessionBeanLookup.lookupSessionEntitySessionBeanLocal();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
        bookingEntitySessionBeanLocal = sessionBeanLookup.lookupBookingEntitySessionBeanLocal();
       }

    /**
     * Retrieves representation of an instance of ws.rest.resources.BookingResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BookingResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    /* wrong area, should be Booking Resource*/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkoutSession(CheckoutSessionReq checkoutSesionreq){
        try {
            System.out.println("Test");
            CustomerEntity ce = customerEntitySessionBeanLocal.customerLogin(checkoutSesionreq.getUsername(), checkoutSesionreq.getPassword());
            SessionEntity[] sessionEntities = checkoutSesionreq.getSessionEntities();
            for (int i = 0; i < sessionEntities.length; i++){
                System.out.println(sessionEntities[1]);
        
        }
          
            return null;
        } catch (Exception ex) {
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
