package org.af.jhlir.backends.biocep;


import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.af.jhlir.call.RList;
import org.af.jhlir.call.RObj;
import org.apache.commons.lang.ArrayUtils;


public class RListBiocep
        extends RObjectBiocep<org.kchine.r.RList, org.kchine.r.RList>
        implements RList<org.kchine.r.RList> {

    public RListBiocep(RCallServicesBiocep rs, org.kchine.r.RList rList) {
        super(rs, rList);
    }

    public int getLength() {
        // empty list in biocep
        Object[] o = getWrapped().getValue();
        if (o == null)
            return 0;
        return o.length;
    }

    public RObjectBiocep get(int i) {
        // empty list in biocep
        Object[] o = getWrapped().getValue();
        if (o == null)
            throw new IndexOutOfBoundsException();
        return rs.wrapObject(getWrapped().getValue()[i]);
    }

    public RObjectBiocep get(String name) {
        int i = ArrayUtils.indexOf(getNames(), name);
        return i == -1 ? null : get(i);
    }

    public boolean hasNames() {
        return getWrapped().getNames() == null;
    }

    public String getName(int i) {
        return getWrapped().getNames()[i];
    }

    public String[] getNames() {
        return getWrapped().getNames();
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
