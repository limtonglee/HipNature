/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.SessionEntity;
import java.util.List;
import javax.ejb.Local;
import util.exception.SessionNotFoundException;

/**
 *
 * @author kelly
 */
@Local
public interface SessionEntitySessionBeanLocal {

    public List<SessionEntity> retrieveAllSessions();

    public Long createNewSession(SessionEntity newSession);

    public SessionEntity retrieveSessionBySessionId(Long sessionId) throws SessionNotFoundException;
    
}
