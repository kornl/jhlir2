package org.af.jhlir.backends.biocep;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.kchine.r.RChar;


public abstract class RMatrixBiocep<ARR_TYPE, EL_TYPE>
        extends RObjectBiocep<org.kchine.r.RMatrix, org.kchine.r.RMatrix>
        implements org.af.jhlir.call.RMatrix<org.kchine.r.RMatrix, ARR_TYPE, EL_TYPE> {

    public RMatrixBiocep(RCallServicesBiocep rs, org.kchine.r.RMatrix wrapped) {
        super(rs, wrapped);
    }

    public int getRowCount() {
        return getResolved().getDim()[0];
    }

    public int getColumnCount() {
        return getResolved().getDim()[1];
    }

    public String[] getRowNames() {
        return ((RChar)getResolved().getDimnames().getValue()[0]).getValue();
    }

    public String[] getColNames() {
        return ((RChar)getResolved().getDimnames().getValue()[1]).getValue();
    }

    public List<String> getRowNamesAsList() {
        return Arrays.asList(getRowNames());
    }

    public List<String> getColNamesAsList() {
        return Arrays.asList(getColNames());
    }

    public int getRowIndex(String name) {
        return ArrayUtils.indexOf(getRowNames(), name);
    }

    public int getColIndex(String name) {
        return ArrayUtils.indexOf(getColNames(), name);
    }

    protected int getIndex(int i, int j) {
        // biocep orders entries by columns
        return j*getRowCount() + i;
    }


    protected abstract EL_TYPE[][] createArr(int rows, int cols);

    public EL_TYPE[][] getDataAsObjArr() {
        EL_TYPE[][] res = createArr(getRowCount(), getColumnCount());
        for (int i = 0; i < getRowCount(); i++)
            for (int j = 0; j < getColumnCount(); j++) {
                res[i][j] = get(i, j);
            }
        return res;
    }

    public EL_TYPE at(int i, String col) {
        int j = ArrayUtils.indexOf(getColNames(), col);
        return get(i,j);
    }

    public EL_TYPE at(String row, int j) {
        int i = ArrayUtils.indexOf(getRowNames(), row);
        return get(i,j);
    }

    public boolean isNA(int i, int j) {
        return get(i,j).equals(RCallServicesBiocep.NA_RNUMERIC);
    }

    //    public RDataFrameBiocep asRDataFrameW() throws RemoteException {
//        return new RDataFrameBiocep(rs, (RDataFrame) rs.call("as.data.frame", getRObject())) ;
//    }
}
