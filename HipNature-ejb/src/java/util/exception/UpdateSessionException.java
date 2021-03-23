/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author leahj
 */
public class UpdateSessionException extends Exception {

    /**
     * Creates a new instance of <code>UpdateSessionException</code> without
     * detail message.
     */
    public UpdateSessionException() {
    }

    /**
     * Constructs an instance of <code>UpdateSessionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UpdateSessionException(String msg) {
        super(msg);
    }
}
