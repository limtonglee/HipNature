/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import entity.PartnerEntity;
import entity.SessionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.InstructorNotFoundException;
import util.exception.InvalidLoginCredentialException;
import util.exception.PartnerNotFoundException;
import util.exception.SessionNotFoundException;
import util.security.CryptographicHelper;

/**
 *
 * @author leahj
 */
@Stateless
@LocalBean
public class PartnerEntitySessionBean implements PartnerEntitySessionBeanLocal {

    @EJB(name = "InstructorEntitySessionBeanLocal")
    private InstructorEntitySessionBeanLocal instructorEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public PartnerEntitySessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public PartnerEntity createNewPartner(PartnerEntity newPartnerEntity) throws InputDataValidationException {
        Set<ConstraintViolation<PartnerEntity>> constraintViolations = validator.validate(newPartnerEntity);
        if (constraintViolations.isEmpty()) {
            em.persist(newPartnerEntity);
            em.flush();
            return newPartnerEntity;
        } else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }

    }

    @Override
    public List<PartnerEntity> retrieveAllPartners() {
        Query query = em.createQuery("SELECT s FROM PartnerEntity s");

        return query.getResultList();
    }

    @Override
    public PartnerEntity retrievePartnerByPartnerId(Long partnerId) throws PartnerNotFoundException {
        PartnerEntity partnerEntity = em.find(PartnerEntity.class, partnerId);

        if (partnerEntity != null) {
            return partnerEntity;
        } else {
            throw new PartnerNotFoundException("Partner ID " + partnerId + " does not exist!");
        }

    }

    @Override
    public PartnerEntity retrievePartnerByUsername(String username) throws PartnerNotFoundException {

        Query query = em.createQuery("SELECT s FROM PartnerEntity s WHERE s.username = :inUsername");
        query.setParameter("inUsername", username);

        try {
            return (PartnerEntity) query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new PartnerNotFoundException("Partner Username " + username + " does not exist!");
        }
    }

    @Override
    public PartnerEntity partnerLogin(String username, String password) throws InvalidLoginCredentialException {
        try {
            System.out.println("Username " + username);
            System.out.println("Password " + password);
            PartnerEntity partnerEntity = retrievePartnerByUsername(username);
            System.out.println(partnerEntity.getSalt());

            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + partnerEntity.getSalt()));
            System.out.println(partnerEntity.getPassword().equals(passwordHash));
            System.out.println(passwordHash);
            System.out.println(partnerEntity.getPassword());
            if (partnerEntity.getPassword().equals(passwordHash)) {
                partnerEntity.getInstructorEntity().size();
                return partnerEntity;
            } else {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        } catch (PartnerNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }

    @Override
    public PartnerEntity retrievePartnerByInstructor(Long instructorId) throws InstructorNotFoundException {

        PartnerEntity instructorPartner = instructorEntitySessionBeanLocal.retrieveInstructorByInstructorId(instructorId).getPartnerEntity();
        Long pid = instructorPartner.getPartnerEntityId();

        Query query = em.createQuery("SELECT p FROM PartnerEntity P WHERE p.PartnerEntityId = :pid");
        query.setParameter("pid", pid);

        return (PartnerEntity) query.getResultList();
    }

    public List<SessionEntity> retrievePartnerClassesSessions(Long partnerId) throws PartnerNotFoundException, ClassNotFoundException, SessionNotFoundException {
        PartnerEntity partnerEntity = em.find(PartnerEntity.class, partnerId);
        
        List<SessionEntity> fullListOfSessions = new ArrayList<>();

        if (partnerEntity != null) {
            
            List<ClassEntity> partnerClasses = partnerEntity.getClassEntity();
            
            if (partnerClasses != null) {
                for (ClassEntity c : partnerClasses) {
                    List<SessionEntity> partnerClassesSessions = c.getSessionEntities();
                    if (partnerClassesSessions != null) {
                        for (SessionEntity s : partnerClassesSessions) {
                            fullListOfSessions.add(s);
                        }
                    } else {
                        throw new SessionNotFoundException ("No sessions found for partner " + partnerId);
                    }
                }
            } else {
                throw new ClassNotFoundException("No classes found for partner " + partnerId);
            }
            
        } else {
            throw new PartnerNotFoundException("Partner ID " + partnerId + " does not exist!");
        }
        return fullListOfSessions;
    }
    
    @Override
    public void updatePartner(PartnerEntity partner) throws PartnerNotFoundException, InputDataValidationException {
        Set<ConstraintViolation<PartnerEntity>> constraintViolations = validator.validate(partner);

        if (constraintViolations.isEmpty()) {
            //System.out.println("updateInstructor in ejb: constraint is empty");

            PartnerEntity partnerToUpdate = retrievePartnerByPartnerId(partner.getPartnerEntityId());
            //System.out.println("updateInstructor in ejb: retrieved instructor " + instructorToUpdate.getInstructorName());

            partnerToUpdate.setPartnerEntityName(partner.getPartnerEntityName());
            partnerToUpdate.setEmail(partner.getEmail());
            partnerToUpdate.setAddress(partner.getAddress());
            partnerToUpdate.setPhone(partner.getPhone());
        } else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }
    }

    private String prepareInputDataValidationException(Set<ConstraintViolation<PartnerEntity>> constraintViolations) {
        String msg = "Input data validation error: ";
        for (ConstraintViolation constraint : constraintViolations) {
            msg += "\n\t" + constraint.getPropertyPath() + " - " + constraint.getInvalidValue() + " : " + constraint.getMessage();
        }
        return msg;
    }
    @Override
    public List<PartnerEntity> retrieveAllPartnerLessCurrent(Long partnerId){
        Query query = em.createQuery("SELECT s FROM PartnerEntity s WHERE s.PartnerEntityId != :partnerId");
        query.setParameter("partnerId", partnerId);
        
        return query.getResultList();
    }
    
}
