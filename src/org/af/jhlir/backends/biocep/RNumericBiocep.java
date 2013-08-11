package org.af.jhlir.backends.biocep;

import org.apache.commons.lang.ArrayUtils;
import org.kchine.r.RNumeric;

public class RNumericBiocep
        extends RVectorBiocep<org.kchine.r.RNumeric, org.kchine.r.RNumeric, double[], Double>
        implements org.af.jhlir.call.RNumeric<RNumeric> {

    public RNumericBiocep(RCallServicesBiocep rs, org.kchine.r.RNumeric wrapped) {
        super(rs, wrapped);
    }

    public double[] getData() {
        return getWrapped().getValue();
    }

    public Double[] getDataAsObjArr() {
        return ArrayUtils.toObject(getData());
    }

    public Double get(int i) {
        return getWrapped().getValue()[i];
    }

    public boolean isNA(int i) {
        return Double.isNaN(get(i));
    }

    //    protected void _set(int i, Double val) {
//        getObj().getValue()[i] = val;
//    }
//
//    protected int[] _getIndexNA() {
//        return getObj().getIndexNA();
//    }
//
//    public static boolean isNA(Double v) {
//        return ObjectUtils.equals(v, NA_VAL);
//    }
//
//    public Double getNaVal() {
//        return NA_VAL;
//    }
//
//    public void setIndexNA(int[] ind) {
//        getObj().setIndexNA(ind);
//    }
//
//    public Double doubleVal() {
//        if (length() != 1)
//            throw new RuntimeException("Cannot convert RNumericBiocep to Double if length is " + length());
//        return at(0);
//    }
//
//    public Integer intVal() {
//        return doubleVal().intValue();
//    }
//
//
//    public boolean canBeInterpretedAsInt() {
//        for (double x:getObj().getValue()) {
//            if (x != (int) x) return false;
//        }
//        return true;
//    }
}
