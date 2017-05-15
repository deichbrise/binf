package com.week4.solution.library;

/**
 * @author pascalstammer
 * @version 14.05.17.
 */
public abstract class LibraryItem {
    private boolean isBorrowed;

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed( final boolean borrowed ) {
        isBorrowed = borrowed;
    }

    public abstract String getDescription();


}
