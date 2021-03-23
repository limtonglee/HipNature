/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.InstructorEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.InstructorNotFoundException;
import util.exception.PartnerNotFoundException;
import util.exception.SessionNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface InstructorEntitySessionBeanLocal {

    public List<InstructorEntity> retrieveAllInstructors();

    public InstructorEntity retrieveInstructorByInstructorId(Long instructorId) throws InstructorNotFoundException;

    public void deleteInstructor(Long instructorIdToDelete);

    public void deleteInstructors(List<Long> instructorIdsToDelete);

    /*public Long createNewInstructor(InstructorEntity newInstructor, Long partnerEntityId, List<Long> sessionsId) throws PartnerNotFoundException, InputDataValidationException, SessionNotFoundException;*/

    public Long createNewInstructorByIns(InstructorEntity newInstructor);

    public void updateInstructor(InstructorEntity instructor, List<Long> sessionIds) throws InstructorNotFoundException, SessionNotFoundException;

    public List<InstructorEntity> retrieveInstructorsByPartner(Long pid);

    public Long createNewInstructor(InstructorEntity newInstructor, Long partnerEntityId) throws PartnerNotFoundException, InputDataValidationException;
    
}
