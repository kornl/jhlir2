package junit;

import org.af.jhlir.call.RNumeric;
import org.af.jhlir.call.RNumericRef;
import org.junit.Before;


public abstract class RNumericRef_Test extends RNumeric_Test {
    private RNumeric rNumW1;
    private RNumeric rNumW2;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        rNumW1 = (RNumericRef) getRServices().evalAndGetRef("rnum1");
        rNumW2 = (RNumericRef) getRServices().evalAndGetRef("rnum2");
        registerVecs(rNumW1, rNumW2);
    }

}
