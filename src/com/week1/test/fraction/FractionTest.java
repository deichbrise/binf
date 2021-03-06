package com.week1.test.fraction;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week1.solution.fraction.Fraction;
import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
@Test
public class FractionTest extends AbstractTest {

    @Test
    public void testMultiplyWithInteger() {
        final Fraction fraction = new Fraction(2, 4);

        final Fraction actual = fraction.multiply( 2 );
        final Fraction expected = new Fraction( 1 );

        assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyWithFraction() {
        final Fraction fraction = new Fraction( 1, 2 );
        final Fraction factor = new Fraction( 1, 2 );

        final Fraction actual = fraction.multiply( factor );
        final Fraction expected = new Fraction( 1, 4 );

        assertEquals( expected, actual );
    }

    @Test
    public void testMultiplyWithMultiplyFraction() {
        final Fraction fraction = new Fraction( 1, 2 );
        final Fraction factor1 = new Fraction( 1, 2 );
        final Fraction factor2 = new Fraction( 1, 2 );

        final Fraction actual = fraction.multiply( factor1, factor2 );
        final Fraction expected = new Fraction( 1, 8 );

        assertEquals( expected, actual );
    }

    @Test
    public void testSimpleConstructor() {
        final Fraction fraction = new Fraction( 2 );

        assertEquals( 1, fraction.getDenominator() );
        assertEquals( 2, fraction.getNumerator() );
    }

    @Test
    public void testMoreComplexConstructor() {
        final Fraction fraction = new Fraction( 2, 4 );

        assertEquals( 1, fraction.getNumerator() );
        assertEquals( 2, fraction.getDenominator() );
    }

    @Test
    public void testNegativeMultiplication() {
        final Fraction fraction1 = new Fraction( -2, 7 );
        final Fraction fraction2 = new Fraction( 2, 7 );

        final Fraction actual = fraction1.multiply( fraction2 );

        assertEquals( new Fraction( -4, 49 ), actual );
    }

    @Test
    public void testToString() {
        final Fraction fraction = new Fraction( 1, 4 );

        final String actual = fraction.toString();
        final String expected = "1/4";

        assertEquals( expected, actual );
    }

    @Test
    public void testDivide() {
        final Fraction fraction = new Fraction( 1, 4 );
        final Fraction divider = new Fraction( 1, 4 );

        final Fraction actual = fraction.divide( divider );
        final Fraction expected = new Fraction( 1 );

        assertEquals( expected, actual );
    }
}
