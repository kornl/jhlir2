package org.af.jhlir.backends.biocep;

public class RNumericRefBiocep
        extends RNumericBiocep
        implements org.af.jhlir.call.RNumericRef<org.kchine.r.RNumericRef, org.kchine.r.RNumeric> {

    protected RNumericRefBiocep(RCallServicesBiocep rs, org.kchine.r.RNumericRef wrapped) {
        super(rs, wrapped);
    }
}
