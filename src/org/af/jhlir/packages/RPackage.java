package org.af.jhlir.packages;

import java.io.File;


/**
 * Class to encapsulate name, version number and install path of an R package
 * If location is null, this means that the package is not installed.
 * Two packages are equal if they have the same name and version number and are
 * installed in the same location.
 * Version number have to be numbers separated by a single colon.
 */

public class RPackage {

    private String name;
    private String title;
    private String description;
    private File libpath;
    private String version;
//    private boolean loaded;
    private boolean defaultPack;

    public RPackage(String name, String title, File libpath, String version) {
        this.name = name;
        this.title = title;
        this.libpath = libpath;
        this.version = version;
//        this.loaded = loaded;
        this.defaultPack = defaultPack;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public File getLibpath() {
        return libpath;
    }

    public String getVersion() {
        return version;
    }

//    public boolean isLoaded() {
//        return loaded;
//    }

    public boolean isDefaultPack() {
        return defaultPack;
    }

    //    public static RPackage[] filter(RPackage[] allpacks, String regex) {
//        if (regex == null || regex == "" || allpacks == null) return allpacks;
//        Pattern p = validFilter(regex);
//        if (p == null) return allpacks;
//        Vector<RPackage> vec = new Vector<RPackage>();
//        RPackage pack;
//        for (int i = 0; i < allpacks.length; i++) {
//            pack = allpacks[i];
//            if (p.matcher(pack.getPack()).matches() || p.matcher(pack.getDescription()).matches()) {
//                vec.add(allpacks[i]);
//            }
//        }
//        if (vec.size() == 0) return null;
//        RPackage[] out = new RPackage[vec.size()];
//        vec.toArray(out);
//        return out;
//    }
//
//    public static Pattern validFilter(String regex) {
//        Pattern p = null;
//        try {
//            p = Pattern.compile("^.*" + regex + ".*$", Pattern.CASE_INSENSITIVE);
//        } catch (Exception e) {
//        }
//        return p;
//    }


//    /**
//     * Constructor
//     *
//     * @param name name of package
//     */
//
//    public RPackage(String name) {
//        this(name, "", null, false);
//    }
//
//    /**
//     * @param name    name of package
//     * @param version version number (numbers separated by a single colons)
//     */
//    public RPackage(String name, String version) {
//        this(name, version, null, false);
//    }
//
//    /**
//     * @param name     name of package
//     * @param version  version number (numbers separated by a single colons)
//     * @param location install path
//     */
//    public RPackage(String name, String version, File location, boolean loaded) {
//        this.name = name;
//        this.version = version;
//        this.location = location;
//        this.loaded = loaded;
//        if (!Pattern.matches("[0-9.,]*", version)) {
//            throw new RuntimeException("RPackage version string has wrong format: " + version);
//        }
//    }
//
//    /**
//     * Two packages are equal if they have the same name and version number and are
//     * installed in the same location.
//     *
//     * @param o the other package
//     * @return true iff both packages are equal
//     */
//    public boolean equals(Object o) {
//        RPackage rp = (RPackage) o;
//        return name.equals(rp.name) &&
//                version.equals(rp.version) &&
//                location.equals(rp.version);
//    }
//
//    /**
//     * @return hashcode
//     */
//    public int hashCode() {
//        // yeah, this is bollocks
//        return name.hashCode() + version.hashCode() + location.hashCode();
//    }
//
//    /**
//     * @return package description
//     */

    public String toString() {
        return name + " " + version + " " + libpath;
//        name + " " + version + " (" + location.getAbsolutePath() + ")";
    }
//
//    /**
//     * compares to another package
//     * lexicographical comparism for name and location, then version comparism
//     *
//     * @param rp the other package
//     * @return neg. int.,0 or pos. int.
//     */
//    public int compareTo(RPackage rp) {
//        int n = name.compareTo(rp.name);
//        if (n != 0)
//            return n;
//        int l = location.compareTo(rp.location);
//        if (l != 0)
//            return l;
//        return compareVersionTo(rp);
//    }
//
//    /**
//     * Is the package the same (reagarding name) as a given package and at least as new (regarding version)?
//     *
//     * @param rp the other package
//     * @return true iff rp is of the same name and same or newer version
//     */
//    public boolean isNewerOrSameVersionOf(RPackage rp) {
//        return getName().equals(rp.getName()) && compareVersionTo(rp) >= 0;
//    }
//
//
//    /**
//     * Is the package at least as new as a given version?
//     *
//     * @param minVer minimal version
//     * @return true iff this package has at least version minVer
//     */
//    public boolean isNewerOrSameVersionOf(String minVer) {
//        return VersionComparator.compare(getVersion(), minVer) >= 0;
//    }
//
//
//    /**
//     * How does the version of this package compare to the version of another package?
//     *
//     * @param rp the other package
//     * @return neg. int.,0 or pos. int.
//     */
//    public int compareVersionTo(RPackage rp) {
//        //logger.debug(getVersion()+" is "+(compareVersionTo(rp.getVersion())<0?"not ":" ")+"suitable, we want >= "+rp.getVersion()+".");
//        return VersionComparator.compare(getVersion(), rp.getVersion());
//    }
//
//    /**
//     * @return name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * @return version
//     */
//    public String getVersion() {
//        return version;
//    }
//
//    /**
//     * @return location
//     */
//    public File getLocation() {
//        return location;
//    }
//
//    /**
//     * @return was package loaded?
//     */
//    public boolean isLoaded() {
//        return loaded;
//    }
//
//
//    public static List<RPackage> packDescs2Rpacks(List<String[]> packDescs) {
//        List<RPackage> result = new ArrayList<RPackage>();
//        for (String[] pd : packDescs) {
//            result.add(new RPackage(pd[0], pd[1]));
//        }
//        return result;
//    }
//

}
