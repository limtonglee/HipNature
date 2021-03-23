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
public class RefundNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>RefundNotFoundException</code> without
     * detail message.
     */
    public RefundNotFoundException() {
    }

    /**
     * Constructs an instance of <code>RefundNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public RefundNotFoundException(String msg) {
        super(msg);
    }
}
