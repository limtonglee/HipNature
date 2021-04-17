/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CreditPlanSessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PurchasedPlanEntitySessionBeanLocal;
import ejb.stateless.TransactionEntitySessionBeanLocal;
import entity.CreditCardEntity;
import entity.CreditPlanEntity;
import entity.CustomerEntity;
import entity.PurchasedPlanEntity;
import entity.TransactionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@Path("CreditPlan")
@RequestScoped
public class CreditPlanResource {

    TransactionEntitySessionBeanLocal transactionEntitySessionBean = lookupTransactionEntitySessionBeanLocal();

    PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBean = lookupPurchasedPlanEntitySessionBeanLocal();

    CustomerEntitySessionBeanLocal customerEntitySessionBean = lookupCustomerEntitySessionBeanLocal();

    CreditPlanSessionBeanLocal creditPlanSessionBean = lookupCreditPlanSessionBeanLocal();

    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CreditPlanResource
     */
    public CreditPlanResource() {
    }

    /**
     * Retrieves representation of an instance of ws.rest.resources.CreditPlanResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CreditPlanResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @Path("retrieveAllCreditPlans")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCreditPlans() {
        try {
            List<CreditPlanEntity> planEntities = creditPlanSessionBean.retrieveAllCreditPlans();

            for (CreditPlanEntity p : planEntities) {
                p.setTransactionEntity(null);
            }

            GenericEntity<List<CreditPlanEntity>> genericEntity = new GenericEntity<List<CreditPlanEntity>>(planEntities) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeNewPurchaseCreditPlan(PurchasePlanReq purchaseplanreq) {

        if (purchaseplanreq != null) {
            try {

                System.out.println(purchaseplanreq.getPlanId());
                CustomerEntity ce = customerEntitySessionBean.customerLogin(purchaseplanreq.getUsername(), purchaseplanreq.getPassword());
                CreditPlanEntity planToUse = creditPlanSessionBean.retrieveCreditPlanByCreditPlanId(purchaseplanreq.getPlanId());
                CreditCardEntity cce = customerEntitySessionBean.retrieveCreditCardById(ce.getCustomerId());
                
                Long planIdToTake = purchasedPlanEntitySessionBean.retrieveCurrentPlanByCusId(ce.getCustomerId()).getPurchasedPlanId();
                purchasedPlanEntitySessionBean.updateCreditValueForPurchasedPlanByPurchasedPlanId(planIdToTake, planToUse.getCreditValue());
                
                TransactionEntity newTransaction = new TransactionEntity();
                newTransaction.setCreditCardEntity(cce);
                newTransaction.setCreditPlan(planToUse);
                newTransaction.setTransactionDate(java.time.LocalDate.now());
                transactionEntitySessionBean.createNewTransaction(newTransaction);
                
                return Response.status(Response.Status.OK).build();
            } catch (Exception ex) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {

            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Request").build();
        }
    }

    private CreditPlanSessionBeanLocal lookupCreditPlanSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CreditPlanSessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/CreditPlanSessionBean!ejb.stateless.CreditPlanSessionBeanLocal");
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

    private PurchasedPlanEntitySessionBeanLocal lookupPurchasedPlanEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PurchasedPlanEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/PurchasedPlanEntitySessionBean!ejb.stateless.PurchasedPlanEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private TransactionEntitySessionBeanLocal lookupTransactionEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (TransactionEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/TransactionEntitySessionBean!ejb.stateless.TransactionEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
