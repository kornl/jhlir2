package org.af.jhlir.backends.rengine;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.af.jhlir.call.RChar;
import org.af.jhlir.call.REngineException;
import org.af.jhlir.call.RErrorException;
import org.af.jhlir.call.RInteger;
import org.af.jhlir.call.RLogical;
import org.af.jhlir.call.RNamedArgument;
import org.af.jhlir.call.RNumeric;
import org.af.jhlir.call.RObj;
import org.af.jhlir.call.RRef;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPInteger;
import org.rosuda.REngine.REXPLogical;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REXPNull;
import org.rosuda.REngine.REXPReference;
import org.rosuda.REngine.REXPString;
import org.rosuda.REngine.REngine;

//todo build in java streams?
//todo handlers for warning and errors


public class RCallServices {

	protected static final String WARNING_VAR = ".jhlir.warnings";
	protected static final String ERROR_VAR = ".jhlir.errors";

	/** Integer value that codes a NA */
	public static Integer NA_RINTEGER = Integer.MIN_VALUE;   
	/** Double value that codes a NA */
	public static Double NA_RNUMERIC = Double.NaN;
	/** Boolean value that codes a NA */
	public static Boolean NA_RLOGICAL = null;
	/** String value that codes a NA for a character */
	public static String NA_CHAR = "NA";
	/** String value that codes a NA for a factor */
	public static String NA_FACTOR = null;

	private REngine rs;
	private REXP env;
	private List<String> history = new Vector<String>();

    public RCallServices(REngine rs) {
    	this(rs, ".GlobalEnv");
    }
    
    public RCallServices(REngine rs, String envir) {
        this.rs = rs;
        try {
            env = rs.parseAndEval(envir);
        } catch (org.rosuda.REngine.REngineException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (REXPMismatchException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        try {
            rs.parseAndEval("options(error = function() { assign(\"" + ERROR_VAR + "\", geterrmessage()})", env, true);
        } catch (org.rosuda.REngine.REngineException e) {
            throw new REngineException(e);
        } catch (REXPMismatchException e) {
            throw new REngineException(e);
        }
    }

    public REngine getREngine() {
        return rs;
    }

    /**
	 * Evaluates an expression.
	 * @param expression
	 * @throws REngineException
	 */
    public void evalVoid(String expression) throws REngineException {    	
        engineEval(expression, true);
    }

	
    /**
     * Evaluates an expression and returns the result.
     * @param expression expression to be evaluated
     * @return the result of evaluating the expression
     * @throws REngineException
     */
    public RObj eval(String expression) throws REngineException {
        REXP robj = engineEval(expression, true);
        return wrapObject(robj);
    }

    /**
     * Evaluates an expression and returns the result as a reference.
     * @param expression expression to be evaluated
     * @return a reference to the result of evaluating the expression
     * @throws RemoteException
     */
    public RRef evalAndGetRef(String expression) throws REngineException {
        REXPReference rr = (REXPReference) engineEval(expression, false);
        return wrapObject(rr);
    }

    public REXP engineEval(String expression, boolean resolve) {
    	history.add(expression);
        try {
            rs.parseAndEval(ERROR_VAR + " <<- NULL", env, true);
            //String expression2 = expression.replace("\"", "\\\"");
            //String s1 = "parse(text=\"" + expression2 + "\")";
            //System.out.println(s1);
            /*REXP rexp = rs.parseAndEval(expression);
            REXP errRexp = rs.parseAndEval(ERROR_VAR);
            if (!(errRexp instanceof REXPNull)) {
                String error = errRexp.asString();
                throw new RErrorException(error);
            }*/
            REXP res = rs.parseAndEval(expression, env, resolve);
            REXP errRexp = rs.parseAndEval(ERROR_VAR, env, true);
            if (!(errRexp instanceof REXPNull)) {
                String error = errRexp.asString();
                throw new RErrorException(error);
            }
            return res;
        } catch (org.rosuda.REngine.REngineException e) {
        	throw new RuntimeException(e);
        } catch (REXPMismatchException e) {
            // should not happen
            throw new RuntimeException(e);
        }
    }

    /**
     * Assigns the result of evaluating the expression to a variable.
     * @param varName variable name that the result should be assigned to
     * @param expression expression to be evaluated
     * @throws RemoteException
     */
    public void assign(String varName, String expression) throws RemoteException {
        evalVoid(varName + "<-" + expression);
    }

    //
//
//    public void callVoid(String function, Object... args) throws RemoteException {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

    //

    /**
     * Calls a function with the given arguments.
     * The arguments can be of Type RObj ... TODO What is allowed to pass here? 
     * @param function name of the function to be called
     * @param args arguments to be part of the call
     * @throws RemoteException
     */
    public RObj call(String function, Object... args) {
    	String argStr = "";
        for (Object o : args) {
        	argStr += getString(o)+",";
        }
        if (argStr.length()>1) argStr = argStr.substring(0, argStr.length()-1);        
        return eval(function+"("+argStr+")");
    }
    
    public String getString(Object o) {    
    	if (o instanceof RObjectREngine) {
    		String tmpname = ".JHLIRtmp"+Math.abs((new Random()).nextInt());
    		put(tmpname, o);
    		String result = eval("paste(capture.output(dput("+tmpname+")), collapse=\"\")").asRChar().getData()[0];
    		return tmpname;
    		//eval("rm("+tmpname+")");
    		//return result;
    	} else if (o instanceof RNamedArgument) {
    		return ((RNamedArgument) o).getName()+"="+getString(((RNamedArgument) o).getRobj());
    	} else if (o instanceof String) {
    		return "\""+(String)o+"\"";
    	} else if (o instanceof List) {    		
    		if (((List)o).get(0) instanceof String) {
    			List l = (List<String>) o;
    			String result = "c(\""+l.get(0)+"\"";
    			for (int i=1; i < l.size(); i++) {
    				result += ", \""+l.get(i)+"\"";
    			}
    			return result+")";
    		}
    	}
		return null;
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
    	history.add("# Assigning "+obj.toString()+" to "+varName);
        try {
            if (obj instanceof RObjectREngine) {
                rs.assign(varName, ((RObjectREngine) obj).getWrapped());
            }
        } catch (Exception e) {
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


    public RRef wrapObject(REXPReference ref) {
        REXP robj = ref.resolve();
        if (robj instanceof org.rosuda.REngine.REXPGenericVector) {
            if (robj.hasAttribute("class") && ((REXPString) robj.getAttribute("class")).asStrings()[0].equals("data.frame"))
                return new RDataFrameRefREngine(this, ref);
//            if (robj.hasAttribute("class")) // should we better call is.object here?
//                return new S3ObjREngine(this, (org.rosuda.REngine.REXPGenericVector) robj);
//            // default
            return new RListRefREngine(this, ref);
        }
        return null;
    }


    public RObjectREngine wrapObject(org.rosuda.REngine.REXP robj) {
    	if (robj == null) {
    		return null;
    		//        RObjectBiocep w = new RObjectBiocep(rs, obj);
    		//        w.rClass = rClass;
    		//        if (obj instanceof RInteger)
    		//            return w.asIntW();
    	} else if (robj instanceof org.rosuda.REngine.REXPDouble) {
    		try {
    			if (robj.hasAttribute("dim") && robj.getAttribute("dim").asIntegers().length == 2)
    				return new RMatrixDoubleREngine(this, (org.rosuda.REngine.REXPDouble) robj);
    		} catch (REXPMismatchException e) {
    		}
    		// default
    		return new RNumericREngine(this, (org.rosuda.REngine.REXPDouble) robj);
    	} else if (robj instanceof org.rosuda.REngine.REXPFactor) {
    		return new RFactorREngine(this, (org.rosuda.REngine.REXPFactor) robj);
    		// factor inherist from integer in REngine
    	} else if (robj instanceof org.rosuda.REngine.REXPInteger && !(robj instanceof org.rosuda.REngine.REXPFactor)) {
    		return new RIntegerREngine(this, (org.rosuda.REngine.REXPInteger) robj);
    	} else if (robj instanceof org.rosuda.REngine.REXPLogical) {
    		return new RLogicalREngine(this, (org.rosuda.REngine.REXPLogical) robj);

    	} else if (robj instanceof org.rosuda.REngine.REXPString) {
    		return new RCharREngine(this, (org.rosuda.REngine.REXPString) robj);

    		//        else if (obj instanceof RFactor)
    		//            return w.asFactorW();
    		//
    	} else if (robj instanceof org.rosuda.REngine.REXPGenericVector) {
    		if (robj.hasAttribute("class") && ((REXPString) robj.getAttribute("class")).asStrings()[0].equals("data.frame")) {
    			return new RDataFrameREngine(this, (org.rosuda.REngine.REXPGenericVector) robj);
    		}
    		if (robj.hasAttribute("class")) { // should we better call is.object here? 
    			return new S3ObjREngine(this, (org.rosuda.REngine.REXPGenericVector) robj);
    		}    		
    		// default
    		return new RListREngine(this, (org.rosuda.REngine.REXPGenericVector) robj);
    	} else if (robj instanceof org.rosuda.REngine.REXPEnvironment) {
    		return new REnvironmentREngine(this, (org.rosuda.REngine.REXPEnvironment) robj);
    	} else if (false /* TODO: How do we test for S4-Objects? */) {
			return new S4ObjREngine(this, robj);
		}
    	return null;
    }

    /**
     * If there was generated at least one warning on R side by the last {@code eval}, 
     * {@code evalVoid}, {@code evalAndGetRef},{@code assign}, {@code call} 
     * or {@code put} command, this method returns the warnings.
     * Otherwise it returns null. 
     * @return the warnings and null if no warning was generated. 
     */
    public String[] getWarning() {
        String[] warning;
        try {
            // dont know what happens here, check this. if i dont call this twice i get null.
            rs.parseAndEval("last.warning");
            warning = rs.parseAndEval("last.warning").asList().keys();
        } catch (Exception e) {
            throw new REngineException(e);
        }
        if (warning.length > 0 && !warning[0].equals("NO_WARNING")) return warning;
        return null;
    }

	public RNumeric createRObject(double[] val) {		
		return new RNumericREngine(this, new REXPDouble(val));
	}

	public RInteger createRObject(int[] val) {		
		return new RIntegerREngine(this, new REXPInteger(val));
	}

	public RChar createRObject(String[] val) {
		return new RCharREngine(this, new REXPString(val));
	}

	public RLogical createRObject(boolean[] val) {
		return new RLogicalREngine(this, new REXPLogical(val));
	}

	public List<String> getHistory() {		
		return history;
	}


}
