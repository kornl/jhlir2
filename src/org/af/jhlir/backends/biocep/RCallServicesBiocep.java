package org.af.jhlir.backends.biocep;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import org.af.jhlir.call.RCallServices;
import org.af.jhlir.call.RChar;
import org.af.jhlir.call.REngineException;
import org.af.jhlir.call.RErrorException;
import org.af.jhlir.call.RInteger;
import org.af.jhlir.call.RLogical;
import org.af.jhlir.call.RNumeric;
import org.af.jhlir.call.RObj;
import org.af.jhlir.call.RRef;
import org.kchine.r.RMatrix;
import org.kchine.r.RObject;
import org.kchine.r.server.RServices;

public class RCallServicesBiocep extends RCallServices {

    static {
        RCallServices.NA_RINTEGER = Integer.MIN_VALUE;
        RCallServices.NA_RNUMERIC = Double.NaN;
        RCallServices.NA_RLOGICAL = null;
        RCallServices.NA_CHAR = "NA";
        RCallServices.NA_FACTOR = null;
    }

    private RServices rs;
    private List<String> history = new Vector<String>();

    public RCallServicesBiocep(RServices rs) {
        this.rs = rs;
        try {
            rs.evaluate("options(error = function() {" + ERROR_VAR + " <<- geterrmessage()})");
        } catch (RemoteException e) {
            throw new REngineException(e);
        }
    }

    public RServices getRServices() {
        return rs;
    }

    public RObject engineEval(String expression, boolean resolve) {
        try {
            RObject res = null;
            rs.evaluate(ERROR_VAR + " <<- NULL");
            String expression2 = expression.replace("\"", "\\\"");
            String s1 = "parse(text=\"" + expression2 + "\")";
            System.out.println(s1);
            rs.evaluate(s1);
            Object error = rs.getObjectConverted(ERROR_VAR);
            if (error != null) {
                throw new RErrorException(error.toString());
            }
            if (resolve)
                res = rs.getObject(expression);
            else
                res = rs.getReference(expression);
            error = rs.getObjectConverted(ERROR_VAR);
            if (error != null) {
                throw new RErrorException(error.toString());
            }
            return res;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    public void evalVoid(String expression) throws REngineException {
        engineEval(expression, false);
    }

    public RObj eval(String expression) throws REngineException {
        org.kchine.r.RObject robj = engineEval(expression, true);
        return wrapObject(robj);
    }

    public RRef evalAndGetRef(String expression) throws RemoteException {
        org.kchine.r.server.ReferenceInterface ref = (org.kchine.r.server.ReferenceInterface) engineEval(expression, false);
        return wrapObject(ref);
    }

    public void assign(String varName, String expression) throws RemoteException {
        evalVoid(varName + "<-" + expression);
    }
//
//    public void callVoid(String function, Object... args) throws RemoteException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    //

    public RObj call(String function, Object... args) {
        org.kchine.r.RObject robj;
		try {
			robj = rs.call(function, args);
		} catch (RemoteException e) {
			throw new REngineException(e);
		}
        return wrapObject(robj);
    }
//
//    public RRef callAndGetRef(String function, Object... args) throws RemoteException {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void callAndAssign(String varName, String function, Object... args) throws RemoteException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//

    public void put(String varName, Object obj) throws REngineException {
        try {
            if (obj instanceof RObjectBiocep) {
                rs.putAndAssign(((RObjectBiocep) obj).getWrapped(), varName);
            }
        } catch (RemoteException e) {
            throw new REngineException(e);
        }
    }

//
//    public boolean symbolExists(String symbol) throws RemoteException {
//        return false;  //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void freeReference(RObj refObj) throws RemoteException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void freeAllReferences() throws RemoteException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    public RRef wrapObject(org.kchine.r.server.ReferenceInterface ref) {
        if (ref instanceof org.kchine.r.RListRef)
            return new RListRefBiocep(this, (org.kchine.r.RListRef) ref);
        return null;
    }

    public RObjectBiocep wrapObject(org.kchine.r.RObject robj) {
        if (robj == null)
            return null;
//        RObjectBiocep w = new RObjectBiocep(rs, obj);
//        w.rClass = rClass;

        else if (robj instanceof org.kchine.r.RNumeric)
            return new RNumericBiocep(this, (org.kchine.r.RNumeric) robj);
        else if (robj instanceof org.kchine.r.RInteger)
            return new RIntegerBiocep(this, (org.kchine.r.RInteger) robj);
        else if (robj instanceof org.kchine.r.RLogical)
            return new RLogicalBiocep(this, (org.kchine.r.RLogical) robj);
        else if (robj instanceof org.kchine.r.RChar)
            return new RCharBiocep(this, (org.kchine.r.RChar) robj);
        else if (robj instanceof org.kchine.r.RFactor)
            return new RFactorBiocep(this, (org.kchine.r.RFactor) robj);

        else if (robj instanceof RMatrix)
            return new RMatrixDoubleBiocep(this, (org.kchine.r.RMatrix) robj);

        else if (robj instanceof org.kchine.r.RList)
            return new RListBiocep(this, (org.kchine.r.RList) robj);
        else if (robj instanceof org.kchine.r.RDataFrame)
            return new RDataFrameBiocep(this, (org.kchine.r.RDataFrame) robj);

        else if (robj instanceof org.kchine.r.REnvironment)
            return new REnvironmentBiocep(this, (org.kchine.r.REnvironment) robj);

//
//        else if (obj instanceof RDataFrameRef)
//            return w.asDfRefW();
//
//        return w;
        return null;
    }

    @Override
    public String[] getWarning() {
        String[] warning;
        try {
            Object o = rs.getObjectConverted("names(warnings())");
            if (o instanceof String[]) {
                warning = (String[]) o;
            } else {
                warning = new String[1];
                warning[0] = (String) o;
            }
        } catch (Exception e) {
            throw new REngineException(e);
        }
        if (warning != null && warning.length > 0 && warning[0] != null && !warning[0].equals("NO_WARNING"))
            return warning;
        return null;
    }

	@Override
	public RNumeric createRObject(double[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RInteger createRObject(int[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RChar createRObject(String[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RLogical createRObject(boolean[] val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getHistory() {		
		return history;
	}
}
