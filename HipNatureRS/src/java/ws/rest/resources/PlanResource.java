/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PlanEntitySessionBeanLocal;
import entity.PlanEntity;
import java.util.List;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.InvalidLoginCredentialException;

/**
 * REST Web Service
 *
 * @author leahj
 */
@Path("Plan")
@RequestScoped
public class PlanResource {


    PlanEntitySessionBeanLocal planEntitySessionBeanLocal;

    @Context
    private UriInfo context;
 
    private final SessionBeanLookup sessionBeanLookup;


    /**
     * Creates a new instance of CustomerResource
     */
     
     

    /**
     * Creates a new instance of PlanResource
     */
    public PlanResource() {
                        sessionBeanLookup = new SessionBeanLookup();
                planEntitySessionBeanLocal= sessionBeanLookup.lookupPlanEntitySessionBeanLocal();

    }

    @Path("retrieveAllPlans")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPlans() {
        try {
            List<PlanEntity> planEntities = planEntitySessionBeanLocal.retrieveAllPlans();

            for (PlanEntity p : planEntities) {
                p.getPurchasedPlans().clear();              
            }

            GenericEntity<List<PlanEntity>> genericEntity = new GenericEntity<List<PlanEntity>>(planEntities) {
            };

            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }
    

    /**
     * PUT method for updating or creating an instance of PlanResource
     * @param content representation for the resource
     
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }*/

}
