/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.PurchasedPlanEntitySessionBeanLocal;
import ejb.stateless.TransactionEntitySessionBeanLocal;
import entity.CustomerEntity;
import entity.PlanEntity;
import entity.PurchasedPlanEntity;
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
import ws.rest.model.PurchasePlanReq;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("PurchasePlan")
@RequestScoped
public class PurchasePlanResource {

    @Context
    private UriInfo context;
    private final SessionBeanLookup sessionBeanLookup;
    private final PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBeanLocal;
    private final CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    private final PlanEntitySessionBeanLocal planEntitySessionBeanLocal;
    private final TransactionEntitySessionBeanLocal transactionEntitySessionBeanLocal;

    /**
     * Creates a new instance of PurchasePlanResource
     */
    public PurchasePlanResource() {
        sessionBeanLookup = new SessionBeanLookup();
        purchasedPlanEntitySessionBeanLocal = sessionBeanLookup.lookupPurchasedPlanEntitySessionBeanLocal();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
        planEntitySessionBeanLocal = sessionBeanLookup.lookupPlanEntitySessionBeanLocal();
        transactionEntitySessionBeanLocal = sessionBeanLookup.lookupTransactionEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of
     * ws.rest.resources.PurchasePlanResource
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
     * PUT method for updating or creating an instance of PurchasePlanResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeNewPurchasePlan(PurchasePlanReq purchaseplanreq) {

        if (purchaseplanreq != null) {
            try {

                System.out.println(purchaseplanreq.getPlanId());
                CustomerEntity ce = customerEntitySessionBeanLocal.customerLogin(purchaseplanreq.getUsername(), purchaseplanreq.getPassword());
                PlanEntity planToUse = planEntitySessionBeanLocal.retrievePlanByPlanId(purchaseplanreq.getPlanId());

                PurchasedPlanEntity planToAdd = new PurchasedPlanEntity();
                planToAdd.setCustomer(ce);
                planToAdd.setPlanId(planToUse);
                planToAdd.setSessionLeft(Math.toIntExact(planToUse.getSessionLimit()));

                Long toReturn = purchasedPlanEntitySessionBeanLocal.createNewPurchasedPlan(planToAdd, purchaseplanreq.getCcId());

                return Response.status(Response.Status.OK).build();
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {

            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Request").build();
        }
    }

    @Path("retrieveCurrentPlan")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCurrentPlan(@QueryParam("username") String username, @QueryParam("password") String password) {
        System.out.println("InRetrieveCurrentPlan");
        try {
            CustomerEntity customerEntity = customerEntitySessionBeanLocal.customerLogin(username, password);
            PurchasedPlanEntity toReturn = purchasedPlanEntitySessionBeanLocal.retrieveCurrentPlanByCusId(customerEntity.getCustomerId());
            System.out.println(toReturn);
            System.out.println("Before Send off");
            if (toReturn != null) {
                toReturn.setCustomer(null);
                toReturn.setPlanId(null);
                toReturn.setTransactionEntity(null);
                toReturn.getBooking().clear();
                GenericEntity<PurchasedPlanEntity> genericEntity = new GenericEntity<PurchasedPlanEntity>(toReturn) {
                };
                return Response.status(Status.OK).entity(genericEntity).build();
            } else {
                return Response.status(Status.OK).build();
            }
        } catch (Exception ex) {
            return Response.status(Status.OK).build();
        }

    }

}
