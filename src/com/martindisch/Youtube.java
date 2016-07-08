package com.martindisch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Provides Youtube functionality
 */
public class Youtube {

    /**
     * Returns the publishing date of the first video found by the title
     *
     * @param title The video's title
     * @return The publishing date formatted as yy_mm_dd
     */
    public static String getPublishingDate(String title) {
        StringBuffer page = Downloader.getHTML(getVideoUrl(title));
        int startIndex = page.indexOf("Published on ") + 13;
        int endIndex = page.indexOf("</strong></div><div id=\"watch-description-text\" class=\"\">");
        String canonicalDate = page.substring(startIndex, endIndex);
        String date = formatDate(canonicalDate);

        return date;
    }

    /**
     * Converts the Youtube date (e.g. July 7, 2016) to yy_mm_dd
     *
     * @param canonicalDate The Youtube date
     * @return The date formatted as yy_mm_dd
     */
    private static String formatDate(String canonicalDate) {
        String parts[] = canonicalDate.split(" ");

        // remove comma
        parts[1] = parts[1].replace(",", "");

        // year
        String year = parts[2].substring(2);

        // month
        Date date = null;
        try {
            date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(parts[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int iMonth = cal.get(Calendar.MONTH) + 1;
        String month = String.format("%02d", iMonth);

        // day
        String day = String.format("%02d", Integer.parseInt(parts[1]));

        return year + "_" + month + "_" + day;
    }

    /**
     * Returns the url of the first video found by searching for the title
     *
     * @param title The video's title
     * @return The url of the first result found for the given title
     */
    private static String getVideoUrl(String title) {
        String searchUrl = getSearchUrl(title);
        StringBuffer page = Downloader.getHTML(searchUrl);
        String url = getFirstVideoUrl(page);

        return url;
    }

    /**
     * Returns the url of the first video on a search page
     *
     * @param page The page source
     * @return The url of the first video
     */
    private static String getFirstVideoUrl(StringBuffer page) {
        int startIndex = page.indexOf("href=\"/watch?v=") + 6;
        int endIndex = startIndex + 21;

        return "https://www.youtube.com" + page.substring(startIndex, endIndex);
    }

    /**
     * Returns the url for searching for the video
     *
     * @param title The video's title
     * @return The search url
     */
    private static String getSearchUrl(String title) {
        return "https://www.youtube.com/results?search_query=" + title.replace("&", "").replace(" ", "+");
    }
}
