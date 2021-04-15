/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CustomerEntitySessionBeanLocal;
import entity.CreditCardEntity;
import entity.CustomerEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
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
    
    @Path("retrieveCreditCard")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCreditCard(@QueryParam("username") String username, @QueryParam("password") String password){
        try{

            CustomerEntity customerEntity = customerEntitySessionBeanLocal.customerLogin(username, password);
            List<CreditCardEntity> toReturn = customerEntitySessionBeanLocal.getAllCreditCardsFromCustomer(customerEntity);
            for(CreditCardEntity cce : toReturn){
            cce.setCustomerEntity(null);

            }
            GenericEntity<List<CreditCardEntity>> genericEntity = new GenericEntity<List<CreditCardEntity>>(toReturn) {};
            return Response.status(Status.OK).entity(genericEntity).build();
        }catch (Exception ex)
            {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
    }
    
    
    @Path("{creditCardId}")
    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCreditCard(@QueryParam("username") String username, @QueryParam("password") String password, @PathParam("creditCardId") Long creditCardId){
        try{
            System.out.println("Username:" + username);
            System.out.println("password:" + password);
             CustomerEntity customerEntity = customerEntitySessionBeanLocal.customerLogin(username, password);
             customerEntitySessionBeanLocal.deleteCreditCard(creditCardId);
              return Response.status(Status.OK).build();
        }catch (Exception ex)
            {
                return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
            }
           }
    

}
