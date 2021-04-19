/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import entity.ClassTypeEntity;
import entity.PartnerEntity;
import entity.TagEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.enumeration.LocationTypeEnum;
import util.exception.CreateNewClassException;
import util.exception.DeleteClassEntityException;
import util.exception.InputDataValidationException;
import util.exception.TagNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class ClassEntitySessionBean implements ClassEntitySessionBeanLocal {

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    @EJB
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBeanLocal;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ClassEntitySessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public List<ClassEntity> retrieveAllClasses() {
        Query query = em.createQuery("SELECT c FROM ClassEntity c ORDER BY c.className ASC");
        List<ClassEntity> list = query.getResultList();
        if (list != null) {
            for (ClassEntity classE : list) {
                classE.getTagEntities().size();
            }
            return list;
        }
        return null;
    }

    @Override
    public List<ClassEntity> retrieveAllClassesByPartnerId(Long idValue) {
        Query query = em.createQuery("SELECT c FROM ClassEntity c WHERE c.partnerEntity.PartnerEntityId = :idNumber ORDER BY c.classId ASC");
        query.setParameter("idNumber", idValue);
        List<ClassEntity> list = query.getResultList();
        if (list != null) {
            for (ClassEntity classE : list) {
                classE.getTagEntities().size();
            }
            return list;
        }
        return null;
    }
    
     @Override
    public PartnerEntity retrievePartnerIdByClass(Long idValue) {
        Query query = em.createQuery("SELECT p FROM PartnerEntity p join  ClassEntity c on c.partnerEntity.PartnerEntityId = p.PartnerEntityId and c.classId=:idNumber");
        query.setParameter("idNumber", idValue);
        PartnerEntity list = (PartnerEntity) query.getSingleResult();
         System.out.println(list);
        if (list != null) {
                list.getClassEntity().size();
                list.getInstructorEntity().size();
            return list;
        }
        return null;
    }

    @Override
    public ClassEntity createNewClass(ClassEntity newClass, Long newClassTypeId, List<Long> newTagEntityId) throws InputDataValidationException, CreateNewClassException, ClassNotFoundException {
        Set<ConstraintViolation<ClassEntity>> constraintViolations = validator.validate(newClass);
        if (constraintViolations.isEmpty()) {

            try {
                ClassTypeEntity classTypeEntity = classTypeEntitySessionBeanLocal.retrieveClassTypeByClassId(newClassTypeId);
                newClass.setClassTypeEntity(classTypeEntity);

                em.persist(newClass);
                classTypeEntity.getClassEntities().add(newClass);
                if (newTagEntityId != null && !(newTagEntityId.isEmpty())) {
                    for (Long tag : newTagEntityId) {
                        TagEntity tagEntity = tagEntitySessionBeanLocal.retrieveTagByTagId(tag);
                        newClass.addTag(tagEntity);
                    }

                }
                em.flush();
                return newClass;
            } catch (TagNotFoundException ex) {
                throw new CreateNewClassException("An Error has occurred: " + ex.getMessage());
            }
        } else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }
    }

    @Override
    public List<ClassEntity> searchClassByName(String searchString) {
        Query query = em.createQuery("SELECT c FROM ClassEntity c where c.className LIKE :inSearchString");
        query.setParameter("inSearchString", "%" + searchString + "%");
        List<ClassEntity> classEntities = query.getResultList();

        return classEntities;
    }

    @Override
    public void updateClass(ClassEntity updateClass, Long newClassTypeId, List<Long> newTagEntityId) throws InputDataValidationException, CreateNewClassException, ClassNotFoundException {
        Set<ConstraintViolation<ClassEntity>> constraintViolations = validator.validate(updateClass);
        if (constraintViolations.isEmpty()) {
            try {
                ClassTypeEntity classTypeEntity = classTypeEntitySessionBeanLocal.retrieveClassTypeByClassId(newClassTypeId);
                ClassEntity classToUpdate = retrieveClassByClassId(updateClass.getClassId());
                if (newTagEntityId != null && !(newTagEntityId.isEmpty())) {
                    for (TagEntity removeId : classToUpdate.getTagEntities()) {
                        removeId.getClassEntities().remove(classToUpdate);
                    }
                    classToUpdate.getTagEntities().clear();
                    for (Long tag : newTagEntityId) {
                        TagEntity tagEntity = tagEntitySessionBeanLocal.retrieveTagByTagId(tag);
                        classToUpdate.addTag(tagEntity);
                    }
                }
                classToUpdate.setCredit(updateClass.getCredit());
                classToUpdate.setClassTypeEntity(classTypeEntity);
                classToUpdate.setLocationTypeEnum(updateClass.getLocationTypeEnum());
                classToUpdate.setClassRating(updateClass.getClassRating());
            } catch (TagNotFoundException ex) {
                throw new CreateNewClassException("An Error has occurred: " + ex.getMessage());
            }
        } else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }
    }

    //This delete method remove ClassEntity from database as well
    @Override
    public void deleteClass(Long classEntityToDeleteId) throws DeleteClassEntityException, ClassNotFoundException {
        ClassEntity classEntityToDelete = em.find(ClassEntity.class, classEntityToDeleteId);
        int size = classEntityToDelete.getSessionEntities().size();
        if (classEntityToDelete.getSessionEntities().size() == 0) {
            em.remove(classEntityToDelete);
        } else {
            throw new DeleteClassEntityException("Unable to delete class Id: " + classEntityToDeleteId + " There are still " + size + " session(s) left");
        }
    }

    @Override
    public ClassEntity NewClass(ClassEntity newClass) {
        em.persist(newClass);
        em.flush();
        return newClass;

    }

    @Override
    public ClassEntity retrieveClassByClassId(Long classId) throws ClassNotFoundException {
        ClassEntity classEntity = em.find(ClassEntity.class, classId);

        if (classEntity != null) {
            return classEntity;
        } else {
            throw new ClassNotFoundException("Class ID " + classId + " does not exist!");
        }
    }

    //public void updateClass()
    // public List<ClassEntity> retrieveClassesByName(String classname) 
    // public List<ClassEntity> retrieveClassesByPartner(Long pid) 
    private String prepareInputDataValidationException(Set<ConstraintViolation<ClassEntity>> constraintViolations) {
        String msg = "Input data validation error: ";
        for (ConstraintViolation constraint : constraintViolations) {
            msg += "\n\t" + constraint.getPropertyPath() + " - " + constraint.getInvalidValue() + " : " + constraint.getMessage();
        }
        return msg;
    }

    @Override
    public List<ClassEntity> retrieveAllClassEntityByLocation(LocationTypeEnum location) {
        Query query = em.createQuery("SELECT s from ClassEntity s WHERE s.locationTypeEnum = :location");
        query.setParameter("location", location);
        return query.getResultList();
    }
}
