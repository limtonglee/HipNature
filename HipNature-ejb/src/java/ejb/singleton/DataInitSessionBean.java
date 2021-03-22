/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.ClassTypeEntity;
import entity.CustomerEntity;
import entity.PartnerEntity;
import entity.PlanEntity;
import entity.TagEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.CustomerTypeEnum;
import util.exception.InputDataValidationException;
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
    private ClassTypeEntitySessionBeanLocal classTypeEntitySessionBeanLocal;

    @EJB
    private TagEntitySessionBeanLocal tagEntitySessionBeanLocal;

    
    @EJB
    private PlanEntitySessionBeanLocal planEntitySessionBeanLocal;

    @EJB
    private CustomerEntitySessionBeanLocal customerEntitySessionBeanLocal;

    @EJB
    private PartnerEntitySessionBean partnerEntitySessionBeanLocal;
    

    @PersistenceContext(unitName = "HipNature-ejbPU")
    private EntityManager em;

    
    @PostConstruct
    public void postConstruct() {
         try
        {
            partnerEntitySessionBeanLocal.retrievePartnerByUsername("partner");
        }
        catch(PartnerNotFoundException ex)
        {
            initializeData();
        }

    }

    private void createPartner() {
        try {
            partnerEntitySessionBeanLocal.createNewPartner(new PartnerEntity("partner", "1234567", "partner@gmail.com", "Singapore", "partner", "password"));
        } catch (InputDataValidationException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createCustomer() {
        customerEntitySessionBeanLocal.createNewCustomer(new CustomerEntity("Mark", "91234567", "mark.tan@gmail.com", "123 Kent Ridge Road", "marktan123", "password123", CustomerTypeEnum.NORMAL));
        customerEntitySessionBeanLocal.createNewCustomer(new CustomerEntity("Rachel", "93244543", "rachel.lee@gmail.com", "456 Orchard Road", "rachellee", "password123", CustomerTypeEnum.STUDENT));
        customerEntitySessionBeanLocal.createNewCustomer(new CustomerEntity("Edith", "95359465", "edith.chan@gmail.com", "12 Namly Place", "edithchan", "password123", CustomerTypeEnum.ELDERLY));
    }
    
    private void createPlan() {
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(19.00, Long.valueOf(10), "Lite Plan", Long.valueOf(3)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(49.00, Long.valueOf(27), "Basic Plan", Long.valueOf(9)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(79.00, Long.valueOf(45), "Standard Plan", Long.valueOf(15)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(159.00, Long.valueOf(100), "Premium Plan", Long.valueOf(33)));
    }

    private void createCreditPlan() {
        
    }
    private void createClassTypeEntity(){
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Art"));
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Dance"));
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Meditation"));
    }
    private void createTagEntity(){
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("paint"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("art"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("dance"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("relax"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("Intensive"));
    }
    
    private void initializeData() {
        createPartner();
        createCustomer();
        createPlan();
        createCreditPlan();
        createClassTypeEntity();
        createTagEntity();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
