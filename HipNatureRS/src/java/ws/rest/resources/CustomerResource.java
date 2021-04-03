/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CustomerEntitySessionBeanLocal;
import entity.CustomerEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.InvalidLoginCredentialException;

/**
 * REST Web Service
 *
 * @author leahj
 */
@Path("Customer")
@RequestScoped
public class CustomerResource {


    @Context
    private UriInfo context;

    private final SessionBeanLookup sessionBeanLookup;
        CustomerEntitySessionBeanLocal customerEntitySessionBean;

    /**
     * Creates a new instance of CustomerResource
     */
     
     
    public CustomerResource() {
                sessionBeanLookup = new SessionBeanLookup();
                customerEntitySessionBean= sessionBeanLookup.lookupCustomerEntitySessionBeanLocal();

    }
    
    @Path("customerLogin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response staffLogin(@QueryParam("username") String username, 
                                @QueryParam("password") String password)
    {
        try
        {
            System.out.print(password);
                        System.out.print(username);

            CustomerEntity custEntity = customerEntitySessionBean.customerLogin(username, password);
            System.out.println("********** CustomerResource.custLogin(): Staff " + custEntity.getUsername() + " login remotely via web service");

            custEntity.setPassword(null);
            custEntity.setSalt(null);
            custEntity.getCreditCardEntity().clear();            
            custEntity.getReviewEntity().clear();            
            custEntity.getPurchasedPlans().clear();            

            return Response.status(Response.Status.OK).entity(custEntity).build();
        }
        catch(InvalidLoginCredentialException ex)
        {
            return Response.status(Response.Status.UNAUTHORIZED).entity(ex.getMessage()).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
   
}
