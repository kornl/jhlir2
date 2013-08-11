package org.af.jhlir.backends.rengine;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.RVectorFactor;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPString;

abstract public class RVectorFactorREngine<WRAPPED_TYPE extends REXP, RESOLVED_TYPE extends REXP, ARR_TYPE, EL_TYPE>
        extends RObjectREngine<WRAPPED_TYPE, RESOLVED_TYPE> implements RVectorFactor<WRAPPED_TYPE, ARR_TYPE, EL_TYPE> {

    protected RVectorFactorREngine(RCallServicesREngine rs, WRAPPED_TYPE wrapped) {
        super(rs, wrapped);
    }

    public List<EL_TYPE> getDataAsList() {
        return Arrays.asList(getDataAsObjArr());
    }

    public String getName(int i) {
        return getNames()[i];
    }

    public String[] getNames() {
        if (!getResolved().hasAttribute("names"))
            return null;
        return ((REXPString) getResolved().getAttribute("names")).asStrings();
    }

    public List<String> getNamesAsList() {
        if (getNames() == null)
            return null;
        return Arrays.asList(getNames());
    }
}
