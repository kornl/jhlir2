package org.af.jhlir.backends.biocep;


import java.util.Arrays;
import java.util.List;

import org.af.jhlir.call.RCallServices;
import org.kchine.r.RFactor;


public class RFactorBiocep
        extends RVectorFactorBiocep<org.kchine.r.RFactor, org.kchine.r.RFactor, String[], String>
        implements org.af.jhlir.call.RFactor<RFactor> {

    public RFactorBiocep(RCallServicesBiocep rs, org.kchine.r.RFactor wrapped) {
        super(rs, wrapped);
    }

    public int getLength() {
        return getResolved().getCode().length;
    }

    public String[] getData() {
        String[] data = new String[getLength()];
        int[] code = getCodes();
        String[] levels = getLevels();
        for (int i = 0; i < getLength(); i++) {
            if (code[i] < 0)
                data[i] = null;
            else
                data[i] = levels[code[i] - 1];
        }
        return data;
    }

    public String[] getDataAsObjArr() {
        return getData();
    }

    public String[] getLevels() {
        return getResolved().getLevels();
    }

    public List<String> getLevelsAsList() {
        return Arrays.asList(getLevels());
    }

    public int[] getCodes() {
        return getResolved().getCode();
    }

    public int getCode(int i) {
        return getCodes()[i];
    }

    public String get(int i) {
        int code = getResolved().getCode()[i];
        return code < 0 ? RCallServices.NA_FACTOR : getResolved().getLevels()[code - 1];
    }

    //todo how are factor names handled in biocep?
    public String[] getNames() {
        return null;
    }

    public boolean isNA(int i) {
        return getResolved().getCode()[i] < 0;
    }

    //    protected void _set(int i, String val) {
//        int[] codes = getObj().getCode();
//        int j = getLevels().indexOf(val);
//        if (j == -1)
//            throw new RuntimeException("Tried to set RFactorBiocep to nonex. level:" + val);
//        codes[i] = j+1;
//        getObj().setCode(codes);
//    }
//
//
//    protected void setNonNA(int i) {}

//    public void setNA(int i) {
//        int[] codes = getObj().getCode();
//        codes[i] = -1;
//        getObj().setCode(codes);
//    }
//
//
//    public void setLevels(Set<String> levels) {
//        getObj().setLevels(levels.toArray(new String[0]));
//    }
//
//    public void add(int i, String v) throws RemoteException{
//        RObject ro = rs.call("factor", rs.call("append", rs.call("as.character", getObj()), v, i));
//        setRObj((RFactor)ro);
//    }
//
//
//    public boolean canBeInterpretedAsInt() {
//        try {
//            for (String x:getObj().asData()) {
//                Integer.parseInt(x);
//            }
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    public boolean canBeInterpretedAsNum() {
//        try {
//            for (String x:getObj().asData()) {
//                Double.parseDouble(x);
//            }
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    // we change the standard conversion to integer and numeric
//    @Override
//    public <E extends RObject> E convert(Class<E> type) throws RemoteException {
//        if (type == RInteger.class || type == RNumeric.class) {
//            RCharBiocep c = new RCharBiocep(rs, super.convert(RChar.class));
//            return (E)c.convert(type);
//        }
//        return super.convert(type);
//    }
}
