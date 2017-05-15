package com.week4.solution.library;

/**
 * @author pascalstammer
 * @version 14.05.17.
 */
public class BluRay extends LibraryItem {
    private String title;
    private String director;

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String getDescription() {
        return getTitle() + " - " + getDirector();
    }
}
