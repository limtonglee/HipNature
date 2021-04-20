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
public class DeletePartnerException extends Exception {

    /**
     * Creates a new instance of <code>DeletePartnerException</code> without
     * detail message.
     */
    public DeletePartnerException() {
    }

    /**
     * Constructs an instance of <code>DeletePartnerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DeletePartnerException(String msg) {
        super(msg);
    }
}
