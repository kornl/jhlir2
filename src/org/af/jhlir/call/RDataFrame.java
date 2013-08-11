package org.af.jhlir.call;

import java.util.List;

/**
 * Interface for a RDataFrame Object that extends RObj.
 * @param <WRAPPED_TYPE> underlying type of the used back-end, see {@link RObj RObj}
 */
public interface RDataFrame<WRAPPED_TYPE> extends RObj<WRAPPED_TYPE>{

	/**
	 * Returns the number of rows in the data frame.
	 * @return the number of rows in the data frame
	 */
    int getRowCount();
    
	/**
	 * Returns the number of columns in the data frame.
	 * @return the number of columns in the data frame
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
    
    /**
     * Returns the name of the specified row.
     * @param i index of the row 
     * @return the name of the specified row
     */
    String getRowName(int i);
    
    /**
     * Returns the name of the specified column.
     * @param i index of the column 
     * @return the name of the specified column
     */
    String getColName(int i);
    
    /**
     * Returns the index of the row with 
     * the specified name in this data frame, 
     * or -1 if this data frame does not contain such a row.
     * @param name name of the row
     * @return the index of the row with 
     * the specified name in this data frame, 
     * or -1 if this data frame does not contain such a row.
     */
    int getRowIndex(String name);
    
    /**
     * Returns the index of the column with 
     * the specified name in this data frame, 
     * or -1 if this data frame does not contain such a column.
     * @param name name of the column
     * @return the index of the column with 
     * the specified name in this data frame, 
     * or -1 if this data frame does not contain such a column.
     */
    int getColIndex(String name);


//    RObj getRow(int i);
//    RObj getRow(String name);
    RVectorFactor getCol(int i);
    RVectorFactor getCol(String name);
    
    /**
     * Returns the element at the specified position in this data frame.
     * @param row row of the element to return
     * @param col column of the element to return
     * @return the element at the specified position in this data frame
     */
    Object get(int row, int col);

	List<RLegalName> getNumberVarsLN();
	
	List<RLegalName> getIntegerVarsLN();
	
	List<RLegalName> getNumericVarsLN();

	List<RLegalName> getColNamesLN();

	List<RLegalName> getFactorVarsLN();

	List<String> getFactorVars();

	List<String> getIntegerVars();

	List<String> getNumberVars();

	void setValue(int row, int col, Object value);

	void addRow(int row);

	void addRow(int row, RLegalName name);

	void addCol(int col, RLegalName name, RVector v);

	void addCol(int col, RLegalName name, RFactor v);

	void addCol(RLegalName name, RVectorFactor v);

	void delRow(int row);

	void delCol(int col);

//    public boolean isRNum(int i) {
//        return getCol(i) instanceof RNumeric;
//    }
//
//    public boolean isRInt(int i) {
//        return getCol(i) instanceof RInteger;
//    }
//
//    public boolean isRFactor(int i) {
//        return getCol(i) instanceof RFactor;
//    }
//
//    public boolean isRChar(int i) {
//        return getCol(i) instanceof RChar;
//    }
//
//    public boolean isRLogical(int i) {
//        return getCol(i) instanceof RLogical;
//    }
//
//
//    public List<String> getNumericVars();
//    public List<String> getFactorVars() {
//    public List<String> getNumberVars() {
//
//    public List<RLegalName> getNumberVarsLN() {
//    }
//
//    public List<String> getIntegerVars();


}
