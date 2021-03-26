/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import entity.ClassTypeEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class ClassTypeEntitySessionBean implements ClassTypeEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    public ClassTypeEntitySessionBean() {
    }
    
    @Override
    public Long createClassType(ClassTypeEntity newClassTypeEntity){
        em.persist(newClassTypeEntity);
        em.flush();
        
        return newClassTypeEntity.getClassTypeId();
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
 }
