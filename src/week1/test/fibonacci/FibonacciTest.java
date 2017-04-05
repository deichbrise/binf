package week1.test.fibonacci;

import common.logger.LogManager;
import common.logger.Logger;
import week1.solution.fibonacci.Fibonacci;
import week1.solution.fibonacci.FibonacciIterable;
import week1.solution.fibonacci.FibonacciPrinter;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public abstract class FibonacciTest {

    private static Logger log = LogManager.getLogger( FibonacciTest.class );

    public static void main( String[] args ) {
        test();
    }

    public static void test() {
        FibonacciPrinter fibonacciPrinter = new FibonacciPrinter();
        fibonacciPrinter.print( new Fibonacci( 100 ) );
    }
}
