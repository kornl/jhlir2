package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RLogical;
import org.rosuda.REngine.REXPLogical;

public class RLogicalREngine
        extends RVectorREngine<REXPLogical, REXPLogical, boolean[], Boolean>
        implements RLogical<REXPLogical> {

    public RLogicalREngine(RCallServices rs, org.rosuda.REngine.REXPLogical wrapped) {
        super(rs, wrapped);
    }

    public boolean[] getData() {
        byte[] bs = getResolved().asBytes();
        boolean[] res = new boolean[getLength()];
        for (int i=0;i<res.length; i++)
            res[i] = bs[i] != 0;
        return res;
    }

    public byte[] getDataAsBytes() {
        return getResolved().asBytes();
    }

    public Boolean[] getDataAsObjArr() {
        byte[] bs = getResolved().asBytes();
        Boolean[] res = new Boolean[getLength()];
        for (int i = 0; i < res.length; i++) {
            if (REXPLogical.isNA(bs[i]))
                res[i] = RCallServices.NA_RLOGICAL;
            else
                res[i] = (bs[i] != 0);
        }
        return res;
    }

    public Boolean get(int i) {
        byte b = getResolved().asBytes()[i];
        if (REXPLogical.isNA(b))
            return RCallServices.NA_RLOGICAL;
        return b != 0;
    }

    public Byte getAsByte(int i) {
        return getDataAsBytes()[i];
    }

    public boolean isNA(int i) {
        byte b = getResolved().asBytes()[i];
        return REXPLogical.isNA(b);
    }
}
