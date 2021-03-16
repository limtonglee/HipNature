/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.PartnerEntitySessionBean;
import entity.PartnerEntity;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.PartnerNotFoundException;

/**
 *
 * @author leahj
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBean;

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    
    @PostConstruct
    public void postConstruct() {
         try
        {
            partnerEntitySessionBean.retrievePartnerByUsername("partner");
        }
        catch(PartnerNotFoundException ex)
        {
            Init();
        }

    }

    public void Init() {
        
        partnerEntitySessionBean.createNewPartner(new PartnerEntity("partner", "1234567", "partner@gmail.com", "Singapore", "partner", "password"));

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void persist(Object object) {
        em.persist(object);
    }
}
