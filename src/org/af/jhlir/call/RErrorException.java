package org.af.jhlir.call;

/**
 * A RErrorException (which extends RuntimeException) 
 * is thrown in the methods of RCallServices, when
 * an error occurred on the R side.
 * 
 * Example: For a RCallServices object res the 
 * following calls should produce RErrorExceptions:
 * 
 * {@code res.evalVoid("rnorm(100"); // missing bracket
 * res.evalVoid("rnorm(x=100)"); // unused argument x
 * res.evalVoid("stop(\"error\")"); // explicit stop}
 *    
 */
public class RErrorException extends RuntimeException {
	
    /** 
     * Constructs a new RErrorException with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for 
     *        later retrieval by the {@link #getMessage()} method.
     */
    public RErrorException(String message) {
    	super(message);
    }
}
