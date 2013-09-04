package org.af.jhlir.call;

import java.util.List;


public interface RChar<WRAPPED_TYPE> extends RVector<WRAPPED_TYPE, String[], String> {
    String[] getData();
    String[] getDataAsObjArr();
    List<String> getDataAsList();
}
