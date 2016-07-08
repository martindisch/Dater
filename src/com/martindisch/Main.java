package com.martindisch;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // get files
        System.out.println();
        LinkedList<File> files = Files.getFiles();
        if (files.size() < 0) {
            System.out.println("No eligible files in this folder. Press enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

        System.out.print("Type an additional phrase to append to the video titles when searching: ");
        String addendum = System.console().readLine();
        System.out.println();

        // main command loop
        while (true) {
            System.out.println("Found " + files.size() + " eligible files. Available commands:");
            System.out.println("1:  Preview new names");
            System.out.println("2:  Rename files");
            System.out.println("0:  Exit");
            System.out.print("Type your command: ");
            String input = System.console().readLine();
            System.out.println();
            if (input.contentEquals("1")) {
                // preview names
                Files.previewNames(files, addendum);
                System.out.println();
            } else if (input.contentEquals("2")) {
                // rename
                Files.renameFiles(files, addendum);
                System.out.println("Press enter to exit...");
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            } else if (input.contentEquals("0")) {
                System.exit(0);
            }
        }
    }
}
