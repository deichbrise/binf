package com.week4.solution.fraction.strategy;

/**
 * Uebernimmt das parsen und das berechnen sowie die Konvertierung des Ergebnisses in einen String
 *
 * @author pascalstammer
 * @version 15.05.17.
 */
public interface CalculationStrategy {

    /**
     * Berechnet das Ergebnis und wandelt das Ergebnis in einen String um
     *
     * @param operand1 Erster Operand
     * @param operator Operator
     * @param operand2 Zweiter Operand
     * @return Das in einen String konvertierte Ergebnis
     * @throws IllegalArgumentException wenn sich das Ergebnis aufgrund eines nicht parsebaren Operanten oder falschen
     * Operator berechnen lässt
     */
    public String calc(final String operand1, final String operator, final String operand2) throws IllegalArgumentException;

    /**
     * Prueft, ob sich die Operatoren durch diese Strategy verarbeiten lassen
     *
     * @param operand1 Operant 1
     * @param operand2 Operant 2
     * @return true wenn sich die Operanten verarbeiten lassen
     */
    public boolean isParseable( final String operand1, final String operand2);

    public interface Operators {
        public final static String OPERATOR_MULTIPLICATION = "*";
        public final static String OPERATOR_DIVISION = "/";
        public final static String OPERATOR_ADDITION = "+";
        public final static String OPERATOR_SUBSTRACTION = "-";
    }
}
