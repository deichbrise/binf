package com.week4.solution.library;

/**
 * Created by Julia on 15.05.2017.
 */
public class Book extends LibraryItem {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Returns the book's title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns name of book's author.
     * @return author
     */
    public  String getAuthor() {
        return author;
    }

    /**
     * Returns information about the book's title and author.
     * @return type: book, title and author
     */
    public String getDescription() {
        return "Book: " + title + ", " + author;
    }
}
