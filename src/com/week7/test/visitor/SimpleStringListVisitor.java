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
    @Override
    public boolean visit( final String o ) {
        collectedItems.add( o );
        return true;
    }

    public List<String> getCollectedItems() {
        return collectedItems;
    }

    public void setCollectedItems( final List<String> collectedItems ) {
        this.collectedItems = collectedItems;
    }
}
