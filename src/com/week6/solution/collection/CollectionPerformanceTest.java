package com.week6.solution.collection;

import com.common.util.table.AsciiTableObsolete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author pascalstammer
 * @version 26.05.17.
 */
public class CollectionPerformanceTest {

    public static void main( String[] args ) {
        final CollectionPerformanceTest collectionPerformanceTest = new CollectionPerformanceTest();
        final Collection[] testCollections = new Collection[] {new ArrayList<>(), new LinkedList<>(), new HashSet<>()};

        collectionPerformanceTest.warmUp( testCollections );
        collectionPerformanceTest.testRun( testCollections );
    }

    public void warmUp(final Collection[] testCollections) {
        for(Collection testCollection : testCollections) {
            testAdd( testCollection );
            testRemove( testCollection );
            testContains( testCollection );
        }
    }

    public void testRun(final Collection[] testCollections) {
        for(Collection testCollection : testCollections) {
            testAdd( testCollection );
            testRemove( testCollection );
            testContains( testCollection );
        }
    }

    public Long testAdd(final Collection testCollection) {
        testCollection.clear();
        return null;
    }

    public Long testRemove(final Collection testCollection) {
        testCollection.clear();
        return null;
    }

    public Long testContains(final Collection testCollection) {
        testCollection.clear();
        return null;
    }

    protected class NonHashedStringWrapper {
        private String content;

        public NonHashedStringWrapper( final String content ) {
            this.content = content;
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;

            final NonHashedStringWrapper that = (NonHashedStringWrapper) o;

            return !(content != null ? !content.equals( that.content ) : that.content != null);

        }
    }

    protected class HashedStringWrapper {
        private String content;

        public HashedStringWrapper( final String content ) {
            this.content = content;
        }

        @Override
        public boolean equals( final Object o ) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;

            final HashedStringWrapper that = (HashedStringWrapper) o;

            return !(content != null ? !content.equals( that.content ) : that.content != null);

        }

        @Override
        public int hashCode() {
            return content != null ? content.hashCode() : 0;
        }
    }
}
