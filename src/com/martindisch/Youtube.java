package com.martindisch;

/**
 * Provides Youtube functionality
 */
public class Youtube {

    /**
     * Returns the url of the video found by searching for the title
     *
     * @param title The video's title
     * @return The url of the first result found for the given title
     */
    public static String getVideoUrl(String title) {
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
        int index = page.indexOf("href=\"/watch?v=");
        return "https://www.youtube.com" + page.substring(index + 6, index + 27);
    }

    /**
     * Returns the url for searching for the video
     *
     * @param title The video's title
     * @return The search url
     */
    private static String getSearchUrl(String title) {
        return "https://www.youtube.com/results?search_query=" + title.replace(" ", "+");
    }
}
