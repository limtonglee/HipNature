/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.RecordEntitySessionBeanLocal;
import entity.RecordEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author limtonglee
 */
@Singleton
@LocalBean
@Startup
public class RecordInitSessionBean {

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    @EJB
    private RecordEntitySessionBeanLocal recordEntitySessionBeanLocal;

    @PostConstruct
    public void PostConstruct() {

        if (em.find(RecordEntity.class, 1l) == null) {
            recordEntitySessionBeanLocal.createNewRecord(new RecordEntity("Record A"));
            recordEntitySessionBeanLocal.createNewRecord(new RecordEntity("Record B"));
            recordEntitySessionBeanLocal.createNewRecord(new RecordEntity("Record C"));
            recordEntitySessionBeanLocal.createNewRecord(new RecordEntity("Record D"));
            recordEntitySessionBeanLocal.createNewRecord(new RecordEntity("Record E"));
}

    }

}
