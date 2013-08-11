package test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.af.jhlir.packages.RPackage;
import org.af.jhlir.tools.RCmdBatch;

public class TestRCmdBatch {
    public static void main(String[] args) {
        try {
            RCmdBatch rcb = new RCmdBatch("c:\\programme\\r\\r-2.9.1");
            List<String> is;
            List<String> os;
            Map<String, String> m;
            String[] os2;

            rcb.retrieveRInfo();
            System.out.println("R version: " + rcb.getRVersion());
            System.out.println("R Lib Paths: " + rcb.getLibPaths());

            is = Arrays.asList("sessionInfo()");
            os = rcb.exec(is);
            for (int i=0; i<os.size(); i++) {
                System.out.println(i + ": " + os.get(i));
            }

            is = Arrays.asList("x <- 1+7", "y <- Sys.Date()");
            m = rcb.retrieveInfo(is, Arrays.asList("x", "y"));
            System.out.println("x: " + m.get("x"));
            System.out.println("y: " + m.get("y"));

            System.out.println("\n\n\n");
            RPackage rp = rcb.getInstalledPackInfo("kernlab");
            System.out.println(rp);

            System.out.println("\n\n\n");
            rp = rcb.getInstalledPackInfo("xxx");
            System.out.println("Package not found, return value is null: " + rp);


            System.out.println("\n\n\n");
            List<String> rps = rcb.getInstalledPackages();
            System.out.println("installed packages:");
            System.out.println(rps);

            System.out.println("\n\n\n");
            try {
                rcb.installCranPackage("bbbasdas");
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\n\n\n");
            try {
                rp = rcb.installCranPackage("randomForest");
                System.out.println("package was installed: " + rp);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
