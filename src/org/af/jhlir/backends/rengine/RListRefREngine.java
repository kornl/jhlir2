package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RListRef;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPReference;

public class RListRefREngine
        extends RListREngine
    implements RListRef<REXPReference, REXPGenericVector> {

    public RListRefREngine(RCallServices rs, REXPReference wrapped) {
        super(rs, wrapped);
    }
}
