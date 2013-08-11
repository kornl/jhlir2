package org.af.jhlir.backends.biocep;

import org.af.jhlir.call.RCallServices;
import org.af.jhlir.call.RChar;

public class RCharBiocep
        extends RVectorBiocep<org.kchine.r.RChar, org.kchine.r.RChar, String[], String>
        implements RChar<org.kchine.r.RChar> {


    public RCharBiocep(RCallServicesBiocep rs, org.kchine.r.RChar wrapped) {
        super(rs, wrapped);
    }

    public String[] getData() {
        return getResolved().getValue();
    }

    public String[] getDataAsObjArr() {
        return getResolved().getValue();
    }

    public String get(int i) {
        return getResolved().getValue()[i];
    }


    public boolean isNA(int i) {
        return get(i).equals(RCallServices.NA_CHAR);
    }

    //    protected void _set(int i, String val) {
//        getObj().getValue()[i] = val;
//    }
//
//    public String stringVal() {
//        if (length() != 1)
//            throw new RuntimeException("Cannot convert RCharBiocep to String if length is " + length());
//        return at(0);
//    }
//
//    protected int[] _getIndexNA() {
//        return getObj().getIndexNA();
//    }
//
//    public void setIndexNA(int[] ind) {
//        getObj().setIndexNA(ind);
//    }

}
