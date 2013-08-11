package org.af.jhlir.call;

import java.util.List;

public interface REnvironment<WRAPPED_TYPE> extends RObj<WRAPPED_TYPE> {
    String[] getNames();
    List<String> getNamesAsList();
    RObj get(String name);
    REnvironment getParentEnv();
}
