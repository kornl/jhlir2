package test;

import org.af.jhlir.backends.rengine.RCallServicesREngine;
import org.af.jhlir.call.RNamedArgument;
import org.af.jhlir.call.RObj;
import org.af.jhlir.call.S4Obj;
import org.rosuda.REngine.JRI.JRIEngine;

public class TestRengine {
    public static void main(String[] args) {
    	
        try {
            RCallServicesREngine rs = new RCallServicesREngine(new JRIEngine());
           /* rs.engineEval("rnorm(10)", true);
            RObj o1 = rs.eval("list(a=c(\"a\"),xx=1:3)");
            RObj o2 = rs.eval("5");
        	RObj o3 = rs.eval("c(\"sosos\")");
        	RObj o4 = rs.eval("0.01");
        	RObj result = rs.call("list", new Object[] {new RNamedArgument("list", o1), o2, new RNamedArgument("so", o3)});
            rs.eval("setClass(\"test\", representation(x=\"numeric\", y=\"numeric\"))");
            RObj x = rs.eval("(try(new(\"test\",x=1,y=2)))");*/
            System.out.println(rs.eval("getClass(\"MethodDefinition\")"));        
            

////            Rengine re = new Rengine();
//            REngine re = new JRIEngine();
//            re.parseAndEval("xx <- list(1,2)");
//            re.parseAndEval("names(xx)[1] <- \"foo\"");
//            re.parseAndEval("yy <- list(foo=1,2)");
//            System.out.println(re.parseAndEval("xx").asList().keyAt(1));
//            System.out.println(re.parseAndEval("yy").asList().keyAt(1));
//
//            RTypeFactory rtf = RTypeFactoryREngine.getInstance(null);
//
//
//            REXP r = re.parseAndEval("list()",g, false);
//            System.out.println(r);
////            re.eval("plot(1:3)");

//            REXP g = re.parseAndEval(".GlobalEnv");
//            re.parseAndEval("x <- rnorm(100)");
//            REXP x_ref = re.parseAndEval("x", g, false);
//            REXP r = new REXPLanguage( new RList( new REXP[]{new REXPSymbol("head"), x_ref}));
//            REXP r3 = re.eval(r, g, true );
//            System.out.println(r);
//            System.out.println(r3);
            System.exit(1);
//        System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
