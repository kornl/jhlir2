package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RCallServices;
import org.af.jhlir.call.RChar;
import org.rosuda.REngine.REXPString;


public class RCharREngine
        extends RVectorREngine<REXPString, REXPString, String[], String>
        implements RChar<REXPString> {


    public RCharREngine(RCallServicesREngine rs, org.rosuda.REngine.REXPString wrapped) {
        super(rs, wrapped);
    }

    public String[] getData() {
        return getWrapped().asStrings();
    }

    public String[] getDataAsObjArr() {
        return getWrapped().asStrings();
    }

    public String get(int i) {
        return getWrapped().asStrings()[i];
    }


    public boolean isNA(int i) {
        return get(i).equals(RCallServices.NA_CHAR) ;
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
//
//    public static boolean isNA(String v) {
//        return ObjectUtils.equals(v, NA_VAL);
//    }
//
//    public String getNaVal() {
//        return NA_VAL;
//    }

}


