package org.af.jhlir.backends.biocep;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.RObj;


public class REnvironmentBiocep
            extends RObjectBiocep<org.kchine.r.REnvironment, org.kchine.r.REnvironment>
            implements org.af.jhlir.call.REnvironment<org.kchine.r.REnvironment> {

    public REnvironmentBiocep(RCallServicesBiocep rs, org.kchine.r.REnvironment wrapped) {
        super(rs, wrapped);
    }

    public String[] getNames() {
        return getResolved().getData().keySet().toArray(new String[0]);
    }

    public List<String> getNamesAsList() {
        return Arrays.asList(getNames());
    }

    public RObj get(String name) {
        return rs.wrapObject(getWrapped().getData().get(name));
    }

    public REnvironmentBiocep getParentEnv() {
//        return rs.wrapObject(getWrapped().getData().get(name));
        return null;
    }
}
