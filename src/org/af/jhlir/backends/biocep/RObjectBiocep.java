package org.af.jhlir.backends.biocep;

import org.af.jhlir.call.REnvironment;
import org.af.jhlir.call.RObj;
import org.af.jhlir.call.RRef;
import org.af.jhlir.call.S4Obj;

public class RObjectBiocep<WRAPPED_TYPE extends org.kchine.r.RObject, RESOLVED_TYPE extends org.kchine.r.RObject>
        implements RObj<WRAPPED_TYPE> {
    protected RCallServicesBiocep rs;
    private RESOLVED_TYPE cached;
    private org.kchine.r.server.ReferenceInterface ref;

    // TODO find a way that this is never called
    protected RObjectBiocep(RCallServicesBiocep rs, WRAPPED_TYPE wrapped) {
        if (wrapped == null)
            throw new RuntimeException("Tried to create RObhjectWrapper on null!");
        this.rs = rs;
        if (wrapped instanceof org.kchine.r.server.ReferenceInterface) {
            this.ref = (org.kchine.r.server.ReferenceInterface) wrapped;
        } else {
            this.cached = (RESOLVED_TYPE) wrapped;
        }
    }

    public RESOLVED_TYPE getResolved() {
        if (this instanceof RRef) {
            if (cached == null)
                cached = (RESOLVED_TYPE) ref.extractRObject();
            return cached;
        }
        else
            return (RESOLVED_TYPE) getWrapped();
    }

    protected WRAPPED_TYPE getRef() {
        if (this instanceof RRef) 
            return getWrapped();
        else
            return null;
    }

    public WRAPPED_TYPE getWrapped() {
        if (this instanceof RRef)
            return (WRAPPED_TYPE) ref;
        else
            return (WRAPPED_TYPE) cached;
    }




//    public static RObjectBiocep makeW(RServices rs, RObject obj, String rClass) {
//        if (obj == null)
//            return null;
//        RObjectBiocep w = new RObjectBiocep(rs, obj);
//        w.rClass = rClass;
//        if (obj instanceof RInteger)
//            return w.asIntW();
//        else if (obj instanceof RNumeric)
//            return w.asNumW();
//        else if (obj instanceof RLogical)
//            return w.asLogW();
//        else if (obj instanceof RChar)
//            return w.asCharW();
//        else if (obj instanceof RFactor)
//            return w.asFactorW();
//
//        else if (obj instanceof RList)
//            return w.asListW();
//
//        else if (obj instanceof RDataFrameRef)
//            return w.asDfRefW();
//        else if (obj instanceof RDataFrame)
//            return w.asDfW();
//        else if (obj instanceof RMatrix)
//            return w.asRMatrixW();
//
//        return w;
//    }
//
//    public static RObjectBiocep makeW(RServices rs, RObject obj) {
//        return makeW(rs, obj, null);
//    }
//


//    public ROBJ_TYPE getObj() {
//        if (obj instanceof ReferenceInterface)
//            return extracted;
//        else
//            return obj;
//    }
//
//    public ROBJ_TYPE getReference() {
//        if (obj instanceof ReferenceInterface)
//            return obj;
//        else
//            return null;
//    }
//
//    public void setRObj(ROBJ_TYPE ro) {
//        obj = ro;
//        if (obj instanceof ReferenceInterface)
//            extracted = (ROBJ_TYPE)((ReferenceInterface) obj).extractRObject();
//    }
//
//    public RNumeric asNum() {
//        return (RNumeric) getObj();
//    }
//
    public RNumericBiocep asRNumeric() {
        return new RNumericBiocep(rs, (org.kchine.r.RNumeric) getWrapped());
    }

    public RIntegerBiocep asRInteger() {
        return new RIntegerBiocep(rs, (org.kchine.r.RInteger) getWrapped());
    }

    public RLogicalBiocep asRLogical() {
        return new RLogicalBiocep(rs, (org.kchine.r.RLogical) getWrapped());
    }

    public RCharBiocep asRChar() {
        return new RCharBiocep(rs, (org.kchine.r.RChar) getWrapped());
    }

    public RFactorBiocep asRFactor() {
        return new RFactorBiocep(rs, (org.kchine.r.RFactor) getWrapped());
    }


    public RMatrixDoubleBiocep asRMatrixDouble() {
        return new RMatrixDoubleBiocep(rs, (org.kchine.r.RMatrix) getWrapped());
    }

    public RListBiocep asRList() {
        return new RListBiocep(rs, (org.kchine.r.RList) getWrapped());
    }

    public RDataFrameBiocep asRDataFrame() {
        return new RDataFrameBiocep(rs, (org.kchine.r.RDataFrame) getWrapped());
    }

    public REnvironment asREnvironment() {
        return new REnvironmentBiocep(rs, (org.kchine.r.REnvironment) getWrapped());
    }

    public S3ObjBiocep asS3Obj() {
        return new S3ObjBiocep(rs, (org.kchine.r.RS3) getWrapped());
    }
    
	public S4Obj asS4Obj() { //TODO
		if (true) throw new RuntimeException("Not yet implemented!");
		return null;
	}

//
//    public RListRef asListRef() {
//        return (RListRef) getObj();
//    }
//
//    public RListW asListW() {
//        return new RListW(rs, asList());
//    }
//
//    public RListRefW asListRefW() {
//        return new RListRefW(rs, asListRef());
//    }
//
//    public RDataFrame asDf() {
//        return (RDataFrame) getObj();
//    }
//
//    public RDataFrameBiocep asDfW() {
//        return new RDataFrameBiocep(rs, asDf());
//    }
//
//    public RDataFrameRef asDfRef() {
//        return (RDataFrameRef) getObj();
//    }
//
//
//    public RMatrix asRMatrix() {
//        return (RMatrix) getObj();
//    }
//
//    public RMatrixW asRMatrixW() {
//        return new RMatrixW(rs, asRMatrix());
//    }
//
//
//
//    public String getRConverterMethod(Class type) {
//        if (type == RNumeric.class) return "as.numeric";
//        if (type == RInteger.class) return "as.integer";
//        if (type == RFactor.class) return "as.factor";
//        if (type == RChar.class) return "as.character";
//        if (type == RLogical.class) return "as.logical";
//        return null;
//    }
//
//    public <E extends RObject> E convert(Class<E> type) throws RemoteException {
//        String m = getRConverterMethod(type);
//        return (E) rs.call(m, getObj());
//    }



}
