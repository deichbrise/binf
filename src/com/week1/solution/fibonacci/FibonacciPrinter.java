package com.week1.solution.fibonacci;

import com.common.Printer;
import com.common.util.table.AsciiTableObsolete;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class FibonacciPrinter implements Printer<Fibonacci> {

    private static FibonacciPrinter instance;

    private FibonacciPrinter() {}

    public static FibonacciPrinter getInstance() {
        if(instance == null) {
            instance = new FibonacciPrinter();
        }

        return instance;
    }

    @Override
    public void print( final Fibonacci o ) {
        final AsciiTableObsolete table = new AsciiTableObsolete();
        table.addColumn( "n" );
        table.addColumn( "f(n)" );
        int index = 0;
        while(o.hasNext()) {
            int n = index++;
            table.newRow().addValue( Integer.toString( n ) ).addValue( o.next().toString() );
        }
        table.print();
    }
}
