package com.martindisch;

import java.io.File;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<File> files = Files.listFiles();
        for (File f : files) {
            System.out.println(Youtube.getPublishingDate(Files.getTitle(f.getName())) + " " + f.getName());
        }
    }
}
