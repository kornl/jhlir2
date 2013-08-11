package org.af.jhlir.backends.rengine;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.RMatrix;
import org.apache.commons.lang.ArrayUtils;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;

public abstract class RMatrixREngine<WRAPPED_TYPE extends REXP, RESOLVED_TYPE extends REXP, ARR_TYPE, EL_TYPE>
        extends RObjectREngine<WRAPPED_TYPE, RESOLVED_TYPE>
        implements RMatrix<WRAPPED_TYPE, ARR_TYPE, EL_TYPE> {

    public RMatrixREngine(RCallServicesREngine rs, WRAPPED_TYPE wrapped) {
        super(rs, wrapped);
    }

    public int getRowCount() {
        try {
            return (getWrapped().getAttribute("dim").asIntegers())[0];
        } catch (REXPMismatchException e) {
            return -1;
        }
    }

    public int getColumnCount() {
        try {
            return (getWrapped().getAttribute("dim").asIntegers())[1];
        } catch (REXPMismatchException e) {
            return -1;
        }
    }

    public String[] getRowNames() {
        try {
            return getWrapped().getAttribute("rownames").asStrings();
        } catch (REXPMismatchException e) {
            return null;
        }
    }

    public String[] getColNames() {
        try {
            return getWrapped().getAttribute("colnames").asStrings();
        } catch (REXPMismatchException e) {
            return null;
        }
    }

    public List<String> getRowNamesAsList() {
        return Arrays.asList(getRowNames());
    }

    public List<String> getColNamesAsList() {
        return Arrays.asList(getColNames());
    }

    protected int getIndex(int i, int j) {
        // biocep orders entries by columns
        return j * getRowCount() + i;
    }


    public int getRowIndex(String name) {
        return ArrayUtils.indexOf(getRowNames(), name);
    }

    public int getColIndex(String name) {
        return ArrayUtils.indexOf(getColNames(), name);
    }

    protected abstract EL_TYPE[][] createArr(int rows, int cols);

    public EL_TYPE[][] getDataAsObjArr() {
        EL_TYPE[][] res = createArr(getRowCount(), getColumnCount());
        for (int i=0; i<getRowCount(); i++)
            for (int j=0; j<getColumnCount(); j++) {
                res[i][j] = get(i,j);
            }
        return res;
    }

    public EL_TYPE get(int i, String col) {
        int j = ArrayUtils.indexOf(getColNames(), col);
        return get(i, j);
    }

    public EL_TYPE get(String row, int j) {
        int i = ArrayUtils.indexOf(getRowNames(), row);
        return get(i, j);
    }


    //    public RDataFrameBiocep asRDataFrameW() throws RemoteException {
//        return new RDataFrameBiocep(rs, (RDataFrame) rs.call("as.data.frame", getRObject())) ;
//    }


}
