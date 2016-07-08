package com.martindisch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 * Provides file functionality
 */
public class Files {

    /**
     * Shows a preview of the new names
     *
     * @param files    The files to be previewed
     * @param addendum The phrase to append to the title
     */
    public static void previewNames(LinkedList<File> files, String addendum) {
        for (File f : files) {
            System.out.println(Youtube.getPublishingDate(Files.getTitle(f.getName(), addendum))[0] + " - " + f.getName());
        }
    }

    /**
     * Renames the given files and saves the links to a file
     *
     * @param files    The files to be renamed
     * @param addendum The phrase to append to the title
     */
    public static void renameFiles(LinkedList<File> files, String addendum) {
        int successes = 0;
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter("date_url.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (File f : files) {
            String[] dateUrl = Youtube.getPublishingDate(Files.getTitle(f.getName(), addendum));
            String filename = dateUrl[0] + " - " + f.getName();
            File newName = new File(filename);
            if (f.renameTo(newName)) {
                System.out.println(filename);
                successes++;
                pw.write(dateUrl[0] + " - " + dateUrl[1] + "\n");
                pw.write(Files.getTitle(f.getName(), "") + "\n");
                pw.write(dateUrl[2] + "\n\n");
            } else {
                System.out.println("Could not rename " + f.getName());
                pw.write("Unable to rename " + dateUrl[1] + "\n");
            }
        }
        pw.close();
        System.out.println();
        System.out.println(successes + " files successfully renamed.");
    }

    /**
     * Returns an array of files in the current directory
     * that are neither hidden nor read-only
     *
     * @return The files in the current directory
     */
    public static LinkedList<File> getFiles() {
        File curDir = new File(".");
        File[] filesList = curDir.listFiles();
        LinkedList<File> simpleFiles = new LinkedList<>();
        for (File f : filesList) {
            if (f.isFile() && !f.isHidden() && f.canWrite() && !f.getName().contains(".jar") && !f.getName().contains(".bat")) {
                simpleFiles.add(f);
            }
        }

        return simpleFiles;
    }

    /**
     * Returns everything before the first opening bracket
     *
     * @param filename The filename
     * @param addendum The phrase to append to the title
     * @return Everything before the first opening bracket (the video title)
     */
    public static String getTitle(String filename, String addendum) {
        int openingBracket = filename.lastIndexOf(" (");
        if (openingBracket == -1) {
            return filename;
        }
        return filename.substring(0, openingBracket) + " " + addendum;
    }
}
