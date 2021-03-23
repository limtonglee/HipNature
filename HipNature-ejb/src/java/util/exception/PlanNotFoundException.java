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
public class PlanNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PlanNotFoundException</code> without
     * detail message.
     */
    public PlanNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PlanNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PlanNotFoundException(String msg) {
        super(msg);
    }
}
