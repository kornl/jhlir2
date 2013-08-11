package org.af.jhlir.backends.biocep;

import org.af.jhlir.call.RVectorFactor;

abstract public class RVectorBiocep<WRAPPED_TYPE extends org.kchine.r.RVector, RESOLVED_TYPE extends org.kchine.r.RVector, ARR_TYPE, EL_TYPE>
        extends RVectorFactorBiocep<WRAPPED_TYPE, RESOLVED_TYPE, ARR_TYPE, EL_TYPE>
        implements RVectorFactor<WRAPPED_TYPE, ARR_TYPE, EL_TYPE> {

    protected RVectorBiocep(RCallServicesBiocep rs, WRAPPED_TYPE wrapped) {
        super(rs, wrapped);
    }

    public int getLength() {
        return getResolved().length();
    }


    public String[] getNames() {
        return getResolved().getNames();
    }

    //    public int[] getIndexNA () {
//        int[] ind = _getIndexNA();
//        return (ind == null) ? new int[0] : ind;
//    }
//
//    public void setNA(int i) {
//        setIndexNA(ArrayUtils.add(getIndexNA(), i));
//    }
//
//    public void setNonNA(int i) {
//        setIndexNA(ArrayUtils.removeElement(getIndexNA(), i));
//    }

//    // NO THIS IS DUMB WHEN DATA CHANGES
//    // hopefully this is smart.....
//    // we are caching NA accesses, this is because:
//    // a) it is very slow to always look thru all NA indices, but thats all biocep has internally
//    // b) it might be slow to compute the isNA boolean array always at construction time.
//    // (e.g. think very large vectors and no Nas)
//    public boolean isNA(int i) {
//        if(isNa[i] == null) {
//            boolean val = false;
//            for (int j:getIndexNA())
//                if (i==j) {
//                    val = true;break;
//                }
//            isNa[i] = val;
//        }
//        return isNa[i];
//    }

//
//    // shifts current el to the right
//    public void add(int i, JAVA_TYPE val) throws RemoteException{
//        RObject ro = rs.call("append", val, i);
//        setRObj((ROBJ_TYPE)ro);
//    }

}
