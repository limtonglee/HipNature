/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.RefundEntitySessionBeanLocal;
import entity.BookingEntity;
import entity.CustomerEntity;
import entity.RefundEntity;
import entity.SessionEntity;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.rest.model.RefundReq;
import ws.rest.model.RefundRsq;
import ws.rest.model.RetrieveBookingsByCusReq;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("Refund")
@RequestScoped
public class RefundResource {

    @Context
    private UriInfo context;

    private final SessionBeanLookup sessionBeanLookup;
    private final CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    private final BookingEntitySessionBeanLocal bookingEntitySessionBeanLocal;
    private final RefundEntitySessionBeanLocal refundEntitySessionBeanLocal;

    /**
     * Creates a new instance of RefundResource
     */
    public RefundResource() {
        sessionBeanLookup = new SessionBeanLookup();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
        bookingEntitySessionBeanLocal = sessionBeanLookup.lookupBookingEntitySessionBeanLocal();
        refundEntitySessionBeanLocal = sessionBeanLookup.lookupRefundEntitySessionBeanLocal();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doRefundService(RefundReq refundReq) {
        System.out.println("DoRefundService");
        if (refundReq != null) {
            try {
                System.out.println("RefundReq");
                BookingEntity bookingToRefund = bookingEntitySessionBeanLocal.retrieveBookingByBookingId(refundReq.getBookingId());

                RefundEntity newRefund = new RefundEntity();
                newRefund.setBookingEntity(bookingToRefund);
                newRefund.setReason(refundReq.getReason());

                refundEntitySessionBeanLocal.createNewRefund(newRefund);
                return Response.status(Status.OK).build();
            } catch (Exception ex) {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Path("retrieveMyRefunds")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMyRefunds(@QueryParam("username") String username, @QueryParam("password") String password) {
        try {

            CustomerEntity customerEntity = customerEntitySessionBeanLocal.customerLogin(username, password);
            List<RefundEntity> toReturn = refundEntitySessionBeanLocal.retrieveRefundsByCustomerId(customerEntity.getCustomerId());
            List<RefundRsq> refundRsq = new ArrayList<>();

            for (RefundEntity cce : toReturn) {
                RefundRsq toAdd = new RefundRsq();
                BookingEntity booking = cce.getBookingEntity();
                SessionEntity session = booking.getSessionEntity();

                RetrieveBookingsByCusReq toGet = new RetrieveBookingsByCusReq();
                toGet.setBookingDate(booking.getBookingDate());
                toGet.setBookingId(booking.getBookingId());
                toGet.setEndTime(session.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                toGet.setStartTime(session.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                toGet.setInstructor(session.getInstructor().getInstructorName());
                toGet.setPhone(session.getPhone());
                toGet.setSessionName(session.getClassEntity().getClassName());
                toGet.setVenue(session.getVenue());
                toGet.setPurchasedplanId(booking.getPurchasedplan().getPurchasedPlanId());
                toGet.setExpiryDate(booking.getPurchasedplan().getExpiryDate());
                
                toAdd.setBookingRefunded(toGet);
                
                toAdd.setReason(cce.getReason());
                toAdd.setRefundValue(cce.getRefundValue());
                toAdd.setRefundId(cce.getRefundId());
                toAdd.setRefundDate(cce.getRefundDate());
                
                refundRsq.add(toAdd);
                
                

            }
            GenericEntity<List<RefundRsq>> genericEntity = new GenericEntity<List<RefundRsq>>(refundRsq) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    /**
     * Retrieves representation of an instance of
     * ws.rest.resources.RefundResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RefundResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

}
