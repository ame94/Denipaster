package ca.ame94.denipaster.denizen;

import ca.ame94.denipaster.pastebin.Contents;
import ca.ame94.denipaster.util.Logger;
import sun.rmi.runtime.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileSystem {

    public static void delScriptFile(String filename) {
        String scriptFile = "plugins/Denizen/scripts/" + filename;
        File file = new File(scriptFile);
        if (file.delete()) {
            Logger.Info("Script file " + filename + " deleted.");
        } else {
            Logger.Warning("Failed to delete " + filename);
        }
    }

    public static ArrayList<String> getScriptFolderContents() {
        ArrayList<String> results = null;
        File scriptFolder = new File("plugins/Denizen/scripts");
        results = new ArrayList<String>(Arrays.asList(scriptFolder.list()));
        return results;
    }

    public static void fetchPasteToFile(String pasteID, String filename) {
        String scriptFile = "plugins/Denizen/scripts/" + filename;
        BufferedWriter writer = null;

        String PasteContents = Contents.getPaste(pasteID);
        if (PasteContents != null) {
            try {
                writer = new BufferedWriter(new FileWriter(scriptFile));
                writer.write(PasteContents);
            }
            catch ( IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if ( writer != null) {
                        writer.close();
                        Logger.Info(filename + " updated.");
                    }
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Logger.Warning("Could not fetch pasteID " + pasteID);
        }
    }
}
