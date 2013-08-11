//package af.statguitoolkit.rpackages;
//
//
//import java.io.File;
//import java.rmi.RemoteException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.kchine.r.RDataFrameRef;
//
//import af.statguitoolkit.rinterface.RDataFrameRefW;
//import af.statguitoolkit.rinterface.RControl;
//import af.statguitoolkit.rinterface.RListW;
//import af.statguitoolkit.rinterface.RCharW;
//import af.statguitoolkit.rinterface.exceptions.PackageNotFoundException;
//import af.statguitoolkit.config.BiocepConfig;
//
///**
// * This class handles all tasks concerning the required installation
// * of R packages for a specific application.
// * Rcontrol is used for the installation.
// * (rJava cannot be installed this way, it is required for RControl)
// */
//
////TODO check for rjava
//public class PackageManagerRControl extends PackageManager {
//    // private static Log logger = LogFactory.getLog(PackageManagerRControl.class);
//
//    // used to install packages
//    private final RControl rctrl;
//
//
//    public PackageManagerRControl(RControl rctrl) {
//        super(BiocepConfig.getInstance().getRLibsDir());
//        this.rctrl = rctrl;
//    }
//
//
//    /**
//     * Constructor
//     *
//     * @param projectLibPath R lib path of project
//     * @param rctrl          RControl to do the installation
//     */
//
//
//    public PackageManagerRControl(File projectLibPath, RControl rctrl) {
//        super(projectLibPath);
//        this.rctrl = rctrl;
//    }
//
//    /**
//     * Eval R command to get installed packages
//     *
//     * @param cmd R command
//     * @return List of String arrays with 3 elements (name, location, version)
//     */
//
//    protected List<String[]> evalInstalledPackagesCmd(String cmd) throws RemoteException {
//        List<String[]> result = new ArrayList<String[]>();
//
//        // assign result of "installed.packages" to ip and put column elements into table
//        RDataFrameRefW df = new RDataFrameRefW(rctrl.getRServices(), (RDataFrameRef) rctrl.getRef(cmd));
//        List<String> libs = df.getAsRCharW(0).getDataAsList();
//        List<String> locs = df.getAsRCharW(1).getDataAsList();
//        List<String> vers = df.getAsRCharW(2).getDataAsList();
//
//        for (int i = 0; i < libs.size(); i++) {
//            String[] row = new String[3];
//            row[0] = libs.get(i);
//            row[1] = locs.get(i);
//            row[2] = vers.get(i);
//            result.add(row);
//        }
//        return result;
//    }
//
//    /**
//     * Eval R command to install package
//     *
//     * @param cmd R command
//     */
//    protected void evalInstallPackageCmd(String cmd) throws RemoteException {
//        rctrl.eval(cmd);
//    }
//
//    /**
//     * Load list of R packages.
//     *
//     * @throws PackageNotFoundException if package was not found
//     */
//    public void loadPackage(RPackage rp)
//            throws PackageNotFoundException {
//        rctrl.loadPackage(rp.getName());
//    }
//
//
//    /**
//     * Load list of R packages (thru InstallPackagesSwingWorker).
//     *
//     * @throws PackageNotFoundException if package was not found
//     */
////    public void loadPackagesBySwingWorker(RControl rctrl, List<RPackage> packages,
////                                          InstallPackagesSwingWorker worker)
////            throws PackageNotFoundException {
////        for (RPackage rp : packages) {
////            rctrl.loadPackage(rp.getName());
//////            worker.publishMsg("Package " + rp.getName() + " was loaded.");
////        }
////    }
//
//
//    public List<String> getAttachedPackageNames() throws RemoteException {
//        List<String> result = new ArrayList<String>();
//        RListW si = rctrl.callAndGetRef("sessionInfo").asListW();
//        RCharW bps = si.atW("basePkgs").asCharW();
//        RListW ops = si.atW("otherPkgs").asListW();
//
//        for (int i=0; i<bps.length(); i++) {
//            result.add(bps.at(i));
//        }
//        result.addAll(ops.getNames());
//        return result;
//    }
//
//
//    public List<RPackage> getAttachedPackages() throws RemoteException {
//        List<RPackage> result = new ArrayList<RPackage>();
//        RListW si = rctrl.callAndGetRef("sessionInfo()").asListW();
//        RCharW bps = si.atW("basePkgs").asCharW();
//        RListW ops = (RListW) rctrl.callAndGetRef("otherPkgs");
//
//        for (int i=0; i<bps.length(); i++) {
//            result.add(makeBasePackageDesc(bps.at(i)));
//        }
//        for (int i=0; i<ops.length(); i++) {
//            result.add(makePackageDesc(ops.atW(i).asListW()));
//        }
//        return result;
//    }
//
//    private RPackage makeBasePackageDesc(String s) {
//        return new RPackage(s);
//    }
//
//    private RPackage makePackageDesc(RListW pdesc) {
//        return new RPackage(
//                pdesc.atW("Package").asCharW().stringVal(),
//                pdesc.atW("Version").asCharW().stringVal()
//        );
//    }
//
//
//}
//
