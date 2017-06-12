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

    @Override
    public boolean visit( final String o ) {
        collectedItems.add( o );
        return collectedItems.size() < limit;
    }

    public List<String> getCollectedItems() {
        return collectedItems;
    }

    public void setCollectedItems( final List<String> collectedItems ) {
        this.collectedItems = collectedItems;
    }
}
