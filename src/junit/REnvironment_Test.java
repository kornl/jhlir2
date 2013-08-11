package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.af.jhlir.call.REnvironment;
import org.af.jhlir.call.RInteger;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

public class REnvironment_Test extends MyTestCase{
    private REnvironment rEnv1;
    private REnvironment rEnv2;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        getRServices().assign("ee", "new.env()");
        getRServices().evalVoid("ee$xx <- 1:3");
        getRServices().evalVoid("assign(\"aa\", 3:5, ee)");
        rEnv1 = getRServices().eval("ee").asREnvironment();
//        rEnv2 = getRServices().eval(".GlobalEnv").asREnvironment();

    }

    @Test
    public void testAt()  {
        String[] ns = rEnv1.getNames();
        assertEquals(ns.length, 2);
        assertTrue(ArrayUtils.contains(ns, "xx"));
        assertTrue(ArrayUtils.contains(ns, "aa"));

        List<String> ns2 = rEnv1.getNamesAsList();
        assertEquals(ns2.size(), 2);
        assertTrue(ns2.contains("xx"));
        assertTrue(ns2.contains("aa"));

        assertTrue(rEnv1.get("xx") instanceof RInteger);
        assertTrue(rEnv1.get("aa") instanceof RInteger);
    }
    @Test
    public void testGlobalEnv()  {
        //todo Global env dont work in biocep
//        String[] ns = rEnv2.getNames();
//        assertEquals(ns.length, 1);
    }

}
