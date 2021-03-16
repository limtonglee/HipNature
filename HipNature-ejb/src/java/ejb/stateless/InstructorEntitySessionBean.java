/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.InstructorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.InstructorNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class InstructorEntitySessionBean implements InstructorEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<InstructorEntity> retrieveAllInstructors() {
        Query query = em.createQuery("SELECT i FROM InstructorEntity I");
        
        return query.getResultList();
    }

    @Override
    public Long createNewInstructor(InstructorEntity newInstructor) {
        
        em.persist(newInstructor);
        em.flush();

        return newInstructor.getInstructorId();
        
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
 
    // public InstructorEntity retrieveInstructorBySessionId
    // public void updateInstructor
    // public Long addInstructorToSession
    // public Long removeInstructorFromSession
    
}
