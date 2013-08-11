package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.af.jhlir.call.RCallServices;
import org.af.jhlir.call.RVector;
import org.junit.Test;

public class RVector_Test extends MyTestCase {
    private RVector rUnnamedVec;
    private RVector rNamedVec;

    protected void registerVecs(RVector rUnnamedVec, RVector vec3Els) {
        this.rUnnamedVec = rUnnamedVec;
        getRServices().put("vec.named", vec3Els);
        getRServices().evalVoid("names(vec.named) <- c(\"xa\", \"xb\", \"NA\")");
        this.rNamedVec = (RVector) getRServices().eval("vec.named");
    }

    @Test
    public void testNames() {
        assertNull(rUnnamedVec.getNames());
        assertNull(rUnnamedVec.getNamesAsList());

        assertEquals(rNamedVec.getName(0), "xa");
        assertEquals(rNamedVec.getName(1), "xb");
        assertEquals(rNamedVec.getName(2), RCallServices.NA_CHAR);

        String[] ns1 = rNamedVec.getNames();
        assertEquals(ns1.length, 3);
        assertEquals(ns1[0], "xa");
        assertEquals(ns1[1], "xb");
        assertEquals(ns1[2], RCallServices.NA_CHAR);

        List<String> ns2 = rNamedVec.getNamesAsList();
        assertEquals(ns2.size(), 3);
        assertEquals(ns2.get(0), "xa");
        assertEquals(ns2.get(1), "xb");
        assertEquals(ns2.get(2), RCallServices.NA_CHAR);
    }
}
