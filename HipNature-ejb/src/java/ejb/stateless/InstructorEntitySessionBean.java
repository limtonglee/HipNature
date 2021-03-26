/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.InstructorEntity;
import entity.PartnerEntity;
import entity.SessionEntity;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.InstructorExistsException;
import util.exception.InstructorNotFoundException;
import util.exception.PartnerNotFoundException;
import util.exception.SessionNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author kelly
 */
@Stateless
public class InstructorEntitySessionBean implements InstructorEntitySessionBeanLocal {

    @EJB(name = "PartnerEntitySessionBeanLocal")
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;

    @EJB(name = "SessionEntitySessionBeanLocal")
    private SessionEntitySessionBeanLocal sessionEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public InstructorEntitySessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<InstructorEntity> retrieveAllInstructors() {
        Query query = em.createQuery("SELECT i FROM InstructorEntity I");

        return query.getResultList();
    }

    @Override
    public Long createNewInstructorByIns(InstructorEntity newInstructor) {

        em.persist(newInstructor);
        em.flush();

        return newInstructor.getInstructorId();
    }

    @Override
    public Long createNewInstructor(InstructorEntity newInstructor, Long partnerEntityId/*, List<Long> sessionsId*/) throws PartnerNotFoundException, InputDataValidationException, InstructorExistsException, UnknownPersistenceException /*, SessionNotFoundException*/ {
        try {
            Set<ConstraintViolation<InstructorEntity>> constraintViolations = validator.validate(newInstructor);

            if (constraintViolations.isEmpty()) {
                try {

                    PartnerEntity partnerEntity = partnerEntitySessionBeanLocal.retrievePartnerByPartnerId(partnerEntityId);
                    em.persist(newInstructor);

                    newInstructor.setPartnerEntity(partnerEntity);

                    /*
                    if (sessionsId != null && !(sessionsId.isEmpty())) {
                        List<SessionEntity> instructorSessions = new ArrayList<>();
                        for (Long sess : sessionsId) {
                            SessionEntity sessions = sessionEntitySessionBeanLocal.retrieveSessionBySessionId(sess);
                            instructorSessions.add(sessions);
                        }
                        newInstructor.setSessionEntity(instructorSessions);
                    }*/
                    em.flush();
                    return newInstructor.getInstructorId();

                } catch (PartnerNotFoundException ex) {
                    throw new PartnerNotFoundException("Partner not found for ID: " + partnerEntityId);
                }
            } else {
                throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
            }
        } catch (PersistenceException ex) {
            if (ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException")) {
                if (ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException")) {
                    throw new InstructorExistsException("Instructor's Phone/Email exists, please try again!");
                } else {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            } else {
                throw new UnknownPersistenceException(ex.getMessage());
            }
        }
    }


@Override
        public InstructorEntity retrieveInstructorByInstructorId(Long instructorId) throws InstructorNotFoundException {
        InstructorEntity ins = em.find(InstructorEntity.class, instructorId);

        if (ins != null) {
            return ins;
        } else {
            throw new InstructorNotFoundException("Instructor ID " + instructorId + " does not exist!");
        }
    }

    @Override
        public List<InstructorEntity> retrieveInstructorsByPartner(Long pid) {

        Query query = em.createQuery("SELECT ins FROM InstructorEntity ins WHERE ins.partnerEntity.partnerEntityId = :partnerId");
        query.setParameter("partnerId", pid);

        return (List<InstructorEntity>) query.getResultList();
    }

    public void updateInstructor(InstructorEntity instructor, List<Long> sessionIds) throws InstructorNotFoundException, SessionNotFoundException {

        if (instructor != null && instructor.getInstructorId() != null) {
            Set<ConstraintViolation<InstructorEntity>> constraintViolations = validator.validate(instructor);

            if (constraintViolations.isEmpty()) {

                List<SessionEntity> sessionsOfInstructor = instructor.getSessionEntity();

                for (Long sessionIdsToAdd : sessionIds) {
                    sessionsOfInstructor.add(sessionEntitySessionBeanLocal.retrieveSessionBySessionId(sessionIdsToAdd));
                    sessionEntitySessionBeanLocal.retrieveSessionBySessionId(sessionIdsToAdd).setInstructor(instructor);
                }
                instructor.setSessionEntity(sessionsOfInstructor);

                InstructorEntity instructorToUpdate = retrieveInstructorByInstructorId(instructor.getInstructorId());

                instructor.setInstructorName(instructorToUpdate.getInstructorName());
                instructor.setPhone(instructorToUpdate.getPhone());
                instructor.setEmail(instructorToUpdate.getEmail());
                instructor.setPartnerEntity(instructorToUpdate.getPartnerEntity());
                instructor.setSessionEntity(instructorToUpdate.getSessionEntity());

            }
        }
    }

    @Override
        public void deleteInstructor(Long instructorIdToDelete) {
        InstructorEntity instructorToDelete = em.find(InstructorEntity

.class  


, instructorIdToDelete);

        if (instructorToDelete != null) {
            instructorToDelete.getPartnerEntity().getInstructorEntity().remove(instructorToDelete);
            List<SessionEntity> instructorSessions = instructorToDelete.getSessionEntity();
            for (SessionEntity session : instructorSessions) {
                session.setInstructor(null);
            }
            em.remove(instructorToDelete);
        }
    }

    @Override
        public void deleteInstructors(List<Long> instructorIdsToDelete) {
        for (Long instructorIdToDelete : instructorIdsToDelete) {
            deleteInstructor(instructorIdToDelete);
        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<InstructorEntity>> constraintViolations) {
        String msg = "Input data validation error!:";
        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        return msg;
    }

    // public InstructorEntity retrieveInstructorBySessionId
    // public void updateInstructor
    // public Long addInstructorToSession
    // public Long removeInstructorFromSession
}
