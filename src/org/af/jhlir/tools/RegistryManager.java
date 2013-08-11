package org.af.jhlir.tools;

import ca.beq.util.win32.registry.RegistryKey;
import ca.beq.util.win32.registry.RootKey;
import ca.beq.util.win32.registry.Win32Exception;


/*
2.17 Does R use the Registry?

No, not when R itself is running.

When you run the R installer, there are options (under `Select Additional Tasks')
to `Save version number in registry' and `Associate R with .RData files'.

If you tick the first option, the following string entries are added to the Windows registry:

    * HKEY_LOCAL_MACHINE\Software\R-core\R\Current Version contains the version number, currently 2.7.0.
    * HKEY_LOCAL_MACHINE\Software\R-core\R\[version]\InstallPath (where [version] is currently 2.7.0) contains the path to the R home directory.

If you do not have administrative privileges on the machine while running the installer,
then the entries are created under HKEY_CURRENT_USER.

If you tick the second option (`Associate R with .RData files') and have administrative privileges,
then entries are created under HKEY_CLASSES_ROOT\.RData and HKEY_CLASSES_ROOT\RWorkspace.

After installation you can add the Registry entries in HKEY_LOCAL_MACHINE by running RSetReg.exe
in the bin folder, and remove them by running this with argument /U. Note that this requires
administrative privileges and neither sets up nor removes the file associations.

*/


public class RegistryManager {

    private static String getValue(RootKey rootKey, String path, String key) {
        RegistryKey r = new RegistryKey(rootKey, path);
        if (!r.exists())
            return null;
        else {
            try {
                return r.getValue(key).getStringValue();
            }
            catch(Win32Exception e) {
                return null;
            }
        }
    }

    private static String getRegistryPathforR(String version) {
        return "Software\\R-core\\R\\"+version;
    }

    private static String findRVersion(RootKey rootKey) {
        return getValue(rootKey, "Software\\R-core\\R", "Current Version");
    }

    private static String findInstallPath(RootKey rootKey, String version) {
        return getValue(rootKey, getRegistryPathforR(version), "InstallPath");
    }

    public static String getInstallPath() {
        String version = null;
        String instPath = null;
        // look for current user first
        version = findRVersion(RootKey.HKEY_CURRENT_USER);
        if (version != null) {
            instPath = findInstallPath(RootKey.HKEY_CURRENT_USER, version);
            if (instPath != null)
                return instPath;
        }
        // nothing found, try local machine
        version = findRVersion(RootKey.HKEY_LOCAL_MACHINE);
        if (version != null) {
            instPath = findInstallPath(RootKey.HKEY_LOCAL_MACHINE, version);
            if (instPath != null)
                return instPath;
        }
        // no luck
        return null;
    }

}
