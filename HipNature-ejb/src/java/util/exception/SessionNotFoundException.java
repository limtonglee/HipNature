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
public class SessionNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>SessionNotFoundException</code> without
     * detail message.
     */
    public SessionNotFoundException() {
    }

    /**
     * Constructs an instance of <code>SessionNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SessionNotFoundException(String msg) {
        super(msg);
    }
}
