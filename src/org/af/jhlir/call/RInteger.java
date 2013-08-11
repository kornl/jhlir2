package org.af.jhlir.call;

import java.util.List;

public interface RInteger<WRAPPED_TYPE> extends RVector<WRAPPED_TYPE, int[], Integer> {
    int[] getData();
    Integer[] getDataAsObjArr();
    List<Integer> getDataAsList();
}