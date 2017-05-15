package com.week4.test.fraction;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.sun.javafx.runtime.async.AbstractAsyncOperation;
import com.week4.solution.fraction.Calculator;

/**
 * @author pascalstammer
 * @version 15.05.17.
 */
@Test
public class CalculatorTest extends AbstractTest {

    public static void main( String[] args ) {
        final CalculatorTest calculatorTest = new CalculatorTest();
        calculatorTest.runAllTests();
    }

    @Test
    public void testCalculate() {
        final Calculator calculator = new Calculator();

        calculator.calculate( "1/2", "+", "1/2" );
        calculator.calculate( "1", "+", "1" );
        calculator.calculate( "1/2", "+", "1.0" );
        calculator.calculate( "1/2", "+", "-1.0" );
        calculator.calculate( "-1/2", "+", "-1.0" );
        calculator.calculate( "1/2", "*", "-1.0" );
        calculator.calculate( "1/2", "/", "-1.0" );
        calculator.calculate( "1/2", "-", "-1.0" );
    }
}
