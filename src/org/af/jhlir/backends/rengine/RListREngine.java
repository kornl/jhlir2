package org.af.jhlir.backends.rengine;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.af.jhlir.call.RList;
import org.af.jhlir.call.RObj;
import org.rosuda.REngine.REXPGenericVector;
import org.rosuda.REngine.REXPReference;


public class RListREngine
        extends RObjectREngine<REXPGenericVector, REXPGenericVector>
        implements RList<REXPGenericVector> {


    protected RListREngine(RCallServices rs, REXPGenericVector wrapped) {
        super(rs, wrapped);
    }

    protected RListREngine(RCallServices rs, REXPReference wrapped) {
        super(rs, wrapped);
    }

    public int getLength() {
        return getResolved().length();
    }

    public RObjectREngine get(int i) {
        if (i < 0 || i >= getLength())
            throw new IndexOutOfBoundsException();
        return rs.wrapObject(getResolved().asList().at(i));
    }

    public RObjectREngine get(String name) {
        return rs.wrapObject(getResolved().asList().at(name));
    }

    public boolean hasNames() {
        return getResolved().asList().names == null;
    }

    public String getName(int i) {
        return getResolved().asList().names.get(i).toString();
    }

    public String[] getNames() {
        return getResolved().asList().keys();
    }

    public List<String> getNamesAsList() {
        return Arrays.asList(getNames());
    }

	public List<RObj> getElements() {
		List<RObj> list = new Vector<RObj>();
		for (int i=0; i<getLength(); i++) {
			list.add(get(i));
		}	
		return list;
	}
}


