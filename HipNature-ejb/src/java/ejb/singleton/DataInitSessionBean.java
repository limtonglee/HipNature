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
import util.exception.InputDataValidationException;
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

    private void createPartner() throws ParseException {
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

    private void createClassTypeEntity() {
        try {
            classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Art"));
            classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Dance"));
            classTypeEntitySessionBeanLocal.createClassType(new ClassTypeEntity("Meditation"));
        } catch (InputDataValidationException ex) {
            Logger.getLogger(DataInitSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void createInstructorEntity() {
        //instructorEntitySessionBean.createNewInstructor(new InstructorEntity("Mr Woekout", "12345678", "Mona@gmail.com"));
    }

    private void createClassEntity() throws ClassNotFoundException, CreateNewClassException {
        classEntitySessionBean.NewClass(new ClassEntity(classTypeEntitySessionBeanLocal.retrieveClassTypeByClassId(1l), "ART 101", new Integer(30), LocationTypeEnum.CENTRAL));
    }

    private void createSessionEntity() throws InstructorNotFoundException, ClassNotFoundException {
        sessionEntitySessionBean.createNewSession(new SessionEntity("Singapore 1", new java.util.Date(), new Integer(2), "12345678", 30, "ON", LocationTypeEnum.CENTRAL, instructorEntitySessionBeanLocal.retrieveInstructorByInstructorId(1l), classEntitySessionBean.retrieveClassByClassId(1l)));
        sessionEntitySessionBean.createNewSession(new SessionEntity("Singapore 2", new java.util.Date(), new Integer(2), "12345678", 30, "ON", LocationTypeEnum.CENTRAL, instructorEntitySessionBeanLocal.retrieveInstructorByInstructorId(1l), classEntitySessionBean.retrieveClassByClassId(1l)));

    }

    private void initializeData() throws InstructorNotFoundException, ClassNotFoundException, CreateNewClassException, ParseException {
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