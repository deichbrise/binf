package com.week7.test.visitor;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week7.solution.visitor.MyList;
import com.week7.solution.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

import static com.common.test.Assert.*;
/**
 * @author pascalstammer
 * @version 11.06.17.
 */
@Test
public class MyListVisitableTest extends AbstractTest {
    public static void main( String[] args ) {
        new MyListVisitableTest().runAllTests();
    }

    @Test
    public void testVistit() {
        final Visitor<String> visitor = new SimpleStringListVisitor();

        final MyList<String> list = new MyList<>();
        list.add( "string1" );
        list.add( "string2" );
        list.add( "string3" );
        list.add( "string4" );
        list.add( "string5" );
        list.add( "string6" );
        list.add( "string7" );
        list.add( "string8" );
        list.add( "string9" );

        list.accept( visitor );

        List<String> result = ((SimpleStringListVisitor)visitor).getCollectedItems();

        assertTrue( result.size() == 9 );
        assertTrue( result.contains( "string1" ) );
        assertTrue( result.contains( "string2" ) );
        assertTrue( result.contains( "string3" ) );
        assertTrue( result.contains( "string4" ) );
        assertTrue( result.contains( "string5" ) );
        assertTrue( result.contains( "string6" ) );
        assertTrue( result.contains( "string7" ) );
        assertTrue( result.contains( "string8" ) );
        assertTrue( result.contains( "string9" ) );
    }

    @Test
    public void testVistitLimited() {
        final Visitor<String> visitor = new SimpleLimitedStringListVisitor(4);

        final MyList<String> list = new MyList<>();
        list.add( "string1" );
        list.add( "string2" );
        list.add( "string3" );
        list.add( "string4" );
        list.add( "string5" );
        list.add( "string6" );
        list.add( "string7" );
        list.add( "string8" );
        list.add( "string9" );

        list.accept( visitor );

        List<String> result = ((SimpleLimitedStringListVisitor)visitor).getCollectedItems();

        assertTrue( result.size() == 4 );
        assertTrue( result.contains( "string9" ) );
        assertTrue( result.contains( "string8" ) );
        assertTrue( result.contains( "string7" ) );
        assertTrue( result.contains( "string6" ) );
    }
}
