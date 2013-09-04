package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.af.jhlir.backends.rengine.RCallServices;
import org.af.jhlir.call.RNumeric;
import org.junit.Before;
import org.junit.Test;

public abstract class RNumeric_Test extends RVector_Test {
    private RNumeric rNumW1;
    private RNumeric rNumW2;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        getRServices().assign("rnum1", "c(1.1, 5, NA, 3, 10.1, NA)");
        getRServices().assign("rnum2", "c(1.2, 2, 3)");
        rNumW1 = getRServices().eval("rnum1").asRNumeric();
        rNumW2 = getRServices().eval("rnum2").asRNumeric();
        registerVecs(rNumW1, rNumW2);
    }

    @Test
    public void testAt()  {
        double[] xs = rNumW1.getData();
        assertEquals(rNumW1.getLength(), 6);

        assertEquals(rNumW1.get(0), 1.1);
        assertFalse(rNumW1.isNA(0));

        assertEquals(rNumW1.get(1), 5.0);
        assertFalse(rNumW1.isNA(1));

        assertEquals(rNumW1.get(2), RCallServices.NA_RNUMERIC);
        assertTrue(rNumW1.isNA(2));

        assertEquals(rNumW1.get(3), 3.0);
        assertFalse(rNumW1.isNA(3));

        assertEquals(rNumW1.get(4), 10.1);
        assertFalse(rNumW1.isNA(4));

        assertEquals(rNumW1.get(5), RCallServices.NA_RNUMERIC);
        assertTrue(rNumW1.isNA(5));

    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds() {
    	rNumW1.get(-1);
    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds2() {
    	rNumW1.get(6);
    }

    @Test
    public void testGetDataAsObjArr()  {
        Double[] xs = rNumW1.getDataAsObjArr();
        assertEquals(xs.length, 6);
        assertEquals(xs[0], (Double)1.1);
        assertEquals(xs[1], (Double)5.0);
        assertEquals(xs[2], RCallServices.NA_RNUMERIC);
        assertEquals(xs[3], (Double)3.0);
        assertEquals(xs[4], (Double)10.1);
        assertEquals(xs[5], RCallServices.NA_RNUMERIC);
    }


    @Test
    public void testAsList()  {
        List<Double> xs = rNumW1.getDataAsList();
        assertEquals(xs.size(), 6);
        assertEquals(xs.get(0), (Double)1.1);
        assertEquals(xs.get(1), (Double)5.0);
        assertEquals(xs.get(2), RCallServices.NA_RNUMERIC);
        assertEquals(xs.get(3), (Double)3.0);
        assertEquals(xs.get(4), (Double)10.1);
        assertEquals(xs.get(5), RCallServices.NA_RNUMERIC);
    }

}
