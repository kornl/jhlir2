package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.af.jhlir.call.RErrorException;
import org.af.jhlir.call.RObj;
import org.junit.Before;
import org.junit.Test;

public class REngineServices_Test extends MyTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @Test
    public void testWarnings() {
    	getRServices().evalVoid("warning(\"Warning!\")");
    	String[] warning = getRServices().getWarning();
    	assertEquals("Warning!", warning[0]);
    }
    
    @Test
    public void testErrors() {
    	String error = "";
    	try {
    		getRServices().evalVoid("stop(\"error\")");
    	} catch(RErrorException re) {
    		error = re.getMessage();        		
    	}    	
    	assertTrue(error.endsWith("error\n"));
    }

    @Test(expected=RErrorException.class)
    public void testMissingBracket1() {        
        getRServices().evalVoid("rnorm(100");
        /* missing bracket ) */
    }

    @Test(expected= RErrorException.class)
    public void testMissingBracket2() {        
        getRServices().evalVoid("{rnorm(100)");
        /* missing bracket } */
    }
    
    @Test(expected=RErrorException.class)
    public void testUnexpected() {        
        getRServices().evalVoid("()");
        /* Fehler: Unerwartetes ')' in "()" */
    }    

    @Test(expected=RErrorException.class)
    public void testUnknownObject() {        
        getRServices().evalVoid("unknown()");
        /* Fehler: konnte Funktion "unknown" nicht finden */
    }
    
    @Test(expected=RErrorException.class)
    public void testunusedArguments() {        
        getRServices().evalVoid("rnorm(x=100)");
        /* Fehler in rnorm(x = 100) : unbenutzte(s) Argument(e) (x = 100) */
    }
    
    @Test(expected=RErrorException.class)
    public void testStop() {        
        getRServices().evalVoid("stop(\"error\")");        
    }
    
    @Test
    public void testCall() {        
    	RObj n = getRServices().eval("5");
    	RObj mean = getRServices().eval("11");
    	RObj sd = getRServices().eval("0.01");
    	RObj result = null;
    	System.out.println(n);
    	System.out.println(mean);
    	System.out.println(sd);
    	result = getRServices().call("list", n, mean, sd);
    	//System.out.println(result);
    }
    
    
}
