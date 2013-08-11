package junit;

import static org.junit.Assert.assertEquals;

import org.af.jhlir.call.S3Obj;
import org.junit.Before;
import org.junit.Test;

public class S3_Test extends MyTestCase {
    private S3Obj s3Obj;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        getRServices().evalVoid("library(rpart)");
        s3Obj = getRServices().eval("rpart(Species~., data=iris)").asS3Obj();
    }

    @Test
    public void testAt()  {
        assertEquals(s3Obj.getS3Class(), "rpart");
    }

}
