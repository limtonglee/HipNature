/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CustomerEntitySessionBeanLocal;
import entity.CreditCardEntity;
import entity.CustomerEntity;
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
import javax.ws.rs.core.Response.Status;
import ws.rest.model.CreditCardReq;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("CreditCard")
@RequestScoped
public class CreditCardResource {

    @Context
    private UriInfo context;
    
    private final SessionBeanLookup sessionBeanLookup;
    private final CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;

    /**
     * Creates a new instance of CreditCardResource
     */
    public CreditCardResource() {
        sessionBeanLookup = new SessionBeanLookup();
        customerEntitySessionBeanLocal = sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of
     * ws.rest.resources.CreditCardResource
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
     * PUT method for updating or creating an instance of CreditCardResource
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
    public Response addNewCard(CreditCardReq creditCardReq ){
        System.out.println("In Add New Card");
        if (creditCardReq != null){
            try{
                CustomerEntity customerEntity = customerEntitySessionBeanLocal.customerLogin(creditCardReq.getUsername(), creditCardReq.getPassword());
                System.out.println("test3");
                CreditCardEntity addedCreditCard = customerEntitySessionBeanLocal.addCreditCardToCustomer(creditCardReq.getCreditCard(), customerEntity);
                return Response.status(Status.OK).entity(addedCreditCard.getCreditCardId()).build();
            }catch (Exception ex)
            {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Request").build();
        }
    }
    
    

}
