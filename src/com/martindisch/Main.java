package com.martindisch;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // get files
        System.out.println();
        LinkedList<File> files = Files.listFiles();
        if (files.size() < 0) {
            System.out.println("No eligible files in this folder. Press enter to exit...");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }

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
                for (File f : files) {
                    System.out.println(Youtube.getPublishingDate(Files.getTitle(f.getName())) + " " + f.getName());
                }
                System.out.println();
            } else if (input.contentEquals("2")) {
                // rename
                int successes = 0;
                for (File f : files) {
                    File newName = new File(Youtube.getPublishingDate(Files.getTitle(f.getName())) + " " + f.getName());
                    if (f.renameTo(newName)) {
                        System.out.println(Youtube.getPublishingDate(Files.getTitle(f.getName())) + " " + f.getName());
                        successes++;
                    } else {
                        System.out.println("Could not rename " + f.getName());
                    }
                }
                System.out.println();
                System.out.println(successes + " files successfully renamed. Press enter to exit...");
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
