/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.SessionEntity;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.validation.ConstraintViolation;
import util.exception.DeleteSessionEntityException;
import util.exception.InputDataValidationException;
import util.exception.SessionNotFoundException;
import util.exception.TagNotFoundException;
import util.exception.UpdateSessionException;

/**
 *
 * @author kelly
 */
@Local
public interface SessionEntitySessionBeanLocal {

    public List<SessionEntity> retrieveAllSessions();

    public Long createNewSession(SessionEntity newSession);
 public void deleteSession(Long sessionEntityToDeleteId) throws DeleteSessionEntityException ;
    public SessionEntity retrieveSessionBySessionId(Long sessionId) throws SessionNotFoundException;
        public List<SessionEntity> searchSessionByName(String searchString);
    public List<SessionEntity> filterSessionsByTags(List<Long> tagIds,String location);
   public String prepareInputDataValidationException(Set<ConstraintViolation<SessionEntity>>constraintViolations);
    public void updateSession(SessionEntity sessionEntity, List<Long> tagIds) throws SessionNotFoundException, TagNotFoundException, UpdateSessionException, InputDataValidationException;


}
