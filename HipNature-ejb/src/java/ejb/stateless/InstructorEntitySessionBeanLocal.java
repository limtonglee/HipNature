/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.InstructorEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InstructorNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface InstructorEntitySessionBeanLocal {

    public List<InstructorEntity> retrieveAllInstructors();

    public Long createNewInstructor(InstructorEntity newInstructor);

    public InstructorEntity retrieveInstructorByInstructorId(Long instructorId) throws InstructorNotFoundException;
    
}
