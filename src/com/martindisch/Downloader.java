package com.martindisch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Responsible for downloading a website
 */
public class Downloader {

    /**
     * Downloads the source of the page at the given url
     *
     * @param stringUrl The url to download from
     * @return The content of the page at the url
     */
    public static StringBuffer getHTML(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        StringBuffer buffer = null;
        try {
            InputStream is = url.openStream();
            int ptr = 0;
            buffer = new StringBuffer();
            while ((ptr = is.read()) != -1) {
                buffer.append((char) ptr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }
}
