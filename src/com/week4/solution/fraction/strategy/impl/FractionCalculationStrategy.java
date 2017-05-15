package com.week4.solution.fraction.strategy.impl;

import com.week4.solution.condition.Conditions;
import com.week4.solution.fraction.Fraction;
import com.week4.solution.fraction.manager.CalculationStrategyManager;
import com.week4.solution.fraction.strategy.CalculationStrategy;

/**
 * Verarbeitet zwei Fractions
 *
 * @author pascalstammer
 * @version 15.05.17.
 */
public class FractionCalculationStrategy implements CalculationStrategy {

    @Override
    public String calc( final String operand1, final String operator, final String operand2 ) throws IllegalArgumentException {
        final Fraction fraction1 = getFraction( operand1 );
        final Fraction fraction2 = getFraction( operand2 );

        return performCalc( fraction1, operator, fraction2 ).toString();
    }

    protected Number performCalc(final Fraction operand1, final String operator, final Fraction operand2) throws IllegalArgumentException{

        Number result;
        switch ( operator ) {
            case Operators.OPERATOR_ADDITION:
                result = operand1.add( operand2 );
                break;
            case Operators.OPERATOR_SUBSTRACTION:
                result = operand1.substract( operand2 ) ;
                break;
            case Operators.OPERATOR_MULTIPLICATION:
                result = operand1.multiply( operand2 );
                break;
            case Operators.OPERATOR_DIVISION:
                result = operand1.divide( operand2 );
                break;
            default:
                throw new IllegalArgumentException( "Operator does not match any of +, -, *, /" );
        }

        return result;
    }

    @Override
    public boolean isParseable( final String operand1, final String operand2 ) {
        return Conditions.allFractions( operand1, operand2 );
    }

    public Fraction getFraction( final String s) throws IllegalArgumentException {
        Fraction fraction = null;
        try {
            fraction = Fraction.parseFraction( s );
        } catch ( final Exception e ) {
            throw new IllegalArgumentException( "Cannot convert string to fraction." );
        }
        return fraction;
    }
}
