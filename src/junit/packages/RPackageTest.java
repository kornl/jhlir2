package junit.packages;


/*

public class RPackageTest extends TestCase {

    @Test
    public void testCompareVersion()  {
        RPackage rp1 = new RPackage("pairwiseCI");
        RPackage rp2 = new RPackage("pairwiseCI", "0.1");
        RPackage rp3 = new RPackage("pairwiseCI", "0.1.5");
        try {
            new RPackage("pairwiseCI", "0.1-5");
            fail();
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().startsWith("RPackage version string has wrong format: 0.1-5"));
        }

        RPackage rp4 = new RPackage("pairwiseCI", "0.2.2.3");

        assertEquals(rp1.getName(), "pairwiseCI");
        assertEquals(rp2.getName(), "pairwiseCI");

        assertEquals(rp1.getVersion(), "");
        assertEquals(rp2.getVersion(), "0.1");
        assertEquals(rp3.getVersion(), "0.1.5");
        assertEquals(rp4.getVersion(), "0.2.2.3");

        assertTrue(rp2.isNewerOrSameVersionOf("0.1"));
        assertTrue(rp3.isNewerOrSameVersionOf("0.1"));
        assertTrue(rp4.isNewerOrSameVersionOf("0.1"));

        assertTrue(rp2.isNewerOrSameVersionOf(rp1));
        assertTrue(rp2.isNewerOrSameVersionOf(rp2));

        assertTrue(rp3.isNewerOrSameVersionOf(rp1));
        assertTrue(rp3.isNewerOrSameVersionOf(rp2));
        assertTrue(rp3.isNewerOrSameVersionOf(rp3));

        assertTrue(rp4.isNewerOrSameVersionOf(rp1));
        assertTrue(rp4.isNewerOrSameVersionOf(rp2));
        assertTrue(rp4.isNewerOrSameVersionOf(rp3));
        assertTrue(rp4.isNewerOrSameVersionOf(rp4));
    }


    @Test
    public void testNaList()  {
        // does not work?
        // return boolean array???
//        assertEquals(1, ((Double)naList.at(2).asDouble()).intValue());
//
//        assertEquals(1, ((Double)naList.at(4).asDouble()).intValue());
    }

}

*/