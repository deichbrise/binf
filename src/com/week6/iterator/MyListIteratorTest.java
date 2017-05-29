package com.week6.iterator;

import com.common.test.AbstractTest;
import com.common.test.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 28.05.17.
 */
@Test
public class MyListIteratorTest extends AbstractTest {

    public static void main( String[] args ) {
        MyListIteratorTest instance = new MyListIteratorTest();
        instance.runAllTests();
    }

    @Test
    public void testNext() {
        final MyList<String> myList = new MyList<>();
        myList.add( "String 1" );
        myList.add( "String 2" );
        myList.add( "String 3" );
        myList.add( "String 4" );
        myList.add( "String 5" );
        myList.add( "String 6" );

        final String expected = myList.elem();

        final Iterator<String> iterator = myList.iterator();

        final String actual = iterator.next();

        assertEquals( expected, actual );
    }

    @Test
    public void testHasNext() {
        final MyList<String> myList1 = new MyList<>();
        myList1.add( "String 1" );
        myList1.add( "String 2" );
        myList1.add( "String 3" );
        myList1.add( "String 4" );
        myList1.add( "String 5" );
        myList1.add( "String 6" );

        final MyList<String> myList2 = new MyList<>();

        assertTrue( myList1.iterator().hasNext() );
        assertFalse( myList2.iterator().hasNext() );
    }

    @Test
    public void testIteration() {
        final MyList<String> myList = new MyList<>();
        myList.add( "String 1" );
        myList.add( "String 2" );
        myList.add( "String 3" );
        myList.add( "String 4" );
        myList.add( "String 5" );
        myList.add( "String 6" );

        final String[] expected = new String[] {"String 6", "String 5", "String 4", "String 3", "String 2", "String 1"};
        final String[] acual = new String[expected.length];

        final Iterator<String> iterator = myList.iterator();

        int index = 0;
        while ( iterator.hasNext() ) {
            acual[index++] = iterator.next();
        }

        assertArrayEqual( expected, acual );
    }

    @Test(shouldThrow = {ConcurrentModificationException.class})
    public void testConcurrentModificationException() {
        final MyList<String> myList = new MyList<>();
        myList.add( "String 1" );
        myList.add( "String 2" );
        myList.add( "String 3" );
        myList.add( "String 4" );
        myList.add( "String 5" );
        myList.add( "String 6" );

        final Iterator<String> iter = myList.iterator();

        myList.delete();
        iter.next();
    }

    /**@Test(shouldThrow = {IllegalStateException.class})
    public void testIllegalStateException() {
        final MyList<String> myList = new MyList<>();
        myList.add( "String 1" );
        myList.add( "String 2" );
        myList.add( "String 3" );
        myList.add( "String 4" );
        myList.add( "String 5" );
        myList.add( "String 6" );

        final Iterator<String> iter = myList.iterator();

        iter.next();

        iter.remove();
        iter.remove();
    }*/
}
