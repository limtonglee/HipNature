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
public class UpdatePartnerException extends Exception {

    /**
     * Creates a new instance of <code>UpdatePartnerException</code> without
     * detail message.
     */
    public UpdatePartnerException() {
    }

    /**
     * Constructs an instance of <code>UpdatePartnerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdatePartnerException(String msg) {
        super(msg);
    }
}
