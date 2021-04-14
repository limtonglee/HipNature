package ws.rest.resources;


import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.ClassEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PlanEntitySessionBeanLocal;
import ejb.stateless.PurchasedPlanEntitySessionBeanLocal;
import ejb.stateless.SessionEntitySessionBeanLocal;
import ejb.stateless.TransactionEntitySessionBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class SessionBeanLookup 
{

    BookingEntitySessionBeanLocal bookingEntitySessionBean = lookupBookingEntitySessionBeanLocal1();

    TransactionEntitySessionBeanLocal transactionEntitySessionBean = lookupTransactionEntitySessionBeanLocal();

    PurchasedPlanEntitySessionBeanLocal purchasedPlanEntitySessionBean = lookupPurchasedPlanEntitySessionBeanLocal();

    SessionEntitySessionBeanLocal sessionEntitySessionBean = lookupSessionEntitySessionBeanLocal();

    ClassEntitySessionBeanLocal classEntitySessionBean = lookupClassEntitySessionBeanLocal();

    PlanEntitySessionBeanLocal planEntitySessionBean = lookupPlanEntitySessionBeanLocal();
    
    
    
    private final String ejbModuleJndiPath;
    
    
    
    public SessionBeanLookup()
    {
        ejbModuleJndiPath = "java:global/PointOfSaleSystemV60/PointOfSaleSystemV60-ejb/";
    }
    
    
    
   public BookingEntitySessionBeanLocal lookupBookingEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (BookingEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/BookingEntitySessionBean!ejb.stateless.BookingEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
   public CustomerEntitySessionBeanLocal lookupCustomerEntitySessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/CustomerEntitySessionBean!ejb.stateless.CustomerEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public PlanEntitySessionBeanLocal lookupPlanEntitySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (PlanEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/PlanEntitySessionBean!ejb.stateless.PlanEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public ClassEntitySessionBeanLocal lookupClassEntitySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ClassEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/ClassEntitySessionBean!ejb.stateless.ClassEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public SessionEntitySessionBeanLocal lookupSessionEntitySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (SessionEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/SessionEntitySessionBean!ejb.stateless.SessionEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public PurchasedPlanEntitySessionBeanLocal lookupPurchasedPlanEntitySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (PurchasedPlanEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/PurchasedPlanEntitySessionBean!ejb.stateless.PurchasedPlanEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public TransactionEntitySessionBeanLocal lookupTransactionEntitySessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (TransactionEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/TransactionEntitySessionBean!ejb.stateless.TransactionEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    public BookingEntitySessionBeanLocal lookupBookingEntitySessionBeanLocal1() {
        try {
            Context c = new InitialContext();
            return (BookingEntitySessionBeanLocal) c.lookup("java:global/HipNature/HipNature-ejb/BookingEntitySessionBean!ejb.stateless.BookingEntitySessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
     
    
    
  
}