/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import ejb.stateless.SessionEntitySessionBeanLocal;
import entity.SessionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.rest.model.ErrorRsp;
import ws.rest.model.retrieveSessionByClassId;

/**
 * REST Web Service
 *
 * @author leahj
 */
@Path("Sessions")
public class SessionsResource {

    SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;
    @Context
    private UriInfo context;
    private final SessionBeanLookup sessionBeanLookup;

    /**
     * Creates a new instance of SessionsResource
     */
    public SessionsResource() {
        sessionBeanLookup = new SessionBeanLookup();
        sessionEntitySessionBeanLocal = sessionBeanLookup.lookupSessionEntitySessionBeanLocal();
    }

    /**
     * Retrieves representation of an instance of ws.rest.SessionsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SessionsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
        @Path("retrieveSessionsByClassId/{classId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSessionsByClassId(@PathParam("classId") Long classId) {
        System.out.println("test");
        try {
            List<retrieveSessionByClassId> toReturn = new ArrayList<>();
            List<SessionEntity> sessionEntities = sessionEntitySessionBeanLocal.retrieveSessionsByClassId(classId);
            for (SessionEntity se: sessionEntities){
                se.getInstructor();
                retrieveSessionByClassId toReturnSingleEntity = new retrieveSessionByClassId(se.getSessionId(),se.getVenue(),se.getStartTime(),se.getEndTime(),se.getDuration(),se.getPhone(),se.getMaxCapacity(), se.getStatus(),se.getLocationTypeEnum(),se.getInstructor().getInstructorName());
                se.setInstructor(null);
                se.getParticipants().clear();
                se.setClassEntity(null);
                toReturn.add(toReturnSingleEntity);
                
            }
//             GenericEntity<List<SessionEntity>> genericEntity = new GenericEntity<List<SessionEntity>>(sessionEntities) {
//            };
            GenericEntity<List<retrieveSessionByClassId>> genericEntity = new GenericEntity<List<retrieveSessionByClassId>>(toReturn) {
            };
            return Response.status(Status.OK).entity(genericEntity).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
}
