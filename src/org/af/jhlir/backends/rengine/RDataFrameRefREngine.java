package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RDataFrameRef;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPReference;

public class RDataFrameRefREngine
        extends RDataFrameREngine
        implements RDataFrameRef<REXPReference, REXPGenericVector> {

    public RDataFrameRefREngine(RCallServicesREngine rs, REXPReference wrapped) {
        super(rs, wrapped);
    }
}