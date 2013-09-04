package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.af.jhlir.backends.rengine.RCallServices;
import org.af.jhlir.call.RLogical;
import org.junit.Before;
import org.junit.Test;

public class RLogical_Test extends RVector_Test{
    private RLogical rLog1;
    private RLogical rLog2;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        rLog1 = getRServices().eval("as.logical(c(T, F, NA, TRUE))").asRLogical();
        rLog2 = getRServices().eval("as.logical(c(T, F, NA))").asRLogical();
        registerVecs(rLog1, rLog2);
    }

    @Test
    public void testAt()  {

        assertEquals(rLog1.getLength(), 4);

        assertEquals(rLog1.get(0), true);
        assertEquals(rLog1.get(1), false);
        assertEquals((Boolean) rLog1.get(2), (Boolean) RCallServices.NA_RLOGICAL);

        assertEquals(rLog1.get(3), true);

        assertFalse(rLog1.isNA(0));
        assertFalse(rLog1.isNA(1));
        assertTrue( rLog1.isNA(2));
        assertFalse(rLog1.isNA(3));

    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds() {
    	rLog1.get(-1);
    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds2() {
    	rLog1.get(6);
    }

    @Test
    public void testGetData()  {
        boolean[] xs = rLog1.getData();
        assertEquals(rLog1.getLength(), 4);

        assertEquals(xs[0], true);
        assertEquals(xs[1], false);
        // we cannot get NAs here, boolean is a bad type
//        assertEquals(xs[2], true);
        assertEquals(xs[3], true);
    }

    @Test
    public void testGetDataAsObjArr()  {
        Boolean[] xs = rLog1.getDataAsObjArr();
        assertEquals(xs.length, 4);
        assertEquals((Boolean)xs[0], (Boolean)true);
        assertEquals((Boolean)xs[1], (Boolean)false);
        assertEquals((Boolean)xs[2], (Boolean) RCallServices.NA_RLOGICAL);
        assertEquals((Boolean)xs[3], (Boolean) true);
    }


    @Test
    public void testAsList()  {
        List<Double> xs = rLog1.getDataAsList();
        assertEquals(xs.size(), 4);
        assertEquals(xs.get(0), true);
        assertEquals(xs.get(1), false);
        assertEquals(xs.get(2), RCallServices.NA_RLOGICAL);
        assertEquals(xs.get(3), true);
    }


}
