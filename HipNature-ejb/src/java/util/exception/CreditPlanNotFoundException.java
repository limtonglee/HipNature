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
public class CreditPlanNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>CreditPlanNotFoundException</code>
     * without detail message.
     */
    public CreditPlanNotFoundException() {
    }

    /**
     * Constructs an instance of <code>CreditPlanNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreditPlanNotFoundException(String msg) {
        super(msg);
    }
}
