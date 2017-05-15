package com.week4.library;

/**
 * Created by Julia on 15.05.2017.
 */
public class BluRay extends LibraryItem {
    private String title;
    private String director;

    public BluRay(String title, String director) {
        this.title = title;
        this.director = director;
    }

    /**
     * Returns BluRay's title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns name of BluRay's director.
     * @return director
     */
    public  String getDirector() {
        return director;
    }

    /**
     * Returns information about the BluRay.
     * @return type: BluRay, title and director
     */
    public String getDescription() {
        return "BluRay: " + title + ", " + director;
    }
}
