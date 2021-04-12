/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import entity.SessionEntity;
import entity.TagEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumeration.LocationTypeEnum;
import util.exception.DeleteClassEntityException;
import util.exception.DeleteSessionEntityException;
import util.exception.InputDataValidationException;
import util.exception.SessionNotFoundException;
import util.exception.TagNotFoundException;
import util.exception.UpdateSessionException;

/**
 *
 * @author kelly
 */
@Stateless
public class SessionEntitySessionBean implements SessionEntitySessionBeanLocal {

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBean;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public SessionEntitySessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<SessionEntity> retrieveAllSessions() {
        Query query = em.createQuery("SELECT s FROM SessionEntity S");

        return query.getResultList();
    }

    @Override
    public Long createNewSession(SessionEntity newSession) {
        Set<ConstraintViolation<SessionEntity>> constraintViolations = validator.validate(newSession);
        try {
            if (constraintViolations.isEmpty()) {
                em.persist(newSession);
                em.flush();

                newSession.getClassEntity().getSessionEntities().add(newSession);
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionEntitySessionBean.class.getName()).log(Level.SEVERE, null, ex);

        }
        return newSession.getSessionId();

    }

    @Override
    public SessionEntity retrieveSessionBySessionId(Long sessionId) throws SessionNotFoundException {
        SessionEntity sessionEntity = em.find(SessionEntity.class, sessionId);

        if (sessionEntity != null) {
            return sessionEntity;
        } else {
            throw new SessionNotFoundException("Session ID " + sessionId + " does not exist!");
        }
    }

    @Override
    public List<SessionEntity> searchSessionByName(String searchString) {
        Query query = em.createQuery("SELECT p FROM SessionEntity p join ClassEntity c where c.className LIKE :inSearchString ORDER BY p.startTime ASC");
        query.setParameter("inSearchString", "%" + searchString + "%");
        List<SessionEntity> sessionEntities = query.getResultList();

        for (SessionEntity sessionEntity : sessionEntities) {
            sessionEntity.getParticipants().size();
            sessionEntity.getClassEntity().getTagEntities().size();
        }

        return sessionEntities;
    }

    @Override
    public List<SessionEntity> filterSessionsByTags(List<Long> tagIds, String location) {
        List<SessionEntity> sessionEntities = new ArrayList<>();

        if (tagIds == null || tagIds.isEmpty()) {
            if (!location.equals("ALL")) {
                String selectClause = "SELECT pe FROM SessionEntity pe where pe.locationTypeEnum =:statusEnum";
                Query query = em.createQuery(selectClause);
                query.setParameter("statusEnum", LocationTypeEnum.valueOf(location));
                sessionEntities = query.getResultList();
                return sessionEntities;

            } else {
                return retrieveAllSessions();
            }

        } else {
            String selectClause = "SELECT pe FROM SessionEntity pe join ClassEntity c ";

            String whereClause = "";
            Boolean firstTag = true;
            Integer tagCount = 1;

            for (Long tagId : tagIds) {

                selectClause += ", IN (c.tagEntities) te" + tagCount;

                if (firstTag) {
                    whereClause = "WHERE te1.tagId = " + tagId;
                    firstTag = false;
                } else {
                    whereClause += " AND te" + tagCount + ".tagId = " + tagId;
                }

                tagCount++;
            }

            if (!location.equals("ALL")) {
                whereClause += " and pe.locationTypeEnum = " + LocationTypeEnum.valueOf(location);

            }
            String jpql = selectClause + " " + whereClause + " ORDER BY c.className ASC";
            Query query = em.createQuery(jpql);
            sessionEntities = query.getResultList();
        }

        for (SessionEntity sessionEntity : sessionEntities) {
            sessionEntity.getParticipants().size();
            sessionEntity.getClassEntity().getTagEntities().size();
        }
        Collections.sort(sessionEntities, new Comparator<SessionEntity>() {
            public int compare(SessionEntity pe1, SessionEntity pe2) {
                return pe1.getStartTime().compareTo(pe2.getStartTime());
            }
        });

        return sessionEntities;
    }

    @Override
    public void updateSession(SessionEntity sessionEntity, List<Long> tagIds) throws SessionNotFoundException, UpdateSessionException, InputDataValidationException  {
       Set<ConstraintViolation<SessionEntity>>constraintViolations = validator.validate(sessionEntity);
        if (constraintViolations.isEmpty()){
            System.out.println(sessionEntity.getSessionId());
           
        System.out.println(sessionEntity.getSessionId());
        if (sessionEntity!= null && sessionEntity.getSessionId() != null  ) {
            SessionEntity sessionEntityToUpdate = retrieveSessionBySessionId(sessionEntity.getSessionId());

            sessionEntityToUpdate.setMaxCapacity(sessionEntity.getMaxCapacity());
            sessionEntityToUpdate.setInstructor(sessionEntity.getInstructor());
            sessionEntityToUpdate.setLocationTypeEnum(sessionEntity.getLocationTypeEnum());

            // Removed in v5.0
            //productEntityToUpdate.setCategory(productEntity.getCategory());
            // Added in v5.1
        } else {
            throw new UpdateSessionException("Invalid session Id");
        }
        }
        else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }

    }

   public String prepareInputDataValidationException(Set<ConstraintViolation<SessionEntity>>constraintViolations){
        String msg = "Input data validation error: ";
        for (ConstraintViolation constraint: constraintViolations){
            msg +="\n\t" + constraint.getPropertyPath() + " - " + constraint.getInvalidValue() + " : " + constraint.getMessage();
        }
        return msg;
    }

   
    @Override
    public void deleteSession(Long sessionEntityToDeleteId) throws DeleteSessionEntityException  {
        SessionEntity sessionEntityToDelete = em.find(SessionEntity.class, sessionEntityToDeleteId);
        int size = sessionEntityToDelete.getParticipants().size();
        if (sessionEntityToDelete.getParticipants().size() == 0) {
            em.remove(sessionEntityToDelete);
        } else {
            throw new DeleteSessionEntityException("Unable to delete class Id: " + sessionEntityToDelete + " There are still " + size + " session(s) left" );
        }
    }
    @Override
    public List<SessionEntity> retrieveSessionsByPartnerId(Long partnerId){
        Query query = em.createQuery("SELECT S FROM SessionEntity s WHERE s.classEntity.partnerEntity.PartnerEntityId =:partnerId");
        query.setParameter("partnerId", partnerId);
        return query.getResultList();
    }
    @Override
    public List<SessionEntity> retrieveSessionsByClassId(Long classId){
        Query query = em.createQuery("SELECT S FROM SessionEntity s WHERE s.classEntity.classId =:classId");
        query.setParameter("classId", classId);
        return query.getResultList();
    }
}
