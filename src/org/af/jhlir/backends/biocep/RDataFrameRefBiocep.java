package org.af.jhlir.backends.biocep;

import org.kchine.r.RDataFrame;
import org.kchine.r.RDataFrameRef;

public class RDataFrameRefBiocep
        extends RDataFrameBiocep
        implements org.af.jhlir.call.RDataFrameRef<RDataFrameRef, RDataFrame> {

    protected RDataFrameRefBiocep(RCallServicesBiocep rs, org.kchine.r.RDataFrameRef wrapped) {
        super(rs, wrapped);
    }
}