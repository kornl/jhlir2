package org.af.jhlir.call;

import java.util.List;

public interface S4Obj {
    String[] getS4Classes();
    List<String> getS4ClassesAsList();
    String getS4Class();
    RObj getSlot(String slot);
}
