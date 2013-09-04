package org.af.jhlir.backends.rengine;

import org.af.jhlir.call.RNumeric;
import org.af.jhlir.call.RTypeFactory;
import org.apache.commons.lang.ArrayUtils;
import org.rosuda.REngine.REXPDouble;

public class RTypeFactoryREngine extends RTypeFactory{
    private RCallServices rs;

    public RTypeFactoryREngine(RCallServices rs) {
        this.rs = rs;
    }

    public static RTypeFactory getInstance(RCallServices rs) {
        return new RTypeFactoryREngine(rs);
    }

    public RNumeric createRNumeric(double[] data) {
        return new RNumericREngine(rs, new REXPDouble(data));
    }

    public RNumeric createRNumeric(Double[] data) {
        return new RNumericREngine(rs, new REXPDouble(ArrayUtils.toPrimitive(data)));
    }
}
