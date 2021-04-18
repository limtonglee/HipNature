/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.ClassTypeEntitySessionBeanLocal;
import ejb.stateless.CreditPlanSessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.InstructorEntitySessionBeanLocal;
import ejb.stateless.PartnerEntitySessionBean;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.ReviewEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TagEntitySessionBeanLocal;
import entity.BookingEntity;
import entity.ClassEntity;
import entity.ClassTypeEntity;
import entity.CreditPlanEntity;
import entity.CustomerEntity;
import entity.InstructorEntity;
import entity.PartnerEntity;
import entity.PlanEntity;
import entity.PurchasedPlanEntity;
import entity.ReviewEntity;
import entity.SessionEntity;
import entity.TagEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import util.exception.CreateNewClassException;
import util.exception.InstructorNotFoundException;

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

    @EJB(name = "ReviewEntitySessionBeanLocal")
    private ReviewEntitySessionBeanLocal reviewEntitySessionBeanLocal;

    @EJB(name = "CreditPlanSessionBeanLocal")
    private CreditPlanSessionBeanLocal creditPlanSessionBeanLocal;
    
    

    @EJB
    private ClassEntitySessionBeanLocal classEntitySessionBean;

    @EJB
    private InstructorEntitySessionBeanLocal instructorEntitySessionBeanLocal;

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
            partnerEntitySessionBeanLocal.retrievePartnerByUsername("partner1");
        } catch (PartnerNotFoundException ex) {
            try {
                initializeData();
            } catch (InstructorNotFoundException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (CreateNewClassException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (ParseException ex1) {
                Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    private void createPartner() throws ParseException, CreateNewClassException {
        try {
            PartnerEntity partner1 = new PartnerEntity("Partner 1", "87654321", "partner1@gmail.com", "Singapore", "partner1", "password");
            partnerEntitySessionBeanLocal.createNewPartner(partner1);
            PartnerEntity partner2 = new PartnerEntity("Partner 2", "61234567", "partner2@gmail.com", "Singapore", "partner2", "password");
            partnerEntitySessionBeanLocal.createNewPartner(partner2);
            PartnerEntity partner3 = new PartnerEntity("Partner 3", "62345678", "partner3@gmail.com", "Singapore", "partner3", "password");
            partnerEntitySessionBeanLocal.createNewPartner(partner3);

            InstructorEntity instructor1 = new InstructorEntity("John Tan", "91234567", "johntan@gmail.com", partner1);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor1);
            InstructorEntity instructor2 = new InstructorEntity("May Tan", "92345678", "maytan@gmail.com", partner2);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor2);
            InstructorEntity instructor3 = new InstructorEntity("June Tan", "93456789", "junetan@gmail.com", partner3);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor3);
            InstructorEntity instructor4 = new InstructorEntity("Damien Tan", "93425323", "damientan@gmail.com", partner1);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor4);
            InstructorEntity instructor5 = new InstructorEntity("David Tan", "93527534", "davidtan@gmail.com", partner1);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor5);
            InstructorEntity instructor6 = new InstructorEntity("Darrell Tan", "93253536", "darrelltan@gmail.com", partner1);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor6);
            InstructorEntity instructor7 = new InstructorEntity("Ashley Tan", "93567752", "ashleytan@gmail.com", partner2);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor7);
            InstructorEntity instructor8 = new InstructorEntity("Faith Tan", "96578737", "faithtan@gmail.com", partner2);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor8);
            InstructorEntity instructor9 = new InstructorEntity("Sean Tan", "91224467", "seantan@gmail.com", partner3);
            instructorEntitySessionBeanLocal.createNewInstructorByIns(instructor9);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            java.util.Date startTime1 = sdf.parse("06/12/2019 12:00");
            java.util.Date endTime1 = sdf.parse("06/12/2019 14:00");
            java.util.Date startTime2 = sdf.parse("07/12/2019 12:00");
            java.util.Date endTime2 = sdf.parse("07/12/2019 14:00");
            java.util.Date startTime3 = sdf.parse("08/12/2019 12:00");
            java.util.Date endTime3 = sdf.parse("08/12/2019 14:00");
            java.util.Date startTime4 = sdf.parse("09/12/2019 12:00");
            java.util.Date endTime4 = sdf.parse("09/12/2019 14:00");
            java.util.Date startTime5 = sdf.parse("10/12/2019 12:00");
            java.util.Date endTime5 = sdf.parse("10/12/2019 14:00");
            
            // to test update sessions, need to create new classes and link to partner. then create sessions that link to the classes

//            SessionEntity session1 = new SessionEntity("Tembusu", startTime1, endTime1, 2, "61234594", 40, "Available", LocationTypeEnum.CENTRAL, instructor1);
//            sessionEntitySessionBean.createNewSession(session1);
//            instructor1.getSessionEntity().add(session1);
//
//            SessionEntity session2 = new SessionEntity("CAPT", startTime2, endTime2, 2, "62345353", 40, "Available", LocationTypeEnum.CENTRAL, instructor1);
//            sessionEntitySessionBean.createNewSession(session2);
//            instructor1.getSessionEntity().add(session2);
//
//            SessionEntity session3 = new SessionEntity("RC4", startTime3, endTime3, 2, "69568344", 40, "Available", LocationTypeEnum.CENTRAL, instructor2);
//            sessionEntitySessionBean.createNewSession(session3);
//            instructor2.getSessionEntity().add(session3);
//
//            SessionEntity session4 = new SessionEntity("RVRC", startTime4, endTime4, 2, "69324536", 40, "Available", LocationTypeEnum.CENTRAL, instructor2);
//            sessionEntitySessionBean.createNewSession(session4);
//            instructor2.getSessionEntity().add(session4);
//
//            SessionEntity session5 = new SessionEntity("USP", startTime5, endTime5, 2, "64363943", 40, "Available", LocationTypeEnum.CENTRAL, instructor2);
//            sessionEntitySessionBean.createNewSession(session5);
//            instructor2.getSessionEntity().add(session5);
            CustomerEntity cus1 = new CustomerEntity("Mark", "91234567", "mark.tan@gmail.com", "123 Kent Ridge Road", "marktan123", "password123", CustomerTypeEnum.NORMAL);
            customerEntitySessionBeanLocal.createNewCustomer(cus1);
            CustomerEntity cus2 = new CustomerEntity("Rachel", "93244543", "rachel.lee@gmail.com", "456 Orchard Road", "rachellee", "password123", CustomerTypeEnum.STUDENT);
            customerEntitySessionBeanLocal.createNewCustomer(cus2);
            CustomerEntity cus3 = new CustomerEntity("Edith", "95359465", "edith.chan@gmail.com", "12 Namly Place", "edithchan", "password123", CustomerTypeEnum.ELDERLY);
            customerEntitySessionBeanLocal.createNewCustomer(cus3);

            ClassTypeEntity classType1 = new ClassTypeEntity("Art");
            classTypeEntitySessionBeanLocal.createClassType(classType1); 
            ClassTypeEntity classType2 = new ClassTypeEntity("Dance");
            classTypeEntitySessionBeanLocal.createClassType(classType2); 
            ClassTypeEntity classType3 = new ClassTypeEntity("Meditation");
            classTypeEntitySessionBeanLocal.createClassType(classType3); 
            
            ClassEntity class2 = new ClassEntity("Yoga", 3, LocationTypeEnum.CENTRAL, classType2, partner1, 5);
            classEntitySessionBean.NewClass(class2);
            ClassEntity class3 = new ClassEntity("Pottery", 3, LocationTypeEnum.WEST, classType1, partner1, 3);
            classEntitySessionBean.NewClass(class3);
            ClassEntity class4 = new ClassEntity("Painting", 3, LocationTypeEnum.CENTRAL, classType1, partner1, 4);
            classEntitySessionBean.NewClass(class4);
                    
            ReviewEntity review1 = new ReviewEntity(3, "Decent class but instructor was 5 mins late.", cus1, class2);
            reviewEntitySessionBeanLocal.createNewReview(review1);
            ReviewEntity review2 = new ReviewEntity(1, "Hated it", cus2, class3);
            reviewEntitySessionBeanLocal.createNewReview(review2);
            ReviewEntity review3 = new ReviewEntity(5, "Loved it", cus3, class4);
            reviewEntitySessionBeanLocal.createNewReview(review3);
            
            //PurchasedPlanEntity pp1 = new PurchasedPlanEntity()
            
            //BookingEntity booking1 = new BookingEntity(purchasedplan, sessionEntity)
            
        } catch (InputDataValidationException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createCustomer() {
    }

    private void createPlan() {
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(19.00, Long.valueOf(10), "Lite Plan", Long.valueOf(3)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(49.00, Long.valueOf(27), "Basic Plan", Long.valueOf(9)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(79.00, Long.valueOf(45), "Standard Plan", Long.valueOf(15)));
        planEntitySessionBeanLocal.createNewPlan(new PlanEntity(159.00, Long.valueOf(100), "Premium Plan", Long.valueOf(33)));
    }

    private void createCreditPlan() {
        creditPlanSessionBeanLocal.createNewCreditPlan(new CreditPlanEntity(10.00, 10L));
         creditPlanSessionBeanLocal.createNewCreditPlan(new CreditPlanEntity(20.00, 25L));
          creditPlanSessionBeanLocal.createNewCreditPlan(new CreditPlanEntity(30.00, 50L));
           creditPlanSessionBeanLocal.createNewCreditPlan(new CreditPlanEntity(40.00, 100L));
    }

    private void createClassTypeEntity() {
    }

    private void createTagEntity() {
        try {
            tagEntitySessionBeanLocal.createNewTag(new TagEntity("paint"));
            tagEntitySessionBeanLocal.createNewTag(new TagEntity("art"));
            tagEntitySessionBeanLocal.createNewTag(new TagEntity("dance"));
            tagEntitySessionBeanLocal.createNewTag(new TagEntity("relax"));
            tagEntitySessionBeanLocal.createNewTag(new TagEntity("Intensive"));
        } catch (InputDataValidationException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initializeData() throws InstructorNotFoundException, ClassNotFoundException, CreateNewClassException, ParseException {
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
