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
public class InstructorNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>InstructorNotFoundException</code>
     * without detail message.
     */
    public InstructorNotFoundException() {
    }

    /**
     * Constructs an instance of <code>InstructorNotFoundException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InstructorNotFoundException(String msg) {
        super(msg);
    }
}
