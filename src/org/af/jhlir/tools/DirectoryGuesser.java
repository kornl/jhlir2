package org.af.jhlir.tools;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.af.commons.io.FilterContains;
import org.af.commons.tools.OSTools;


/**
 * Class encapsulates methods to guess installation paths for used third party applications
 * and our own applications
 */

public class DirectoryGuesser {

    public abstract static class Predicate {
        abstract boolean check(File f);
    }

    public static class ContainsFilePredicate extends Predicate {

        private String containedFile;
        public ContainsFilePredicate(String f) {
            containedFile = f;
        }

        boolean check(File f) {
            if (!f.exists() || !f.isDirectory())
                return false;
            else {
                File cFile = new File(f, containedFile);
                return Arrays.asList(f.listFiles()).contains(cFile);
            }
        }
    }

    public static class RHomeBinPredicate extends ContainsFilePredicate {

        public RHomeBinPredicate() {
            super("R.exe");
        }
    }


    /**
     * Searches for files and returns the first candidate.
     * @param search Array of paths to the root directories of this search.
     * @param searchDir Files which contain these Strings as substrings should be searched.
     * @param searchFile Files which contain these Strings as substrings are final candidates.
     * @param directory is the file we are looking for a directory?
     * @return
     */
	public static String searchDir(String[] search, String[] searchDir, String[] searchFile, boolean directory) {
		// Generalize to a vector of candidates?
		String result = "";
		for (String s : search) {
			File dir = new File(s);
			if (dir.exists()) {
				String subresult=searchforFile(dir, searchDir, searchFile, directory);
				if (!subresult.equals("")) result = subresult;
			}
		}
		return result;
	}

    /**
     * Searches for files and returns the first candidate.
     * @param searchDir Files which contain these Strings as substrings should be searched.
     * @param searchFile Files which contain these Strings as substrings are final candidates.
     * @param directory is the file we are looking for a directory?
     * @return
     */
	public static String searchforFile(File dir, String[] searchDir, String[] searchFile, boolean directory) {
		String result = "";

		File[] candidates = dir.listFiles(new FilterContains(searchDir));
		if ((candidates == null) || (dir == null)) {
			return "";
			}
//		logger.info("Found "+candidates.length+" candidates in "+dir.getAbsolutePath()+".");
		for (File f : candidates) {
			if(f.isDirectory() == directory) {
				String name = f.getName().toUpperCase();
				for (String s : searchFile) {
					if (name.indexOf(s) != -1) {
						result = f.getAbsolutePath();
//						logger.info("Found: "+f.getAbsolutePath());
					}
				}
			}
			if (f.isDirectory()) {
				String subresult=searchforFile(f, searchDir, searchFile, directory);
				if (!subresult.equals("")) result = subresult;
			}
		}
		return result;
	}


    public static File searchForFile(File dir, final List<String> searchFirst, final Predicate pred) {

        // get all files in dir
        List<File> candidates = Arrays.asList(dir.listFiles());

        // sort files: first the ones which contain a string in searchFirst,
        // lower indices in searchfirst are more important
        Collections.sort(candidates , new Comparator<File>() {
            public int compare(File o1, File o2) {
                int i1 = getSearchFirstIndex(o1.getName(), searchFirst);
                int i2 = getSearchFirstIndex(o2.getName(), searchFirst);
                // if both dont contain something from searchFirst or both at the same index, dont really care
                if (i1 == i2)
                   return o1.getName().compareTo(o2.getName());
                else
                   return i2 - i1;
            }
            private int getSearchFirstIndex(String s, List<String> searchFirst) {
                for (int i=0; i<searchFirst.size(); i++)
                    if (s.contains(searchFirst.get(i)))
                        return i;
                return -1;
            }
        });

        for (File f : candidates) {
            if (pred.check(f))
                return f;
            else if (f.isDirectory()) {
                File result = searchForFile(f, searchFirst, pred);
                if (result != null)
                    return result;
            }
        }
        return null;
    }


    /**
     * Guesses absolute path to PDF viewer.
     * @return Guessed path to PDF viewer.
     */
    public static String guessPDFViewerPath() {
		String[] search = {"C:\\Programme", "C:\\Program Files" };
		String[] searchDir = {"ADOBE", "ACR", "READER"};
		String[] searchFile = {"ACRORD32.EXE", "ACRORD64.EXE"};
		return searchDir(search, searchDir, searchFile, false);
    }

    /**
     * Guesses R Home.
     * @return Guessed path to R Home.
     */
    public static String guessRHome() {
        // if R_HOME is set use this
        if (System.getenv("R_HOME") != null)
            return System.getenv("R_HOME");

    	if (OSTools.isWindows()) {
            // if windows, try registry first
    		String instPath = null;
    		try {
    			instPath = RegistryManager.getInstallPath();
    		} catch(UnsatisfiedLinkError e) {
                e.printStackTrace();
    		}
            if (instPath != null)
                return instPath;

            String[] search = {System.getenv("ProgramFiles")};
    		String[] searchDir = {"R"};
    		String[] searchFile = {"R-2."};
    		return searchDir(search, searchDir, searchFile, true);
    	} else {
    		String path = "";
    		if (OSTools.isLinux()) {
    			path = guessRHomeUnix("rhome_linux.sh");
    		} else if (OSTools.isMac()) {
    			path = guessRHomeUnix("rhome_mac.sh");
    		}
    		File rhome = new File(path);
			if (rhome.exists()) return rhome.getAbsolutePath();
			return "";
    	}
    }

    private static String guessRHomeUnix(String script) {
//    	try {
//            FileTransfer ft = FileTransfer.getFileTransfer();
////            GeneralConfig conf = Configuration.getInstance().getGeneralConfig();
//
//			String file = ft.copyFileToLocalDir(script,
//                    new File(conf.getTempDir())).getAbsolutePath();
//			Vector<String> command = new Vector<String>();
//			command.add(OSTools.getBash());
//			command.add(file);
//			List<String> list = ProcessResult.getResult(command);
//			return list.get(0);
//		} catch (IOException e) {
////			logger.error("Error executing RScript: "+e.getMessage());
//		}
		return "";
    }


    public static String guessJavaHome() {
        return System.getProperty("java.home");
    }

    public static String guessDesktop() {
        File desktop = new File(System.getProperty("user.home")+"/Desktop");
        if (desktop.exists())
            return desktop.getAbsolutePath();
        return null;
    }
}
