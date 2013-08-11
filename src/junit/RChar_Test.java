package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.af.jhlir.call.RCallServices;
import org.af.jhlir.call.RChar;
import org.junit.Before;
import org.junit.Test;

public class RChar_Test extends RVector_Test{
    private RChar rChar1;
    private RChar rChar2;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        rChar1 = getRServices().eval("as.character(c(\"b\", NA,\"b\", \"a\"))").asRChar();
        rChar2 = getRServices().eval("as.character(c(\"b\", NA, \"a\"))").asRChar();
        registerVecs(rChar1, rChar2);
    }

    @Test
    public void testAt()  {
        assertEquals(rChar1.getLength(), 4);

        assertEquals(rChar1.get(0), "b");
        assertFalse(rChar1.isNA(0));

        assertEquals(rChar1.get(1), RCallServices.NA_CHAR);
        assertTrue(rChar1.isNA(1));

        assertEquals(rChar1.get(2), "b");
        assertFalse(rChar1.isNA(2));

        assertEquals(rChar1.get(3), "a");
        assertFalse(rChar1.isNA(3));
    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds() {
    	rChar1.get(-1);
    }

    @Test(expected=IndexOutOfBoundsException.class) public void outOfBounds2() {
    	rChar1.get(4);
    }

    @Test
    public void testGetDataAsObjArr()  {
        String[] xs = rChar1.getDataAsObjArr();
        assertEquals(xs.length, 4);
        assertEquals(xs[0], "b");
        assertEquals(xs[1], RCallServices.NA_CHAR);
        assertEquals(xs[2], "b");
        assertEquals(xs[3], "a");
    }


    @Test
    public void testAsList()  {
        List<String> xs = rChar1.getDataAsList();
        assertEquals(xs.size(), 4);
        assertEquals(xs.get(0), "b");
        assertEquals(xs.get(1), RCallServices.NA_CHAR);
        assertEquals(xs.get(2), "b");
        assertEquals(xs.get(3), "a");
    }


}
