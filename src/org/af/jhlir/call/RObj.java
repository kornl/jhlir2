package org.af.jhlir.call;

/**
 * 
 *
 * @param <WRAPPED_TYPE> underlying type of the used back-end
 */
public interface RObj<WRAPPED_TYPE> {
    WRAPPED_TYPE getWrapped();

    RNumeric asRNumeric();
    RInteger asRInteger();
    RLogical asRLogical();
    RChar asRChar();
    RFactor asRFactor();
    RMatrixDouble asRMatrixDouble();
//    RMatrixChar asRMatrixChar();
    RList asRList();
    RDataFrame asRDataFrame();
    REnvironment asREnvironment();
    S3Obj asS3Obj();
	S4Obj asS4Obj();
    
}
