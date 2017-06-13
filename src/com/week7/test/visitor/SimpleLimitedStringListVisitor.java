package com.week7.test.visitor;

import com.week7.solution.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class SimpleLimitedStringListVisitor implements Visitor<String> {
    private Integer limit;
    private List<String> collectedItems = new ArrayList<>();

    public SimpleLimitedStringListVisitor( final Integer limit ) {
        this.limit = limit;
    }

    /**
     * Visits given String and adds it to its ArrayList
     * @param o
     *           the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return true if size of ArrayList is still below maximal size
     *         false if ArrayList has already reached maximal size
     */
    @Override
    public boolean visit( final String o ) {
        collectedItems.add( o );
        return collectedItems.size() < limit;
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
