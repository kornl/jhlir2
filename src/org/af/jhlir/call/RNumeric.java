package org.af.jhlir.call;

import java.util.List;

public interface RNumeric<WRAPPED_TYPE> extends RVector<WRAPPED_TYPE, double[], Double> {
    double[] getData();
    Double[] getDataAsObjArr();
    List<Double> getDataAsList();
}
