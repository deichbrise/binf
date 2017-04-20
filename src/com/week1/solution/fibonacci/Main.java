package com.week1.solution.fibonacci;

/**
 * @author pascalstammer
 * @version 06.04.17.
 */
public class Main {
    public static void main( String[] args ) {
        final Integer n = Integer.parseInt( args[0] );
        final Fibonacci fibonacci = new Fibonacci( n );
        final FibonacciPrinter fibonacciPrinter = FibonacciPrinter.getInstance();

        fibonacciPrinter.print( fibonacci );
    }
}
