package org.af.jhlir.call;

import java.util.List;

/**
 * Interface for a RList Object that extends RObj.
 * @param <WRAPPED_TYPE> underlying type of the used back-end, @see RObj
 */
//todo check null / NA horror. fuck R!
public interface RList<WRAPPED_TYPE> extends RObj<WRAPPED_TYPE> {


	/**
	 * Returns the number of elements in this list.
	 * @return the number of elements in this list
	 */
    int getLength();
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param i index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= getLength()</tt>)
     */
    RObj get(int i);
    
    /**
     * Returns the element at the first position which has the given name in this list or null if no such name exists
     *
     * @param name name of the element to return
     * @return first element of the specified name in this list or null
     */
    RObj get(String name);
    
    /**
     * Returns the elements in this list
     *
     * @param name name of the element to return
     * @return the elements in this list
     */
    List<RObj> getElements();

    /**
     * List can either be named or unnamed. The first case implies that there is always a name for each position of the list (possibly ""),
     * while the latter case means that no names attribute exists at all.
     * @return {@code true} iff the positions of the list are named
     */
    boolean hasNames();



    /**
     * Returns the name at the specified position of this list.
     * @param i index of the position
     * @return the name of the specified position of this list
     * @throws NullPointerException if {@code hasNames()} is false
     */
    String getName(int i);
    
    /**
     * If no name is set {@code null} is returned.
     * Otherwise returns a String Array of length equal to {@code getLength()}
     * that contains the names of the positions in this list.
     * @return the names of this list
     */
    String[] getNames();
    
    /**
     * If no name is set {@code null} is returned.
     * Returns a List of Strings of size equal to {@code getLength()}
     * that contains the names of the positions in this list.
     * @return the names of this list
     */
    List<String> getNamesAsList();
}
