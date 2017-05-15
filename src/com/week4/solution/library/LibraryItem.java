package com.week4.solution.library;

/**
 * Created by Julia on 15.05.2017.
 */
public abstract class LibraryItem {

    private boolean isBorrowed = false;

    public LibraryItem(){}

    /**
     * Gives information about the (un)availability of the item.
     * @return true if item is borrowed and unavailable
     *         false if item is not yet borrowed
     */
    public boolean isBorrowed() {
        return isBorrowed;
    }

    /**
     * Marks item borrowed or available.
     * @param isBorrowed
     */
    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    /**
     * Is implemented by Book and BluRay
     */
    public abstract String getDescription();
}
