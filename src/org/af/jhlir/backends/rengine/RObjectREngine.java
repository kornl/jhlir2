package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RDataFrame;
import org.af.jhlir.call.REnvironment;
import org.af.jhlir.call.RObj;
import org.af.jhlir.call.RRef;
import org.af.jhlir.call.S4Obj;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPReference;


public class RObjectREngine<WRAPPED_TYPE extends REXP, RESOLVED_TYPE extends REXP>
        implements RObj<WRAPPED_TYPE> {

    protected RCallServicesREngine rs;
    private RESOLVED_TYPE cached;
    private REXPReference ref;

    protected RObjectREngine(RCallServicesREngine rs, WRAPPED_TYPE wrapped) {
        if (wrapped == null)
            throw new RuntimeException("Tried to create RObjectWrapper on null!");
        this.rs = rs;
        this.cached = (RESOLVED_TYPE) wrapped;
    }

    protected RObjectREngine(RCallServicesREngine rs, REXPReference wrapped) {
        if (wrapped == null)
            throw new RuntimeException("Tried to create RObjectWrapper on null!");
        this.rs = rs;
        this.ref = wrapped;
    }

    public RESOLVED_TYPE getResolved() {
        if (getWrapped() instanceof REXPReference) {
            if (cached == null)
                cached = (RESOLVED_TYPE) ref.resolve();
            return cached;
        }
        else
            return (RESOLVED_TYPE) getWrapped();
    }

    protected WRAPPED_TYPE getRef() {
        if (getWrapped() instanceof REXPReference)
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

    public boolean isRef() {
        return ref != null;
    }


    public RNumericREngine asRNumeric() {
        return new RNumericREngine(rs, (org.rosuda.REngine.REXPDouble) getWrapped());
    }

    public RIntegerREngine asRInteger() {
        return new RIntegerREngine(rs, (org.rosuda.REngine.REXPInteger) getWrapped());
    }

    public RLogicalREngine asRLogical() {
        return new RLogicalREngine(rs, (org.rosuda.REngine.REXPLogical) getWrapped());
    }

    public RCharREngine asRChar() {
        return new RCharREngine(rs, (org.rosuda.REngine.REXPString) getWrapped());
    }

    public RFactorREngine asRFactor() {
        return new RFactorREngine(rs, (org.rosuda.REngine.REXPFactor) getWrapped());
    }

    public RMatrixDoubleREngine asRMatrixDouble() {
        return new RMatrixDoubleREngine(rs, (org.rosuda.REngine.REXPDouble) getWrapped());
    }

//    public RMatrixCharREngine asRMatrixChar() {
//        return new RMatrixCharREngine(rs, (REXPString) getWrapped());
//    }

    public RListREngine asRList() {
        return new RListREngine(rs, (org.rosuda.REngine.REXPGenericVector) getWrapped());
    }

    public RDataFrame asRDataFrame() {
        return new RDataFrameREngine(rs, (org.rosuda.REngine.REXPGenericVector) getWrapped());
    }

    public REnvironment asREnvironment() {
        return new REnvironmentREngine(rs, (org.rosuda.REngine.REXPEnvironment) getWrapped());
    }

    public S3ObjREngine asS3Obj() {
        return new S3ObjREngine(rs, (org.rosuda.REngine.REXPGenericVector) getWrapped());
    }

	public S4Obj asS4Obj() {
		return new S4ObjREngine(rs, (REXP) getWrapped());
	}
}

