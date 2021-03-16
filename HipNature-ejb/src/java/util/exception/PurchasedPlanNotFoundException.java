/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author kelly
 */
public class PurchasedPlanNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PurchasedPlanNotFoundException</code>
     * without detail message.
     */
    public PurchasedPlanNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PurchasedPlanNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public PurchasedPlanNotFoundException(String msg) {
        super(msg);
    }
}
