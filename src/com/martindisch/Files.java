package com.martindisch;

import java.io.File;
import java.util.LinkedList;

/**
 * Provides file functionality
 */
public class Files {

    /**
     * Returns an array of files in the current directory
     * that are neither hidden nor read-only
     *
     * @return The files in the current directory
     */
    public static LinkedList<File> listFiles() {
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        LinkedList<File> simpleFiles = new LinkedList<>();
        for (File f : filesList) {
            if (f.isFile() && !f.isHidden() && f.canWrite() && !f.getName().contains(".jar")) {
                simpleFiles.add(f);
            }
        }

        return simpleFiles;
    }

    /**
     * Returns everything before the first opening bracket
     *
     * @param filename The filename
     * @return Everything before the first opening bracket (the video title)
     */
    public static String getTitle(String filename) {
        int openingBracket = filename.lastIndexOf(" (");
        if (openingBracket == -1) {
            return filename;
        }
        return filename.substring(0, openingBracket);
    }
}
