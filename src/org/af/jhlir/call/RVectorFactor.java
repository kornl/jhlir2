package org.af.jhlir.call;

import java.util.List;

/**
 * Common interface for Vectors and Factors. Extends RObj.
 * @param <WRAPPED_TYPE> underlying type of the used back-end, see {@link RObj RObj}
 * @param <ARR_TYPE> used Java array to store the data.
 * @param <EL_TYPE> boxed type of an element of ARR_TYPE. Extends Object.
 * Example: For RInteger {@code ARR_TYPE} equals {@code int[]} and 
 * @code EL_TYPE} equals {@code Integer}.
 */

public interface RVectorFactor<WRAPPED_TYPE, ARR_TYPE, EL_TYPE> extends RObj<WRAPPED_TYPE>{

    /**
     * Returns the number of elements in this vector/factor.
     * @return the number of elements in this vector/factor
     */
    public int getLength();


    /**
     * Returns an array containing all of the elements
     * in this vector/factor in proper sequence
     * @return an array containing all of the elements
     * in this vector/factor in proper sequence
     */
    public ARR_TYPE getData();

    /**
     * Returns an array containing all of the elements
     * in this vector/factor in proper sequence.
     * If getData() returns an array of primitives, this methods converts to an array of Java Objects.
     * Note that this takes linear time for long arrays. 
     * @return an array containing all of the elements
     * in this vector/factor in proper sequence
     */
    public EL_TYPE[] getDataAsObjArr();

    /**
     * Returns a list containing all of the elements
     * in this vector/factor in proper sequence
     * @return a list containing all of the elements
     * in this vector/factor in proper sequence
     */
    public List<EL_TYPE> getDataAsList();


    /**
     * Returns the element at the specified position in this vector/factor.
     *
     * @param i index of the element to return
     * @return the element at the specified position in this vector/factor
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= getLength()</tt>)
     */
    public EL_TYPE get(int i);

    /**
     * Returns the name at the specified position of this vector/factor.
     * If the name is not set {@code RCallServices.NA_CHAR} is returned.
     * @param i index of the position
     * @return the name of the specified position of this vector/factor
     */
    String getName(int i);

    /**
     * If no name is set {@code null} is returned.
     * Otherwise returns a String Array of length equal to {@code getLength()}
     * that contains the names of the positions in this vector/factor.
     * If a name is not set {@code RCallServices.NA_CHAR} is used.
     * @return the names of this vector/factor
     */
    String[] getNames();

    /**
     * If no name is set {@code null} is returned.
     * Returns a List of Strings of size equal to {@code getLength()}
     * that contains the names of the positions in this vector/factor.
     * If a name is not set {@code RCallServices.NA_CHAR} is used.
     * @return the names of this vector/factor
     */
    List<String> getNamesAsList();

    /**
     * Returns true if the specified element is NA. 
     * @param i index of the element to test for NA
     * @return true if the specified element is NA
     */
    public boolean isNA(int i);
    
}
