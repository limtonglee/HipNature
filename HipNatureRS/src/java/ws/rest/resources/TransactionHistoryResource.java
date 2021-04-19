/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CreditPlanSessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.TransactionEntitySessionBeanLocal;
import entity.CustomerEntity;
import entity.TransactionEntity;
import java.util.ArrayList;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ws.rest.model.TransactionRequest;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("TransactionHistory")
@RequestScoped
public class TransactionHistoryResource {

    TransactionEntitySessionBeanLocal transactionEntitySessionBean = lookupTransactionEntitySessionBeanLocal();

    CreditPlanSessionBeanLocal creditPlanSessionBean = lookupCreditPlanSessionBeanLocal();

    PlanEntitySessionBeanLocal planEntitySessionBean = lookupPlanEntitySessionBeanLocal();

    
    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;
    private final CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;
    
    

    /**
     * Creates a new instance of TransactionHistoryResource
     */
    public TransactionHistoryResource() {
        sessionBeanLookup = new SessionBeanLookup();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.rest.resources.TransactionHistoryResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TransactionHistoryResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    @Path("retrieveMyTransactions")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMyTransactions(@QueryParam("username") String username, @QueryParam("password") String password) {
        try{
            CustomerEntity customer = customerEntitySessionBeanLocal.customerLogin(username, password);
            List<TransactionEntity> toFilter = transactionEntitySessionBean.getAllTransactionsFromCustomerId(customer.getCustomerId());
            List<TransactionRequest> toReturn = new ArrayList<>();
            System.out.println("Transaction Size: " + toFilter.size());
            for (TransactionEntity te: toFilter){
                System.out.println("temp " + te.getCreditPlan());
                TransactionRequest toAdd = new TransactionRequest();
                toAdd.setTransactionId(te.getTransactionId());
                toAdd.setTransactionDate(te.getTransactionDate());
                toAdd.setCcNumber(te.getCreditCardEntity().getCardNumber());
                if (te.getPurchasedPlan() != null){
                    toAdd.setPrice(te.getPurchasedPlan().getPlanId().getPrice());
                    toAdd.setPlanType(te.getPurchasedPlan().getPlanId().getPlanName());
                } else {
                    
                    toAdd.setPrice(te.getCreditPlan().getPrice());
                    toAdd.setPlanType("Credit Plan " + te.getCreditPlan().getCreditPlanId().toString());
                }
                toReturn.add(toAdd);
            }
            GenericEntity<List<TransactionRequest>> genericEntity = new GenericEntity<List<TransactionRequest>>(toReturn) {
            };
            //return Response.status(Response.Status.OK).build();
            return Response.status(Response.Status.OK).entity(genericEntity).build();
        }catch(Exception ex){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    private PlanEntitySessionBeanLocal lookupPlanEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PlanEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/PlanEntitySessionBean!ejb.stateless.PlanEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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
