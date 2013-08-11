package org.af.jhlir.call;

import java.util.List;

public interface RMatrix<WRAPPED_TYPE, ARR_TYPE, EL_TYPE> extends RObj<WRAPPED_TYPE>{
	/**
	 * Returns the number of rows in the matrix.
	 * @return the number of rows in the matrix
	 */
	int getRowCount();
    
	/**
	 * Returns the number of columns in the matrix.
	 * @return the number of columns in the matrix
	 */
	int getColumnCount();
    
    /**
     * Returns the names of the rows.
     * @return the names of the rows
     */
    String[] getRowNames();
    
    /**
     * Returns the names of the columns.
     * @return the names of the columns
     */
    String[] getColNames();
    
    /**
     * Returns the names of the rows.
     * @return the names of the rows
     */    
    List<String> getRowNamesAsList();
    
    /**
     * Returns the names of the columns.
     * @return the names of the columns
     */
    List<String> getColNamesAsList();
    
    ARR_TYPE getData();
    
    /**
     * Returns the element at the specified position in this matrix.
     * @param row row of the element to return
     * @param col column of the element to return
     * @return the element at the specified position in this matrix
     */
    EL_TYPE get(int row, int col);
    
    EL_TYPE[][] getDataAsObjArr();

    /**
     * Returns true if the specified element is NA. 
     * @param row row index of the element to test for NA
     * @param col column index of the element to test for NA
     * @return true if the specified element is NA
     */
    boolean isNA(int row, int col);
}
