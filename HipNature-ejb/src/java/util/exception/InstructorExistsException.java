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
public class InstructorExistsException extends Exception {

    /**
     * Creates a new instance of <code>InstructorExistsException</code> without
     * detail message.
     */
    public InstructorExistsException() {
    }

    /**
     * Constructs an instance of <code>InstructorExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InstructorExistsException(String msg) {
        super(msg);
    }
}
