package org.af.jhlir.call;

abstract public class RTypeFactory {
    public abstract RNumeric createRNumeric(double[] data);
    public abstract RNumeric createRNumeric(Double[] data);

}
