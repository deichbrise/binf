package com.week4.solution.fraction.strategy;

/**
 * @author pascalstammer
 * @version 15.05.17.
 */
public interface CalculationStrategy {

    public String calc(final String operand1, final String operator, final String operand2) throws IllegalArgumentException;
    public boolean isParseable( final String operand1, final String operand2);

    public interface Operators {
        public final static String OPERATOR_MULTIPLICATION = "*";
        public final static String OPERATOR_DIVISION = "/";
        public final static String OPERATOR_ADDITION = "+";
        public final static String OPERATOR_SUBSTRACTION = "-";
    }
}
