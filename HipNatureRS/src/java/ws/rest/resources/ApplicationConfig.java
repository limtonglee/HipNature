/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.rest.resources;

import java.util.Set;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author leahj
 */
@javax.ws.rs.ApplicationPath("Resources")
public class ApplicationConfig extends javax.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(MultiPartFeature.class);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.rest.resources.BookingsResource.class);
        resources.add(ws.rest.resources.ClassResource.class);
        resources.add(ws.rest.resources.CorsFilter.class);
        resources.add(ws.rest.resources.CreditCardResource.class);
        resources.add(ws.rest.resources.CreditPlanResource.class);
        resources.add(ws.rest.resources.CustomerResource.class);
        resources.add(ws.rest.resources.FileResource.class);
        resources.add(ws.rest.resources.PlanResource.class);
        resources.add(ws.rest.resources.PurchasePlanResource.class);
        resources.add(ws.rest.resources.RefundResource.class);
        resources.add(ws.rest.resources.ReviewResource.class);
        resources.add(ws.rest.resources.SessionsResource.class);
        resources.add(ws.rest.resources.TransactionHistoryResource.class);
    }
    
}
