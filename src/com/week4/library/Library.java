package com.week4.library;

/**
 * Created by Julia on 15.05.2017.
 */
public class Library {

    private List itemList;

    public Library() {
        itemList = new List();
    }

    /**
     * Adds new item to existing list of items.
     * No items with identical description are allowed.
     * @param item
     */
    public void addItem(LibraryItem item) {
        if(!checkForOccurrence(item)) itemList.add(item);
    }

    /**
     * If list contains item, it is deleted.
     * @param item
     */
    public void deleteItem(LibraryItem item) {
        if(checkForOccurrence(item)) itemList.delete();
    }

    /**
     * Checks if an item is already in the list and keeps the position of it.
     * Compares items by comparing their descriptions.
     * @param item
     * @return true if item exists in list
     *         false if it does not
     */
    private boolean checkForOccurrence(LibraryItem item) {
        itemList.reset();
        while(!itemList.endpos()) {
            if (((LibraryItem)itemList.elem()).getDescription().equals(item.getDescription())) return true;
            itemList.advance();
        }
        return false;
    }

    /**
     * Goes through whole list and creates a new list with all items containing the searched text.
     * @param text
     * @return list with all found items
     */
    public List search(String text){
        List foundList = new List();
        itemList.reset();
        while(!itemList.endpos()) {
            if(((LibraryItem)itemList.elem()).getDescription().contains(text))
                foundList.add(itemList.elem());
            itemList.advance();
        }
        return foundList;
    }
}
