package junit;

import org.af.jhlir.call.RObj;
import org.junit.Before;
import org.junit.Test;

public class RObj_Test extends MyTestCase {
    private RObj rObj1;
    private RObj rObj2;


    @Before
	public void setUp() throws Exception {
        super.setUp();
        rObj1 = getRServices().eval("as.character(c(\"b\", NA,\"b\", \"a\"))");
        rObj2 = getRServices().eval("list()");
    }

    @Test
    public void test()  {

    }

    @Test(expected=ClassCastException.class) 
    public void wrongType() {
    	rObj1.asRInteger();
    }

}
