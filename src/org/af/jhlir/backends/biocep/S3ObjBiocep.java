package org.af.jhlir.backends.biocep;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.S3Obj;

public class S3ObjBiocep
        extends RObjectBiocep<org.kchine.r.RS3, org.kchine.r.RS3>
        implements S3Obj<org.kchine.r.RS3>  {

    public S3ObjBiocep(RCallServicesBiocep rs, org.kchine.r.RS3 wrapped) {
        super(rs, wrapped);
    }

    public String[] getS3Classes() {
        return getResolved().getClassAttribute();
    }

    public List<String> getS3ClassesAsList() {
        return Arrays.asList(getS3Classes());
    }

    public String getS3Class() {
        return getS3Classes()[0];
    }

    //    public String[] getClasses() {
//        return getWrapped().getClassAttribute();
//    }
//
//    public String[] getClasses() {
//        return getWrapped().getClassAttribute();
//    }
}
