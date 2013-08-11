package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RMatrixDouble;
import org.apache.commons.lang.ArrayUtils;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPMismatchException;

public class RMatrixDoubleREngine
        extends RMatrixREngine<REXPDouble, REXPDouble, double[][], Double>
        implements RMatrixDouble<REXPDouble> {

    public RMatrixDoubleREngine(RCallServicesREngine rs, REXPDouble wrapped) {
        super(rs, wrapped);
    }

    public double[][] getData() {
        try {
            return getWrapped().asDoubleMatrix();
        } catch (REXPMismatchException e) {}
        return null;
    }

    protected Double[][] createArr(int rows, int cols) {
        return new Double[rows][cols];
    }

    /**
     * Get element in row i and column j.
     */
    public Double get(int i, int j) {
        return getResolved().asDoubles()[getIndex(i,j)];
    }

    public boolean isNA(int i, int j) {
        return REXPDouble.isNA(get(i,j));
    }

	public Double[] getCol(int j) {
		Double[] row = new Double[ncol()];
		for (int nr=0; nr<ncol();nr++) {
			row[nr] = get(nr, j);
		}
		return row;
	}

	public Double[] getRow(int i) {
		return ArrayUtils.toObject(getData()[i]);
	}

	public int ncol() {		
		return getData()[0].length;
	}

	public int nrow() {
		return getData().length;
	}
}
