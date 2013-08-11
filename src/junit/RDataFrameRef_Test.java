package junit;

import org.af.jhlir.call.RDataFrameRef;
import org.junit.Before;

public class RDataFrameRef_Test extends RDataFrame_Test {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        rDf1 = (RDataFrameRef) getRServices().evalAndGetRef("rdf1");
    }
}
