package org.af.jhlir.call;

/**
 * The R reference interface.
 * The class that is providing by this way a reference to a R object 
 * must implement a method getResolved() to return the referenced object. 
 * @param <WRAPPED_REF> underlying type of the used back-end, @see RObj
 * @param <RESOLVED_TYPE> type of the resolved Object
 */
public interface RRef<WRAPPED_REF, RESOLVED_TYPE> {
    RESOLVED_TYPE getResolved();
}
