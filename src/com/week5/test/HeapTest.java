package com.week5.test;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week5.solution.heap.ArrayHeap;
import com.week5.solution.heap.Heap;

/**
 * @author pascalstammer
 * @version 20.05.17.
 */
@Test
public class HeapTest extends AbstractTest {

    public static void main( String[] args ) {
        final HeapTest instance = new HeapTest();
        instance.runAllTests();
    }

    @Test
    public void testExtractMin() {
        final Heap<Integer> heap = new ArrayHeap<>(  );
        heap.insert( 10 );
        heap.insert( 9 );
        heap.insert( 8 );
        heap.insert( 7 );
        heap.insert( 6 );
        heap.insert( 5 );
        heap.insert( 4 );
        heap.insert( 3 );
        heap.insert( 2 );
        heap.insert( 1 );
        heap.insert( 1 );
        heap.insert( 100 );

        Assert.assertEquals( 1, heap.extractMin() );
    }

    @Test
    public void testDeleteFirst() {
        final Heap<Integer> heap = new ArrayHeap<>(  );
        heap.insert( 10 );
        heap.insert( 9 );
        heap.insert( 8 );
        heap.insert( 7 );
        heap.insert( 6 );
        heap.insert( 5 );
        heap.insert( 4 );
        heap.insert( 3 );
        heap.insert( 2 );
        heap.insert( 1 );
        heap.insert( 100 );

        Assert.assertEquals( 1, heap.extractMin() );

        Assert.assertEquals( 1, heap.deleteFirst() );

        Assert.assertEquals( 2, heap.extractMin() );

    }

    @Test
    public void testEmpty() {
        final Heap<Integer> heap = new ArrayHeap<>();

        Assert.assertTrue( heap.empty() );

        heap.insert( 1 );

        Assert.assertFalse( heap.empty() );

        heap.deleteFirst();

        Assert.assertTrue( heap.empty() );
    }

    @Test(shouldThrow = {IllegalStateException.class})
    public void testConstructorShouldThrowExceptionIfNotComparableAndNoComperatorGiven() {
        final Heap<NotCompareableByDefault> heap = new ArrayHeap<>();
        heap.insert( new NotCompareableByDefault() );
    }

    @Test
    public void testConstructorMustNotThrowExceptionIfCompareable() {
        final Heap<ComparableClass> heap = new ArrayHeap<ComparableClass>();
        heap.insert( new ComparableClass() );
    }


    /*
        Test Classes
     */
    private class NotCompareableByDefault {

    }

    private class ComparableClass implements Comparable<ComparableClass> {
        @Override
        public int compareTo( final ComparableClass o ) {
            return 0;
        }
    }
}
