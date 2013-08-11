package org.af.jhlir.backends.rengine;

import org.rosuda.REngine.REXPVector;


abstract public class RVectorREngine<WRAPPED_TYPE extends REXPVector, RESOLVED_TYPE extends REXPVector, ARR_TYPE, EL_TYPE>
        extends RVectorFactorREngine<WRAPPED_TYPE, RESOLVED_TYPE, ARR_TYPE, EL_TYPE> {

    protected RVectorREngine(RCallServicesREngine rs, WRAPPED_TYPE wrapped) {
        super(rs, wrapped);
    }

    public int getLength() {
        return getResolved().length();
    }

}
