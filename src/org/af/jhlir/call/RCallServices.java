package org.af.jhlir.call;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;


//todo build in java streams?
// todo handlers for warning and errors

abstract public class RCallServices {

    protected static final String WARNING_VAR = ".jhlir.warnings";
    protected static final String ERROR_VAR = ".jhlir.errors";

	/**
	 * Evaluates an expression.
	 * @param expression
	 * @throws REngineException
	 */
     abstract public void evalVoid(String expression) throws REngineException;
     
     /**
      * Evaluates an expression and returns the result.
      * @param expression expression to be evaluated
      * @return the result of evaluating the expression
      * @throws REngineException
      */
     abstract public RObj eval(String expression) throws REngineException;
     
     /**
      * Evaluates an expression and returns the result as a reference.
      * @param expression expression to be evaluated
      * @return a reference to the result of evaluating the expression
      * @throws RemoteException
      */
     abstract public RRef evalAndGetRef(String expression) throws RemoteException;
     
     /**
      * Assigns the result of evaluating the expression to a variable.
      * @param varName variable name that the result should be assigned to
      * @param expression expression to be evaluated
      * @throws RemoteException
      */
     abstract public void assign(String varName, String expression) throws RemoteException;
     
//     public void callVoid(String function, Object... args) throws RemoteException;
     
     /**
      * Calls a function with the given arguments.
      * The arguments can be of Type RObj ... TODO What is allowed to pass here? 
      * @param function name of the function to be called
      * @param args arguments to be part of the call
      * @throws RemoteException
      */
     abstract public RObj call(String function, Object... args);
     
//     public RRef callAndGetRef(String function, Object... args) throws RemoteException;
//     public void callAndAssign(String varName, String function, Object... args) throws RemoteException;
     
     /**
      * TODO
      * @param varName
      * @param obj
      * @throws RemoteException
      */     
     abstract public void put(String varName, Object obj) throws REngineException;
     
//     public boolean symbolExists(String symbol) throws RemoteException;
//     public void freeReference(RObj refObj) throws RemoteException;
//     public void freeAllReferences() throws RemoteException;

     /** Integer value that codes a NA */
    public static Integer NA_RINTEGER;   
    /** Double value that codes a NA */
    public static Double NA_RNUMERIC;
    /** Boolean value that codes a NA */
    public static Boolean NA_RLOGICAL;
    /** String value that codes a NA for a character */
    public static String NA_CHAR;
    /** String value that codes a NA for a factor */
    public static String NA_FACTOR;
    
    /**
     * If there was generated at least one warning on R side by the last {@code eval}, 
     * {@code evalVoid}, {@code evalAndGetRef},{@code assign}, {@code call} 
     * or {@code put} command, this method returns the warnings.
     * Otherwise it returns null. 
     * @return the warnings and null if no warning was generated. 
     */
	abstract public String[] getWarning();
	
	public abstract RNumeric createRObject(double[] val);
	
	public RNumeric createRObject(Double[] val) {
		return createRObject(ArrayUtils.toPrimitive(val));
	}
	
	public RNumeric createRObject(double val) {
		return createRObject(new double[] {val});
	}
	
	public abstract RInteger createRObject(int[] val);
	
	public RInteger createRObject(Integer[] val) {
		return createRObject(ArrayUtils.toPrimitive(val));
	}	
	
	public abstract RChar createRObject(String[] val);
	
	public RChar createRObject(String val) {
		return createRObject(new String[] {val});
	}
	
	public abstract RLogical createRObject(boolean[] val);
	
	public RLogical createRObject(Boolean[] val) {
		return createRObject(ArrayUtils.toPrimitive(val));
	}
	
	public abstract List<String> getHistory();
	
}
