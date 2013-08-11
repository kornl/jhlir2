package org.af.jhlir.backends.biocep;

import org.af.jhlir.call.RListRef;


public class RListRefBiocep
        extends RListBiocep
        implements RListRef<org.kchine.r.RListRef, org.kchine.r.RList> {

    protected RListRefBiocep(RCallServicesBiocep rs, org.kchine.r.RListRef wrapped) {
        super(rs, wrapped);
    }
}
