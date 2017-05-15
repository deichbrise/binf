package com.week4.solution.library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pascalstammer
 * @version 14.05.17.
 */
public class Library {
    private List<LibraryItem> inventory = new ArrayList<>();

    public void addItem(final LibraryItem item) {
        inventory.add( item );
    }

    public void deleteItem(final LibraryItem item) {
        inventory.remove( item );
    }

    public List<LibraryItem> search(final String text) {
        return fancySearch( text );
    }

    protected List<LibraryItem> fancySearch(final String text) {
        return inventory.parallelStream()
                .filter( item -> item.getDescription().contains( text ) )
                .collect( Collectors.toList());
    }

    protected List<LibraryItem> boringSearch(final String text) {
        final List<LibraryItem> result = new ArrayList<>();
        for(final LibraryItem libraryItem : inventory) {
            if(libraryItem.getDescription().contains( text )) {
                result.add( libraryItem );
            }
        }
        return result;
    }
}
