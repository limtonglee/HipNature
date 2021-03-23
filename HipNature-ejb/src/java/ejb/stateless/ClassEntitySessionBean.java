/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ClassEntity;
import entity.TagEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kelly
 */
@Stateless
public class ClassEntitySessionBean implements ClassEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<ClassEntity> retrieveAllClasses() {
        Query query = em.createQuery("SELECT c FROM ClassEntity c ORDER BY c.className ASC");
        List<ClassEntity> list = query.getResultList();
        if (list != null){
            for (ClassEntity classE:list){
                classE.getTagEntities().size();
            }
            return list;
            }
        return null;
    }

    @Override
    public Long createNewClass(ClassEntity newClass) {
        
        em.persist(newClass);
        em.flush();
      
        
        return newClass.getClassId();
        
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
}
