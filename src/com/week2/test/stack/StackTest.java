package com.week2.test.stack;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week2.solution.stack.StringStack;

/**
 * Created by Julia on 20.04.2017.
 */
@Test
public class StackTest extends AbstractTest {

    public static void testCopyConstructor( StringStack tester ) {
        StringStack copy = new StringStack (tester);
        System.out.print("Kopie und Original identisch?");
        if (tester == copy) {
            System.out.println(" --> ja");
        } else { System.out.println(" --> nein");}
        System.out.print("oberster Eintrag identisch?");
        if (tester.empty()) {
            System.out.println(" -->keine Eintraege");
        } else if (tester.peek() == copy.peek()) {
            System.out.println(" --> ja");
        } else {
            System.out.println(" --> nein");
        }

    }

    @Test
    public void testAll() {

        StringStack keller = new StringStack();
        System.out.println( "Leerer Keller:" );
        testCopyConstructor( keller );

        System.out.println( "Keller mit Eintraegen:" );
        keller.push( "Ein" );
        keller.push( "kleiner" );
        keller.push( "Test" );
        testCopyConstructor( keller );

    }
}
