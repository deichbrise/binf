package com.week4.solution.fraction;


import com.week4.solution.fraction.manager.CalculationStrategyManager;
import com.week4.solution.fraction.strategy.CalculationStrategy;

import java.util.List;

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

    public void calculate( final String operand1, final String operator, final String operand2) {
        for(CalculationStrategy calculationStrategy : getCalculationStrategies()) {
            if(calculationStrategy.isParseable( operand1, operand2 )) {
                try {
                    final String result = calculationStrategy.calc( operand1, operator, operand2 );
                    System.out.println("Result: " + result);
                } catch ( final Exception e ) {
                    System.out.println("Error: " + e.getMessage());
                    printManual();
                    System.exit( 1 );
                }
                return;
            }
        }
        System.out.println("Cannot calculate.");
        printManual();
    }


    protected void printManual() {
        final StringBuilder builder = new StringBuilder();

        builder.append( "Usage: ").append( System.lineSeparator() );
        builder.append( "\t" ).append( "<Zahl> <Operator> <Zahl>" ).append( System.lineSeparator() );
        builder.append( "\t" ).append( "Mögliche Operatoren: +, -, \"*\", /" ).append( System.lineSeparator() );
        builder.append( "\t" ).append( "Mögliche Formatierung der Zahlen: 1/2, 1, -1/2, -10" ).append( System.lineSeparator() );

        System.err.println(builder.toString());
    }

    protected void printResult(final Fraction fraction) {
        System.out.println("Result: " + fraction);
    }

    public List<CalculationStrategy> getCalculationStrategies() {
        return getCalculationStrategyManager().getCalculationStrategies();
    }

    public CalculationStrategyManager getCalculationStrategyManager() {
        return CalculationStrategyManager.getInstance();
    }
}
