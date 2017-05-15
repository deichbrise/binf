package com.week4.solution.fraction.strategy.impl;

import com.sun.javafx.binding.DoubleConstant;
import com.week4.solution.condition.Conditions;
import com.week4.solution.fraction.Fraction;
import com.week4.solution.fraction.manager.CalculationStrategyManager;
import com.week4.solution.fraction.strategy.CalculationStrategy;

/**
 * Verarbeitet alle anderen Nummern
 *
 * @author pascalstammer
 * @version 15.05.17.
 */
public class FloatingPointCalculationStrategy implements CalculationStrategy {


    @Override
    public String calc( final String operand1, final String operator, final String operand2 ) throws IllegalArgumentException {
        final Number number1 = toNumber( operand1 );
        final Number number2 = toNumber( operand2 );
        return performCalc( number1, operator, number2 ).toString();
    }

    @Override
    public boolean isParseable( final String operand1, final String operand2 ) {
        return !Conditions.allFractions( operand1, operand2 );
    }

    protected Number toNumber(final String s) {
        if(Conditions.isFraction( s )) {
            return Fraction.parseFraction( s );
        } else {
            return Double.valueOf( s );
        }
    }

    protected Number performCalc(final Number operand1, final String operator, final Number operand2) throws IllegalArgumentException{

        double result;
        switch ( operator ) {
            case Operators.OPERATOR_ADDITION:
                result = operand1.doubleValue() + operand2.doubleValue();
                break;
            case Operators.OPERATOR_SUBSTRACTION:
                result = operand1.doubleValue() - operand2.doubleValue() ;
                break;
            case Operators.OPERATOR_MULTIPLICATION:
                result = operand1.doubleValue() * operand2.doubleValue();
                break;
            case Operators.OPERATOR_DIVISION:
                result = operand1.doubleValue() / operand2.doubleValue();
                break;
            default:
                throw new IllegalArgumentException( "Operator does not match any of +, -, *, /" );
        }

        return result;
    }
}
