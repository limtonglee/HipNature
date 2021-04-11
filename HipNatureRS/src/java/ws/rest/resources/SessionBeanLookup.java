package ws.rest.resources;


import ejb.stateless.BookingEntitySessionBeanLocal;
import ejb.stateless.CustomerEntitySessionBeanLocal;
import ejb.stateless.PlanEntitySessionBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



public class SessionBeanLookup 
{

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
    
  
}