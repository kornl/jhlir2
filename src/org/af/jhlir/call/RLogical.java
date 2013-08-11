package org.af.jhlir.call;

import java.util.List;

public interface RLogical<WRAPPED_TYPE> extends RVector<WRAPPED_TYPE, boolean[], Boolean> {
    boolean[] getData();
    Boolean[] getDataAsObjArr();
    List<Boolean> getDataAsList();
}
