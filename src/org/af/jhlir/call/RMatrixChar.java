package org.af.jhlir.call;


public interface RMatrixChar<WRAPPED_TYPE> extends RMatrix<WRAPPED_TYPE, String[][], String> {
    String[][] getData();
}

