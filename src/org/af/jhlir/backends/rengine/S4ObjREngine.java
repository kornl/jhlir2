package org.af.jhlir.backends.rengine;

import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.RObj;
import org.af.jhlir.call.S4Obj;
import org.rosuda.REngine.REXP;

public class S4ObjREngine extends RObjectREngine<REXP, REXP> implements S4Obj {

    public S4ObjREngine(RCallServicesREngine rs, REXP wrapped) {
    	super(rs, wrapped);    	
    }

    public String[] getS4Classes() {        
    	return rs.call("class", getWrapped()).asRChar().getData();        
    }

    public List<String> getS4ClassesAsList() {
        return Arrays.asList(getS4Classes());
    }

    public String getS4Class() {
        return getS4Classes()[0];
    }

	public RObj getSlot(String slot) {
		// TODO Auto-generated method stub
		return null;
	}
}
