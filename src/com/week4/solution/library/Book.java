package com.week4.solution.library;

/**
 * @author pascalstammer
 * @version 14.05.17.
 */
public class Book extends LibraryItem {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return getTitle() + " - " + getAuthor();
    }
}
