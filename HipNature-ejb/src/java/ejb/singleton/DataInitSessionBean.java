/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.InstructorEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.ClassEntity;
import entity.ClassTypeEntity;
import entity.CustomerEntity;
import entity.InstructorEntity;
import entity.PartnerEntity;
import entity.PlanEntity;
import entity.SessionEntity;
import entity.TagEntity;
import java.sql.Date;
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
import util.enumeration.LocationTypeEnum;
import util.exception.InstructorNotFoundException;
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
    private ClassEntitySessionBeanLocal classEntitySessionBean;
    
    @EJB
    private InstructorEntitySessionBeanLocal instructorEntitySessionBean;
    
    @EJB
    private SessionEntitySessionBeanLocal sessionEntitySessionBean;
    
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
        try {
            partnerEntitySessionBeanLocal.retrievePartnerByUsername("partner");
        } catch (PartnerNotFoundException ex) {
            try {
                initializeData();
            } catch (InstructorNotFoundException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    private void createPartner() {
        partnerEntitySessionBeanLocal.createNewPartner(new PartnerEntity("partner", "1234567", "partner@gmail.com", "Singapore", "partner", "password"));
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

    private void createClassTypeEntity() {
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Art"));
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Dance"));
        classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Meditation"));
    }

    private void createTagEntity() {
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("paint"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("art"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("dance"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("relax"));
        tagEntitySessionBeanLocal.createNewTag(new TagEntity("Intensive"));
    }

    private void createInstructorEntity() {
        instructorEntitySessionBean.createNewInstructor(new InstructorEntity("Mr Woekout", "12345678", "Mona@gmail.com"));
    }
    
    private void createClassEntity() throws ClassNotFoundException {
        classEntitySessionBean.createNewClass(new ClassEntity(classTypeEntitySessionBeanLocal.retrieveClassTypeByClassId(1l), "ART 101", new Integer(30), LocationTypeEnum.CENTRAL));
    }
    
    private void createSessionEntity() throws InstructorNotFoundException, ClassNotFoundException {
        sessionEntitySessionBean.createNewSession(new SessionEntity("Singapore 1", new java.util.Date(), new Integer(2), "12345678", 30, "ON", LocationTypeEnum.CENTRAL, instructorEntitySessionBean.retrieveInstructorByInstructorId(1l), classEntitySessionBean.retrieveClassByClassId(1l)));
        sessionEntitySessionBean.createNewSession(new SessionEntity("Singapore 2", new java.util.Date(), new Integer(2), "12345678", 30, "ON", LocationTypeEnum.CENTRAL, instructorEntitySessionBean.retrieveInstructorByInstructorId(1l), classEntitySessionBean.retrieveClassByClassId(1l)));

    }
    
    private void initializeData() throws InstructorNotFoundException, ClassNotFoundException {
        createPartner();
        createCustomer();
        createPlan();
        createCreditPlan();
        createClassTypeEntity();
        createTagEntity();
        createInstructorEntity();
         createClassEntity();
        createSessionEntity();
        
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
}
