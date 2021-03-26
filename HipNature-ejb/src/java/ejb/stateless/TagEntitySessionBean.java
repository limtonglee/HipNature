/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.TagEntity;
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
import util.exception.TagNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class TagEntitySessionBean implements TagEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public TagEntitySessionBean() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory(); 
        this.validator = validatorFactory.getValidator();

    }
    
    @Override
    public List<TagEntity> retrieveAllTags() {
        Query query = em.createQuery("SELECT t FROM TagEntity t ORDER BY t.tagId ASC");
        List<TagEntity> list = query.getResultList();
//            for (TagEntity tag:list){
//                tag.getClassEntities().size();
//            }

        return list;
    }

    @Override
    public TagEntity createNewTag(TagEntity newTag) throws InputDataValidationException {
        Set<ConstraintViolation<TagEntity>>constraintViolations = validator.validate(newTag);
        if (constraintViolations.isEmpty()){
                em.persist(newTag);
                em.flush();
                return newTag;
        }
        else {
            throw new InputDataValidationException(prepareInputDataValidationException(constraintViolations));
        }
    }
        
    @Override
    public TagEntity retrieveTagByTagId(Long tagId) throws TagNotFoundException {
        TagEntity tagEntity = em.find(TagEntity.class, tagId);
        
        if (tagEntity != null) {
            return tagEntity;
        } else {
            throw new TagNotFoundException("Tag ID " + tagId + " does not exist!");
        }
    }

    private String prepareInputDataValidationException(Set<ConstraintViolation<TagEntity>>constraintViolations){
        String msg = "Input data validation error: ";
        for (ConstraintViolation constraint: constraintViolations){
            msg +="\n\t" + constraint.getPropertyPath() + " - " + constraint.getInvalidValue() + " : " + constraint.getMessage();
        }
        return msg;
    }
    
}
