/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassTypeEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;

/**
 *
 * @author User
 */
@Stateless
public class ClassTypeEntitySessionBean implements ClassTypeEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ClassTypeEntitySessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory(); 
        this.validator = validatorFactory.getValidator();
    }
    
    @Override
    public ClassTypeEntity createClassType(ClassTypeEntity newClassTypeEntity) throws InputDataValidationException{
        Set<ConstraintViolation<ClassTypeEntity>>constraintViolations = validator.validate(newClassTypeEntity);
        if (constraintViolations.isEmpty()){
        em.persist(newClassTypeEntity);
        em.flush();
        
        return newClassTypeEntity;
        } 
        else{
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }
    }

    @Override
    public List<ClassTypeEntity> retrieveAllClassTypeEntity(){
        Query query = em.createQuery("Select s from ClassTypeEntity s ORDER BY s.classTypeId ASC");
        List<ClassTypeEntity> list = query.getResultList();
        for(ClassTypeEntity temp:list){
            temp.getClassEntities().size();
        }
        return list;
    }
    
     @Override
    public ClassTypeEntity retrieveClassTypeByClassId(Long classId) throws ClassNotFoundException {
        ClassTypeEntity classTypeEntity = em.find(ClassTypeEntity.class, classId);
        
        if (classTypeEntity != null) {
            return classTypeEntity;
        } else {
            throw new ClassNotFoundException("Class ID " + classId + " does not exist!");
        }
    }
    
    private String prepareInputDataValidationException(Set<ConstraintViolation<ClassTypeEntity>>constraintViolations){
        String msg = "Input data validation error: ";
        for (ConstraintViolation constraint: constraintViolations){
            msg +="\n\t" + constraint.getPropertyPath() + " - " + constraint.getInvalidValue() + " : " + constraint.getMessage();
        }
        return msg;
    }
 }
