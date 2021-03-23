/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.RecordEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author limtonglee
 */
@Stateless
public class RecordEntitySessionBean implements RecordEntitySessionBeanLocal {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    
    @Override
    public List<RecordEntity> retrieveAllRecords() 
    {

        Query query = em.createQuery("SELECT r from RecordEntity r");
        return query.getResultList();
        
    }

    @Override
    public Long createNewRecord(RecordEntity newRecordEntity) {

        em.persist(newRecordEntity);
        em.flush();

        return newRecordEntity.getRecordId();

    }
}
