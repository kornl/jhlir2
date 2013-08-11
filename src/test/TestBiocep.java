package test;

import org.kchine.r.RList;
import org.kchine.r.server.DirectJNI;
import org.kchine.r.server.RServices;

public class TestBiocep {
  public static void main(String[] args) {
        try {
            RServices re = DirectJNI.getInstance().getRServices();

//            re.evaluate("library(rpart)");
//            re.evaluate("library(kernlab)");

//            RObject r = re.getObject("rpart(Species~., data=iris)");
//            RObject r = re.getObject("ksvm(Species~., data=iris)");
//            re.evaluate("xx <- as.character(c(\"a\", \"b\", N");
//            re.evaluate("names(xx)[1] <- \"blubb\"");
            RList r = (RList) re.getObject("list()");

            System.out.println(r);
            System.out.println(r.getValue());

//            byte[] xs = rexp.asBytes();
//            for (int i=0; i<xs.length; i++) System.out.println(xs[i]);
//
//            boolean[] ys = rexp.isNA();
//            for (int i=0; i<ys.length; i++) System.out.println(ys[i]);
//
//            System.out.println(REXPLogical.isNA(xs[2]));
            System.exit(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}