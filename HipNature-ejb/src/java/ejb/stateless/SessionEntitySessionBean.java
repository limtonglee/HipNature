/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.SessionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.SessionNotFoundException;

/**
 *
 * @author kelly
 */
@Stateless
public class SessionEntitySessionBean implements SessionEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @Override
    public List<SessionEntity> retrieveAllSessions() {
        Query query = em.createQuery("SELECT s FROM SessionEntity S");
        
        return query.getResultList();
    }

    @Override
    public Long createNewSession(SessionEntity newSession) {
        
        em.persist(newSession);
        em.flush();

        return newSession.getSessionId();
        
    }
        
    @Override
    public SessionEntity retrieveSessionBySessionId(Long sessionId) throws SessionNotFoundException {
        SessionEntity sessionEntity = em.find(SessionEntity.class, sessionId);
        
        if (sessionEntity != null) {
            return sessionEntity;
        } else {
            throw new SessionNotFoundException("Session ID " + sessionId + " does not exist!");
        }
    }

    
    
}
