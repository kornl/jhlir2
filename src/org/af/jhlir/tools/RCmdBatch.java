package org.af.jhlir.tools;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.af.jhlir.packages.CantFindPackageException;
import org.af.jhlir.packages.RPackage;


/**
 * Execute R in batch mode. Use this to query R for information / set up stuff / install packages,
 * before you can properly connect to it through jhlir. Don't rely on this class too much and try to connect
 * to R properly as aerly as possibly as error recognition and handling is shaky, as one can only use
 * the textual output of the R batch script.
 * Only prerequisite to invoke this class is knowledge of the path to R_HOME.
 */

public class RCmdBatch {

    public static final String[] R_ARGS = {"--vanilla", "--slave"};
    //	= {"--no-site-file","--no-init-file","--no-restore","--no-save"};
    protected File rHome;
    protected File tempDir;

    protected String libPaths;
    protected String rVersion;

    /**
     * Constructor
     *
     * @param rHome R Home directory.
     */
    public RCmdBatch(File rHome) {
        this(rHome.getAbsolutePath());
    }


    /**
     * Constructor
     *
     * @param rHome Absolute Path to R Home directory.
     */
    public RCmdBatch(String rHome) {
        this.rHome = new File(rHome);
        this.tempDir = new File(System.getProperty("java.io.tmpdir"));

        if (rHome == null || rHome.trim().equals("")) {
            String s = "Empty R_Home passed to RCmdBatch! It was: " + rHome;
            throw new RuntimeException(s);
        }
    }

    /**
     * Excecute a list of R commands in batch mode.
     * R CMD BATCH will write an output file, whose lines are returned as strings.
     *
     * @param input List of R commonds
     * @return Contents of output file.
     * @throws RCmdBatchException In case of IO errors or general errors with the R CMD BATCH process. Underlying exceptions are encapsulated as causing exceptions.
     */
    public List<String> exec(List<String> input) throws RCmdBatchException {
        List<String> output = new ArrayList<String>();
        String fn = "r_batch_" + Calendar.getInstance().getTime().getTime();
        File batchFile = new File(tempDir, fn);

        try {
            makeRBatchScript(batchFile, input);
        } catch (FileNotFoundException e) {
            throw new RCmdBatchException("", e);
        }

        List<String> commands = new ArrayList<String>();
        commands.add(getRExePath().getAbsolutePath());
        commands.add("CMD");
        commands.add("BATCH");
        commands.add("--no-timing");
        for (String arg : R_ARGS) {
            commands.add(arg);
        }
        commands.add(batchFile.getAbsolutePath());


        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(tempDir);
//        logger.info("Starting process: " + pb.command().toString());
        Process ps = null;
        try {
            ps = pb.start();
            logProcessOutput(ps);
        } catch (IOException e) {
            throw new RCmdBatchException("", e);
        }
        int exitCode = 0;
        try {
            exitCode = ps.waitFor();
        } catch (InterruptedException e) {
            throw new RCmdBatchException("", e);
        }

        File outputFile = new File(tempDir, fn + ".Rout");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(outputFile));
        } catch (FileNotFoundException e) {
            throw new RCmdBatchException("", e);
        }
        String line;
        try {
            while ((line = reader.readLine()) != null)
                output.add(line);
        } catch (IOException e) {
            throw new RCmdBatchException("sdf: ", e);
        }

        if (exitCode != 0) {
            String s = "R CMD BATCH exited abnormally with code: " + exitCode + "\n";
            s += "Batch file was:\n" + input.toString() + "\n";
            s += "R Output was:\n" + output.toString();
            throw new RCmdBatchException(s);
        }

        return output;
    }

    protected void makeRBatchScript(File target, List<String> commands) throws FileNotFoundException {
//    	logger.info("Creating R batch script: "+target.getAbsolutePath());
        PrintStream p = new PrintStream(new FileOutputStream(target));
        for (String c : commands)
            p.println(c);
        p.close();
    }


    /**
     * Excecute a list of R commands in batch mode and return the content of a R variable as a string.
     * Uses exec method.
     * !! Don't use this for too complicated stuff. At the moment the R variable must only generate a one-line string when printed!!
     *
     * @param input List of R commands
     * @param var   R variable you want to retrieve.
     * @return Contents R variable as string when printed in R.
     * @throws RCmdBatchException In case of IO errors or general errors with the R CMD BATCH process. Underlying exceptions are encapsulated as causing exceptions.
     */
    public String retrieveInfo(List<String> input, String var) throws RCmdBatchException {
        return retrieveInfo(input, Arrays.asList(var)).get(var);
    }

    /**
     * Excecute a list of R commands in batch mode and return the contents of R variables as strings.
     * Uses exec method.
     * !! Don't use this for too complicated stuff. At the moment the R variables must only generate a one-line string when printed!!
     *
     * @param input List of R commands
     * @param vars  R variables you want to retrieve.
     * @return Contents R variables as strings when printed in R.
     * @throws RCmdBatchException In case of IO errors or general errors with the R CMD BATCH process. Underlying exceptions are encapsulated as causing exceptions.
     */
    public Map<String, String> retrieveInfo(List<String> input, List<String> vars) throws RCmdBatchException {

        Map<String, String> res = new HashMap<String, String>();
        List<String> is = new ArrayList<String>();
        for (String v : vars) {
            is.add(v + "<- ''");
        }
        is.addAll(input);
        for (String v : vars) {
            is.add("print(paste(\"<var>\"," + v + "))");
        }
        List<String> os = exec(is);

        for (int i = 0; i < vars.size(); i++) {
            String s = os.get(os.size() - 1 - i);
            int n1 = s.indexOf("<var>");
            int n2 = s.lastIndexOf("\"");
            res.put(vars.get(vars.size() - 1 - i), s.substring(n1 + 6, n2));
        }
        return res;
    }

    protected File getRExePath() {
        return new File(new File(rHome, "bin"), "R");
    }


    /**
     * Retrieve information about an installed R package on the current library paths.
     * Uses exec method.
     *
     * @param pack R package name.
     * @return Information about R package or null if package was not found.
     * @throws RCmdBatchException See {@link #exec}.
     */
    public RPackage getInstalledPackInfo(String pack) throws RCmdBatchException {
        List<String> is = Arrays.asList(
                "found <- require('" + pack + "')",
                "if (found) {",
                "desc <- packageDescription('" + pack + "')",
                "title <- desc$Title",
                "ver <- desc$Version",
                "lp <- dirname(dirname(dirname(attr(desc, 'file'))))",
                "}"
        );


        Map<String, String> m = retrieveInfo(is, Arrays.asList("title", "lp", "ver"));
        if (m.get("lp").trim().equals(""))
            return null;
        return new RPackage(pack, m.get("title"), new File(m.get("lp")), m.get("ver"));
    }

    /**
     * Check whether an R package was installed on the current library paths.
     * Simply performs getInstalledPackInfo(pack) == null
     *
     * @param pack R package name.
     * @return True if package was found.
     * @throws RCmdBatchException See {@link #exec}.
     */
    public boolean isInstalled(String pack) throws RCmdBatchException {
        return getInstalledPackInfo(pack) == null;
    }

    /**
     * Retrieves all installed R packages on the library paths. Uses exec method.
     *
     * @return List of installed R package names.
     * @throws RCmdBatchException See {@link #exec}.
     */
    public List<String> getInstalledPackages() throws RCmdBatchException {
        List<String> is = Arrays.asList(
                "ps <- installed.packages(noCache=TRUE)[,'Package']",
                "names(ps) <- NULL",
                "ps <- paste(ps, collapse=';')"
        );
        String ps = retrieveInfo(is, "ps");
        String[] names = ps.split(";");
        return Arrays.asList(names);
    }


    private RPackage installPackage(String p, String repos, File where) throws CantFindPackageException, RCmdBatchException {
        List<String> is = Arrays.asList(
                "install.packages('" +
                        p + "'" +
                        ", repos='" + repos + "'" +
                        (where == null ? "" : ", destdir='" + where.getAbsolutePath().replace("\\", "/") + "', lib='" + where.getAbsolutePath().replace("\\", "/") + "'") +
                        ")"
        );
        List<String> os = exec(is);
        for (String s : os) {
            if (s.contains(p) && s.contains("is not available"))
                throw new CantFindPackageException("Can't find package " + p + " on repository " + repos + "!");
        }
        return getInstalledPackInfo(p);
    }

    private RPackage installPackage(String pack, String repos) throws CantFindPackageException, RCmdBatchException {
        return installPackage(pack, repos, null);
    }


    /**
     * Installs an R package from CRAN into the defaullt library directory. Uses exec method.
     *
     * @param pack R package to install.
     * @return Package information after install.
     * @throws CantFindPackageException If the package is not available on CRAN.
     * @throws RCmdBatchException       See {@link #exec}.
     */
    public RPackage installCranPackage(String pack) throws CantFindPackageException, RCmdBatchException {
        return installPackage(pack, "http://cran.r-project.org");
    }

    /**
     * Installs an R package from CRAN. Uses exec method.
     *
     * @param pack  R package to install.
     * @param where Directory to install the package into.
     * @return Package information after install.
     * @throws CantFindPackageException If the package is not available on CRAN.
     * @throws RCmdBatchException       See {@link #exec}.
     */
    public RPackage installCranPackage(String pack, File where) throws CantFindPackageException, RCmdBatchException {
        return installPackage(pack, "http://cran.r-project.org", where);
    }

    /**
     * Installs an R package from R-Forge into the defaullt library directory. Uses exec method.
     *
     * @param pack R package to install.
     * @return Package information after install.
     * @throws CantFindPackageException If the package is not available on R-Forge.
     * @throws RCmdBatchException       See {@link #exec}.
     */
    public RPackage installRForgePackage(String pack) throws CantFindPackageException, RCmdBatchException {
        return installPackage(pack, "http://R-Forge.R-project.org");
    }

    /**
     * Installs an R package from R-Forge. Uses exec method.
     *
     * @param pack  R package to install.
     * @param where Directory to install the package into.
     * @return Package information after install.
     * @throws CantFindPackageException If the package is not available on R-Forge.
     * @throws RCmdBatchException       See {@link #exec}.
     */
    public RPackage installRForgePackage(String pack, File where) throws CantFindPackageException, RCmdBatchException {
        return installPackage(pack, "http://R-Forge.R-project.org", where);
    }


//    /*
//     *  CMD INSTALL [options] [-l lib] pkgs
//	 *  Arguments
//     *  pkgs 	A space-separated list with the path names of the packages to be installed.
//     *  lib 	the path name of the R library tree to install to.
//     *  options 	a space-separated list of options through which in particular the process
//     *  for building the help files can be controlled. Options should only be given once.
//     *  Use R CMD INSTALL --help for the current list of options.
//     */
//
//    public int installPackage(File file, String lib) throws IOException, InterruptedException {
//    	List<String> commands = new ArrayList<String>();
//		commands.add(getRExePath());
//        commands.add("CMD");
//        commands.add("INSTALL");
//        commands.add("-l");
//        commands.add(lib);
//        commands.add(file.getAbsolutePath());
//        ProcessBuilder pb = new ProcessBuilder(commands);
//        pb.directory(new File(tempDir));
//        logger.info("Starting process: " + pb.command().toString());
//        Process ps = pb.start();
//        logProcessOutput(ps);
//        int exitCode = ps.waitFor();
//        return exitCode;
//    }

    //

    private void logProcessOutput(Process ps) throws IOException {
        BufferedReader is = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String line;
        while ((line = is.readLine()) != null) {
//        	logger.info("Process: "+ line);
        }
        is = new BufferedReader(new InputStreamReader(ps.getErrorStream()));
        while ((line = is.readLine()) != null) {
//        	logger.error("Process error: "+line);
        }
    }

    /**
     * Collects information of the used R engine and stores it in this object.
     *
     * @throws RCmdBatchException
     * @see this.getRVersion
     * @see this.getLibPaths
     */
    public void retrieveRInfo() throws RCmdBatchException {
        List<String> input = new ArrayList<String>();
        input.add("lp <-  paste(.libPaths(),collapse=.Platform$path.sep)");
        input.add("ver <-  paste(sessionInfo()$R.version$major, sessionInfo()$R.version$minor, sep=\".\")");

        Map<String, String> m = retrieveInfo(input, Arrays.asList("lp", "ver"));
        libPaths = m.get("lp");
        rVersion = m.get("ver");
    }

    /**
     * Get R version as a String.
     * Call retrieveRInfo() before this.
     *
     * @return R version.
     */
    public String getRVersion() {
        return rVersion;
    }

    /**
     * Get the R lib paths, separated by the path separator of your platform.
     * Call retrieveRInfo() before this.
     *
     * @return R lib paths.
     */
    public String getLibPaths() {
        return libPaths;
    }
}



