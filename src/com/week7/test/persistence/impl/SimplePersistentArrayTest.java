package com.week7.test.persistence.impl;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week7.solution.persistence.PersistentArray;
import com.week7.solution.persistence.impl.SimplePersistentArray;

import java.io.IOException;

import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class SimplePersistentArrayTest extends AbstractTest {

    public static void main( String[] args ) {
        new SimplePersistentArrayTest().runAllTests();
    }

    @Test
    public void testPut() {
        try {
            final PersistentArray persistentArray = new SimplePersistentArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, "abc.txt" );

            System.out.print( "Size: " + persistentArray.size() );
            assertEquals( 1, persistentArray.get( 0 ) );
            assertEquals( 2, persistentArray.get( 1 ) );
            assertEquals( 3, persistentArray.get( 2 ) );
            assertEquals( 4, persistentArray.get( 3 ) );
            assertEquals( 5, persistentArray.get( 4 ) );
            assertEquals( 6, persistentArray.get( 5 ) );
            assertEquals( 7, persistentArray.get( 6 ) );
            assertEquals( 8, persistentArray.get( 7 ) );
            assertEquals( 9, persistentArray.get( 8 ) );

            ((AutoCloseable)persistentArray).close();

            final PersistentArray persistentArray2 = new SimplePersistentArray( "abc.txt" );

            assertEquals( 1, persistentArray2.get( 0 ) );
            assertEquals( 2, persistentArray2.get( 1 ) );
            assertEquals( 3, persistentArray2.get( 2 ) );
            assertEquals( 4, persistentArray2.get( 3 ) );
            assertEquals( 5, persistentArray2.get( 4 ) );
            assertEquals( 6, persistentArray2.get( 5 ) );
            assertEquals( 7, persistentArray2.get( 6 ) );
            assertEquals( 8, persistentArray2.get( 7 ) );
            assertEquals( 9, persistentArray2.get( 8 ) );
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
