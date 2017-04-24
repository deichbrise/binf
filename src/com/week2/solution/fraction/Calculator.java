package com.week2.solution.fraction;

import com.week1.solution.fraction.Fraction;

/**
 * @author pascalstammer
 * @version 20.04.17.
 */
public class Calculator {

    public final static String OPERATOR_MULTIPLICATION = "*";
    public final static String OPERATOR_DIVISION = "/";
    public final static String OPERATOR_ADDITION = "+";
    public final static String OPERATOR_SUBSTRACTION = "-";

    public static void main( String[] args ) {
        final Calculator calculator = new Calculator();
        if(args.length != 3) {
            calculator.printManual();
        }

        calculator.calculate( args[0], args[1], args[2] );
    }

    public void calculate(final String operand1, final String operator, final String operand2) {
        final ExtendedFraction fraction1 = getFraction( operand1 );
        final ExtendedFraction fraction2 = getFraction( operand2 );

        final Fraction result = performCalc( fraction1, operator, fraction2 );

        printResult( result );
    }

    protected Fraction performCalc(final ExtendedFraction operand1, final String operator, final ExtendedFraction operand2) {

        Fraction result = null;
        switch ( operator ) {
            case OPERATOR_ADDITION:
                result = operand1.add( operand2 );
                break;
            case OPERATOR_SUBSTRACTION:
                result = operand1.substract( operand2 );
                break;
            case OPERATOR_MULTIPLICATION:
                result = operand1.multiply( operand2 );
                break;
            case OPERATOR_DIVISION:
                result = operand1.divide( operand2 );
                break;
            default:
                printManual();
                System.exit( 1 );
        }

        return result;
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
        final StringBuilder builder = new StringBuilder();

        builder.append( "Usage: ").append( System.lineSeparator() );
        builder.append( "\t" ).append( "<Zahl> <Operator> <Zahl>" ).append( System.lineSeparator() );
        builder.append( "\t" ).append( "Mögliche Operatoren: +, -, \"*\", /" ).append( System.lineSeparator() );
        builder.append( "\t" ).append( "Mögliche Formatierung der Zahlen: 1/2, 1, -1/2, -10" ).append( System.lineSeparator() );

        System.err.println(builder.toString());
    }

    public void printResult(final Fraction fraction) {
        System.out.println("Result: " + fraction);
    }
}
