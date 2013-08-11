//package rpackages;
//
//import org.kchine.r.server.RServices;
//import org.kchine.r.RDataFrame;
//import org.kchine.r.RMatrix;
//import org.kchine.r.RChar;
//import org.kchine.r.workbench.RGui;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.ArrayList;
//import java.rmi.RemoteException;
//
//import rinterface.RDataFrameW;
//import rinterface.RMatrixCharW;
////
////import af.statguitoolkit.errorhandling.ErrorHandlerSGTK;
////import af.statguitoolkit.logging.LoggingSystemSGTK;
////import static af.statguitoolkit.rpackages.InstallResult.Type.ALREADY_THERE;
////import static af.statguitoolkit.rpackages.InstallResult.Type.INSTALLED;
////import org.apache.commons.io.FileUtils;
////import org.apache.commons.logging.Log;
////import org.apache.commons.logging.LogFactory;
////
////import java.io.File;
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////
////
/////**
//// * Abstract base class to handle everything concerning the installation of R Packages.
//// * Derived classes either uses RExecutor or RControl for implementation.
//// * Packages are all installed into a given lib path - which should be application specific and
//// * different from any usual R lib path of the installed R system.
//// */
//
////
//public class PackageManager {
//    private RGui rgui;
//    private RServices rs;
//
//    public PackageManager(RGui rgui) {
//        this.rgui = rgui;
//        rs = rgui.getR();
//    }
//
//    public RGui getRGui() {
//        return rgui;
//    }
//    //
////    // installed R libs, cached, call readInstalledPackages to update
////    private List<RPackage> rpacks = new ArrayList<RPackage>();
////    // path to the project R libs, parts always separated by forward slashes
////    protected String projectLibPath;
////
////
////    /**
////     * Constructor
////     *
////     * @param projectLibPath R lib path of project
////     */
////    public PackageManager(File projectLibPath) {
////        this.projectLibPath = projectLibPath.getAbsolutePath();
////        // we want forward slashes for R
////        this.projectLibPath = this.projectLibPath.replace(
////                Character.toString(File.separatorChar), "/");
////    }
////
////    /**
////     * get R command to install package from CRAN, used by abstract evalInstallPackageCmd
////     *
////     * @param name name of R package
////     * @return R command to install package from CRAN
////     */
////    protected String getInstallPackageCmd(String name) {
////    	String cmd = "install.packages(c('" + name + "')," +
////    		"destdir=\"" + projectLibPath + "\"," +
////    		"lib=\"" + projectLibPath + "\"," +
////    		"repos=c('http://www.rforge.net/','http://cran.r-project.org')," +
////    		"dependencies = c(\"Depends\", \"Imports\")" +
////    		")";
////    	return cmd;
////    }
////
////    /**
////     * install newest version of a package from CRAN
////     *
////     * @param name   package to install
////     * @param minVer minimal version required
////     * @return installed RPackage object
////     * @throws IOException              can be thrown by RExecutor
////     * @throws InterruptedException     can be thrown by RExecutor
////     * @throws CantFindPackageException if not found on CRAN
////     */
////    public InstallResult installPackage(String name, String minVer)
////            throws IOException, InterruptedException {
////        logger.info("Trying to install: " + name + " from Cran...");
////        String cmd = getInstallPackageCmd(name);
////        // use derived class
////        evalInstallPackageCmd(cmd);
////        // check if installation worked
////        readInstalledPackages();
////        RPackage p = getPackage(name);
////        // not found at all?
////        if (p == null) {
////            // can't hurt to delete...
////            deletePackage(name);
////            return new InstallResult("There was an error installing package:\n" + name);
////            /*return new InstallResult("Can't find " + name + " on CRAN!\n" +
////            "(at least for your version of R)");*/
////        } else {
////            // installed but version is too old
////            if (!p.isNewerOrSameVersionOf(minVer)) {
////                deletePackage(name);
////                String s = "Can't find version " + minVer + " of " + name +
////                        " on CRAN\nfor your version of R, only version " + p.getVersion() + "!";
////                return new InstallResult(s);
////            }
////            return new InstallResult(INSTALLED, p);
////        }
////    }
////
////    /**
////     * deletes dir. of R package in projectLibPath (after faulty installation)
////     *
////     * @param name package
////     */
////    private void deletePackage(String name) throws IOException {
////        File p = new File(projectLibPath, name);
////        if (p.exists() && p.isDirectory()) {
////            FileUtils.deleteDirectory(p);
////        }
////    }
////
////    /**
////     * Eval R command to install package
////     *
////     * @param cmd R command
////     * @throws IOException          thrown by RExecutor
////     * @throws InterruptedException thrown by RExecutor
////     */
////    abstract protected void evalInstallPackageCmd(String cmd)
////            throws IOException, InterruptedException;
////
////    /**
////     * Eval R command to get installed packages
////     *
////     * @param cmd R command
////     * @return List of String arrays with 3 elements (name, location, version)
////     * @throws IOException          thrown by RExecutor
////     * @throws InterruptedException thrown by RExecutor
////     */
////    abstract protected List<String[]> evalInstalledPackagesCmd(String cmd)
////            throws IOException, InterruptedException;
//
//    //
//    /**
//     * Updates the internal list of installed packages by using the derived class
//     * and calling evalInstalledPackagesCmd
//     *
//     * @throws IOException          thrown by RExecutor or RControl
//     * @throws InterruptedException thrown by RExecutor or RControl
//     */
//    public List<RPackage> readInstalledPackages(File libPath) throws IOException, InterruptedException {
//
//        List<RPackage> result = new ArrayList<RPackage>();
//
////        rgui.getRLock().lock();
////        RServices rs = null;
//        RMatrixCharW ip = new RMatrixCharW(rs, (RMatrix) rs.getObject("svTools:::installedPackages()"));
//        for (int i = 0; i < ip.getRowNr(); i++) {
//            result.add(new RPackage(
//                    ip.get(i, "Package"),
//                    ip.get(i, "Title"),
//                    new File(ip.get(i, "LibPath")),
//                    ip.get(i, "Version"),
//                    ip.get(i, "Loaded").equals("1"),
//                    ip.get(i, "Default").equals("1")
//                    ));
//        }
//        return result;
//    }
//
//    public String getPackageDescription(String pack) throws RemoteException{
//        return rs.getObjectConverted("paste( svTools:::packdesc('"+ pack +"'), collapse = '\n' )").toString();
//    }
//
//    public void requirePackage(String name) throws RemoteException{
//        rs.evaluate("require(" + name + ")");
//    }
//
//    public void detachPackage(String name) throws RemoteException{
//        rs.evaluate("detach('package:" + name + "')");
//    }
//
//     public Vignette getVignette(String topic, String pack ){
////		try{
////		  rgui.getRLock().lock() ;
////		  rgui.getR().evaluate( ".vignette <- vignette(topic='"+topic+"', package='"+pack+"')" ) ;
////			String file = ((RChar) rs.getObject(".vignette$file") ).getValue()[0] ;
////			String pdf  = ((RChar) rs.getObject(".vignette$pdf") ).getValue()[0] ;
////			rgui.getR().evaluate( "rm( .vignette )" ) ;
////		} catch( Exception e){  } finally{
////		  rgui.getRLock().unlock() ;
////		}
//         return null;
//	}
//
//
////
////        List<String[]> rpacksTable = evalInstalledPackagesCmd(instPackCmd);
////
////        // parse String arrays into RPackage objects
////        for (String[] row : rpacksTable) {
////            String ver = row[2];
////            // some version nrs have "-" as separator
////            ver = ver.replace("-", ".");
////            File loc = new File(row[1]);
////            rpacks.add(new RPackage(row[0], ver, loc, false));
////        }
////        logger.info("Reading installed packages: Done.");
//    }
////
////    /**
////     * get installed packages
////     * Nota Bene: you probably want to call readInstalledPackages before this!
////     *
////     * @return list of last read installed packages
////     */
////    public List<RPackage> getInstalledPackages() {
////        return rpacks;
////    }
////
////    /**
////     * Find all the packages from the given list, which are not installed.
////     * Version of given packages are interpreted as minimally required versions.
////     *
////     * @param packages given packages to search for
////     * @return not installed packages of the given list
////     * @throws IOException          thrown by RExecutor or RControl
////     * @throws InterruptedException thrown by RExecutor or RControl
////     */
////    public List<RPackage> findNotInstalledPackages(List<RPackage> packages)
////            throws IOException, InterruptedException {
////        List<RPackage> result = new ArrayList<RPackage>();
////        for (RPackage rp : packages) {
////            if (!isInstalled(rp.getName(), rp.getVersion()))
////                result.add(rp);
////        }
////        return result;
////    }
////
////    /**
////     * @param name package to find
////     * @return true iff package of the same name installed
////     */
////    public boolean isInstalled(String name) {
////        return getPackage(name, "0") != null;
////    }
////
////    /**
////     * @param name package to find
////     * @param minVer minimal version required
////     * @return true iff package of the same name, which has at
////     *         least the given version, is installed
////     * @throws IOException          thrown by RExecutor or RControl
////     * @throws InterruptedException thrown by RExecutor or RControl
////     */
////    public boolean isInstalled(String name, String minVer)
////            throws IOException, InterruptedException {
////        readInstalledPackages();
////        RPackage p = getPackage(name, minVer);
////        return p != null && p.isNewerOrSameVersionOf(minVer);
////    }
////
////
////    /**
////     * @param name package to find
////     * @param minVer minimal version required
////     * @return first found installed package of the same name and which has at
////     *         least the given version or null otherwise
////     */
////    public RPackage getPackage(String name, String minVer) {
////        for (RPackage rp2 : rpacks)
////            if (rp2.isNewerOrSameVersionOf(new RPackage(name, minVer)))
////                return rp2;
////        return null;
////    }
////
////    /**
////     * @param name package to find
////     * @return first found installed package of the same name
////     */
////    public RPackage getPackage(String name) {
////        return getPackage(name, "0");
////    }
////
////    /**
////     * Install a package if not present on the system .
////     *
////     * @param name package to find
////     * @param minVer minimal version required
////     * @return InstallResult
////     * @throws IOException          thrown by RExecutor or RControl
////     * @throws InterruptedException thrown by RExecutor or RControl
////     * @throws CantFindPackageException if not found on CRAN
////     */
////    public InstallResult installIfNecessary(String name, String minVer)
////            throws IOException, InterruptedException {
////        logger.info("Checking R package: " + name + ", " + minVer);
////        readInstalledPackages();
////        RPackage p = getPackage(name, minVer);
////        if (p != null) {
////            return new InstallResult(ALREADY_THERE, p);
////        } else {
////            InstallResult ir = installPackage(name, minVer);
////            logger.info("-> installed!");
////            return ir;
////        }
////    }
////
////    /**
////     * Install a package if not present on the system. Nearly the same as installIfNecessary
////     * but convenience method as all exceptions are caught and shown in owner component via dialog.
////     *
////     * @param name package to find
////     * @param minVer minimal version required
////     * @return InstallResult
////     */
////    public InstallResult requirePackage(String name, String minVer) {
////        LoggingSystemSGTK.getInstance().getApplicationLog().logRequiredRPackage(name, minVer);
////        InstallResult res = null;
////        try {
////            res = installIfNecessary(name, minVer);
////        } catch (Exception e) {
////            String msg = "Could not install " + name + " version " + minVer + "!";
////            ErrorHandlerSGTK.getInstance().makeCritErrDialog(msg, e);
////        }
////        return res;
////    }
////
////    /**
////     * Install a package if not present on the system (thru a InstallPackagesSwingWorker).
////     * Nearly the same as requirePackage but convenience method as progress is passed to the SW.
////     *
////     * @param name package to find
////     * @param minVer minimal version required
////     * @param worker used SwingWorker
////     * @return InstallResult
////     */
//////    public InstallResult requirePackageByWorker(String name, String minVer, InstallPackagesSwingWorker worker) {
//////        worker.publish("Trying to install " + name + " " + minVer + " ...");
//////        InstallResult res = requirePackage(name, minVer, worker.getOwner());
//////        RPackage p = res.rPackage;
//////            if (res.wasInstalled()) {
//////                worker.publish("Installed package " + p.getName() + " " + p.getVersion());
//////            }
//////        return res;
//////    }
////
////
////
////}
