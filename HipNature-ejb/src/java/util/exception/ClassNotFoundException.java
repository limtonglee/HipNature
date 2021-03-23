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
public class ClassNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ClassNotFoundException</code> without
     * detail message.
     */
    public ClassNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ClassNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ClassNotFoundException(String msg) {
        super(msg);
    }
}
