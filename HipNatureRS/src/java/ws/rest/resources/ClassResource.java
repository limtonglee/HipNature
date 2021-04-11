/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ws.rest.model.RetrieveAllClassesRsp;
import ws.rest.model.ErrorRsp;
import ejb.stateless.ClassEntitySessionBeanLocal;
import entity.ClassEntity;
import static entity.ClassTypeEntity_.classEntities;
import entity.PlanEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author kelly
 */
@Path("Class")
public class ClassResource {

    ClassEntitySessionBeanLocal classEntitySessionBeanLocal;

    @Context
    private UriInfo context;

    private final SessionBeanLookup sessionBeanLookup;

    public ClassResource() {
        sessionBeanLookup = new SessionBeanLookup();
        classEntitySessionBeanLocal = sessionBeanLookup.lookupClassEntitySessionBeanLocal();
        System.out.println("HERE");

    }

    @Path("retrieveAllClasses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllClasses() {
        System.out.println("HERE");
        try {

            List<ClassEntity> classEntities = classEntitySessionBeanLocal.retrieveAllClasses();

            for (ClassEntity c : classEntities) {
                // clear bidirectional mappings or set the inverse side to null
                c.getReviewEntities().clear();
                c.setClassTypeEntity(null);
                c.getTagEntities().clear();
                c.getSessionEntities().clear();
                c.setPartnerEntity(null);
            }
            System.out.println(classEntities);
            GenericEntity<List<ClassEntity>> genericEntity = new GenericEntity<List<ClassEntity>>(classEntities) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

}
