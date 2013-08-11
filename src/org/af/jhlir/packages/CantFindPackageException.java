package org.af.jhlir.packages;

/**
 * Exception is thrown, if R package is not found in repository.
 *
 */

public class CantFindPackageException extends Exception{

    public CantFindPackageException(String message) {
        super(message);
    }

}
