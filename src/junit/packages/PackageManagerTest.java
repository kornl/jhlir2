package junit.packages;

/*

import java.io.File;
import java.rmi.RemoteException;

import org.junit.BeforeClass;
import org.junit.Test;


public class PackageManagerTest extends SysTestCase {

    private RControlLocal rctrl;
    private PackageManagerRControl pmRctrl;
    private File libDir = af.statguitoolkit.junittests.sys_dep.Setup.libDir;

    @BeforeClass
    public void setUp() {
        super.setUp();

        if (libDir.exists())
            DirectoryManager.deleteDirectory(libDir);
        libDir.mkdir();
        try {
			rctrl = new RControlLocal(libDir.getAbsolutePath());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        pmRctrl = new PackageManagerRControl(libDir, rctrl);
        System.out.println("PackageManagerTest setup done.");
    }

    @Test
    public void testInstallPackages() throws Exception {
        // rJava is not installed
        pmRctrl.readInstalledPackages();
        RPackage p = pmRctrl.getPackage("klaR");
        assertNull(p);
        assertFalse(pmRctrl.isInstalled("klaR"));

        InstallResult ir;

        // try to install a nonexistent package
        ir = pmRctrl.installPackage("klaR123", "0");
        assertTrue(ir.wasNotFound());
        assertTrue(ir.getMessage().startsWith("Can't find klaR123 on CRAN"));

        // try to install a nonexistent version
        ir = pmRctrl.installPackage("klaR", "999");
        assertTrue(ir.wasNotFound());
        assertTrue(ir.getMessage().startsWith("Can't find version 999 of klaR on CRAN"));

        // try to install a nonexistent version 2
         ir = pmRctrl.installIfNecessary("klaR", "999");
        assertTrue(ir.wasNotFound());
        assertTrue(ir.getMessage().startsWith("Can't find version 999 of klaR on CRAN"));


        pmRctrl.readInstalledPackages();
        p = pmRctrl.getPackage("klar", "0.5");
        assertNull(p);
        assertFalse(pmRctrl.isInstalled("klaR"));

        // install a correct version
        pmRctrl.installPackage("klaR", "0.5");

        pmRctrl.readInstalledPackages();
        p = pmRctrl.getPackage("klaR");
        assertNotNull(p);
        assertTrue(pmRctrl.isInstalled("klaR"));
        assertTrue(p.isNewerOrSameVersionOf("0.5"));
        assertTrue(pmRctrl.isInstalled("klaR", "0.5"));

        // test installIfNecessary for ex. packagage

        // nothing happens
        InstallResult res =  pmRctrl.installIfNecessary("klaR", "0.4");
        assertTrue(res.isAlreadyThere());

        // normal install
        res =  pmRctrl.installIfNecessary("pairwiseCI", "0.1");
        assertTrue(res.wasInstalled());

        // require  bunch of packs
//        List<RPackage> rpList = Arrays.asList(
//                new RPackage("pairwiseCI"),
//                new RPackage("kernlab"),
//                new RPackage("MASS"),
//                new RPackage("klaR")
//        );
//
//        pmRctrl.installRequiredPackages(rpList, null);
//        pmRctrl.readInstalledPackages();
//
//        assertTrue(pmRctrl.isInstalled("pairwiseCI"));
//        assertTrue(pmRctrl.isInstalled("kernlab"));
//        assertTrue(pmRctrl.isInstalled("MASS"));
//        assertTrue(pmRctrl.isInstalled("klaR"));


    }

}
*/