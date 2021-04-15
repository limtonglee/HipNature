/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ws.rest.model.ErrorRsp;
import ejb.stateless.ClassEntitySessionBeanLocal;
import entity.ClassEntity;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.rest.model.RetrieveClassRsp;

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

            GenericEntity<List<ClassEntity>> genericEntity = new GenericEntity<List<ClassEntity>>(classEntities) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @GET
    @Path("retrieveClass/{classId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveClassByClassId(@PathParam("classId") Long classId) {
        try {
            ClassEntity classEntity = classEntitySessionBeanLocal.retrieveClassByClassId(classId);
            classEntity.getReviewEntities().clear();
            classEntity.getSessionEntities().clear();
            classEntity.getTagEntities().clear();
            classEntity.setClassTypeEntity(null);
            classEntity.setPartnerEntity(null);
            GenericEntity<ClassEntity> genericEntity = new GenericEntity<ClassEntity>(classEntity) {            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
}
