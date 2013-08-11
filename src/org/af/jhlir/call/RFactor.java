package org.af.jhlir.call;

import java.util.List;

public interface RFactor<WRAPPED_TYPE> extends RVectorFactor<WRAPPED_TYPE, String[], String>{

    String[] getData();
    String[] getDataAsObjArr();
    List<String> getDataAsList();

    /**
     * Get factor levels as String array.
     * @return factor levels as String array.
     */
    String[] getLevels();

    /**
     * Get factor levels as List of Strings.
     * @return factor levels as List of Strings.
     */
    List<String> getLevelsAsList();

    /**
     * Get integer codes for all elements of this factor (indices into level array, zero-based).
     * @return integer codes for all elements of this factor.
     */
    int[] getCodes();

    /**
     * Get integer code for elements i of this factor (index into level array, zero-based).
     * @return integer code for element i.
     */
    int getCode(int i);
}
