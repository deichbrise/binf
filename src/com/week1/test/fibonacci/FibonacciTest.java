package com.week1.test.fibonacci;

import com.common.Assert;
import com.common.logger.LogManager;
import com.common.logger.Logger;
import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week1.solution.fibonacci.Fibonacci;
import com.week1.solution.fibonacci.FibonacciPrinter;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class FibonacciTest extends AbstractTest {

    private static Logger log = LogManager.getLogger( FibonacciTest.class );

    public static void main( String[] args ) {
        run( FibonacciTest.class );
    }

    @Test
    public void testFibonacci() {

        final Fibonacci fibonacci = new Fibonacci( 10 );

        final Integer[] actual = Fibonacci.toArray( fibonacci );
        final Integer[] expected = new Integer[]{0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

        Assert.assertArrayEqual(expected, actual);
    }

    @Test
    public void testPrintFibonacci() {
        final Fibonacci fibonacci = new Fibonacci( 10 );
        final FibonacciPrinter fibonacciPrinter = FibonacciPrinter.getInstance();

        fibonacciPrinter.print( fibonacci );
    }
}
