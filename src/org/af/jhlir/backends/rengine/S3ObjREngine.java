package org.af.jhlir.backends.rengine;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.S3Obj;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPMismatchException;

public class S3ObjREngine
        extends RObjectREngine<REXPGenericVector, REXPGenericVector>
        implements S3Obj<REXPGenericVector> {

    public S3ObjREngine(RCallServices rs, REXPGenericVector wrapped) {
        super(rs, wrapped);
    }

    public String[] getS3Classes() {
        try {
            return getWrapped().getAttribute("class").asStrings();
            //todo exc
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getS3ClassesAsList() {
        return Arrays.asList(getS3Classes());
    }

    public String getS3Class() {
        return getS3Classes()[0];
    }
}
