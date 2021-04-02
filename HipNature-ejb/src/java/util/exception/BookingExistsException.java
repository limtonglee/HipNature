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
public class BookingExistsException extends Exception {

    /**
     * Creates a new instance of <code>BookingAlreadyCreatedException</code>
     * without detail message.
     */
    public BookingExistsException() {
    }

    /**
     * Constructs an instance of <code>BookingAlreadyCreatedException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public BookingExistsException(String msg) {
        super(msg);
    }
}
