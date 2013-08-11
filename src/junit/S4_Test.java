package junit;

import static org.junit.Assert.assertEquals;

import org.af.jhlir.call.S4Obj;
import org.junit.Before;
import org.junit.Test;

public class S4_Test extends MyTestCase {
    private S4Obj s4Obj;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        getRServices().eval("setClass(\"test\", representation(x=\"numeric\", y=\"numeric\"))");
        s4Obj = getRServices().eval("new(\"test\",x=1,y=2)").asS4Obj();        
    }

    @Test
    public void testAt()  {
        assertEquals(s4Obj.getS4Class(), "test");
    }

}
