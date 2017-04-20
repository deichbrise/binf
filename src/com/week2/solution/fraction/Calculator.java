package com.week2.solution.fraction;

import com.week1.solution.fraction.Fraction;

/**
 * @author pascalstammer
 * @version 20.04.17.
 */
public class Calculator {
    public static void main( String[] args ) {
        if(args.length != 3) {
            System.err.println("Format: Operand + Operator + Operand");
        }

        final Calculator calculator = new Calculator();
        calculator.calculate( args[0], args[1], args[2] );
    }

    public void calculate(final String operand1, final String operator, final String operand2) {
        final ExtendedFraction fraction1 = getFraction( operand1 );
        final ExtendedFraction fraction2 = getFraction( operand2 );

        switch ( operator ) {
            case "+":
                printResult( fraction1.add( fraction2 ) );
                break;
            case "-":
                printResult( fraction1.substract( fraction2 ) );
                break;
            case "*":
                printResult( fraction1.multiply( fraction2 ) );
                break;
            case "/":
                printResult( fraction1.divide( fraction2 ) );
                break;
            default:
                printManual();
                System.exit( 1 );
        }
    }

    public ExtendedFraction getFraction( final String s) {
        ExtendedFraction fraction = null;
        try {
            fraction = ExtendedFraction.parseFraction( s );
        } catch ( final Exception e ) {
            printManual();
            System.exit( 1 );
        }
        return fraction;
    }

    public void printManual() {

    }

    public void printResult(final Fraction fraction) {
        System.out.println("Result: " + fraction);
    }
}
