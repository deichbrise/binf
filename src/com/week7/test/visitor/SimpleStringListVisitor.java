package com.week7.test.visitor;

import com.week7.solution.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class SimpleStringListVisitor implements Visitor<String> {

    private List<String> collectedItems = new ArrayList<>();

    /**
     * Visits given String and adds it to its ArrayList
     * @param o
     *           the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return true at all times
     */
    @Override
    public boolean visit( final String o ) {
        collectedItems.add( o );
        return true;
    }

    /**
     * @return ArrayList with all visited Strings
     */
    public List<String> getCollectedItems() {
        return collectedItems;
    }

    /**
     * Sets its ArrayList with given List
     * @param collectedItems
     */
    public void setCollectedItems( final List<String> collectedItems ) {
        this.collectedItems = collectedItems;
    }
}
