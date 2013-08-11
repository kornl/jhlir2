package org.af.jhlir.backends.biocep;

import org.af.jhlir.call.RCallServices;
import org.apache.commons.lang.ArrayUtils;
import org.kchine.r.RLogical;

public class RLogicalBiocep
        extends RVectorBiocep<org.kchine.r.RLogical, org.kchine.r.RLogical, boolean[], Boolean>
        implements org.af.jhlir.call.RLogical<RLogical> {

    // NB: biocep does return boolean:false for NAs

    public RLogicalBiocep(RCallServicesBiocep rs, org.kchine.r.RLogical wrapped) {
        super(rs, wrapped);
    }

    public boolean[] getData() {
        return getResolved().getValue();
    }

    public Boolean[] getDataAsObjArr() {
        Boolean[] bs = ArrayUtils.toObject(getData());
        int[] naInds = getResolved().getIndexNA();
        for (int i=0; i<naInds.length; i++)
            bs[naInds[i]] = RCallServices.NA_RLOGICAL;
        return bs;
    }

    public Boolean get(int i) {
        if (isNA(i))
            return RCallServices.NA_RLOGICAL;
        return getResolved().getValue()[i];
    }


    public boolean isNA(int i) {
        return ArrayUtils.contains(getResolved().getIndexNA(), i);
    }


//    protected void _set(int i, Boolean val) {
//        getObj().getValue()[i] = val;
//    }
//
//    protected int[] _getIndexNA() {
//        return getObj().getIndexNA();
//    }
//
//    public static boolean isNA(Boolean v) {
//        return ObjectUtils.equals(v, NA_VAL);
//    }
//
//    public Boolean getNaVal() {
//        return NA_VAL;
//    }
//
//    public void setIndexNA(int[] ind) {
//        getObj().setIndexNA(ind);
//    }
}
