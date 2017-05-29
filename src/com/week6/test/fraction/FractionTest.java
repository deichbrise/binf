package com.week6.test.fraction;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week6.solution.fraction.Fraction;

import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 27.05.17.
 */
@Test
public class FractionTest extends AbstractTest {

    public static void main( String[] args ) {
        final FractionTest instance = new FractionTest();

        instance.runAllTests();
    }

    @Test
    public void testConstruction() {
        final Fraction fraction1 = Fraction.getInstance( 1 );
        final Fraction fraction2 = Fraction.getInstance( 1 );
        final Fraction fraction3 = Fraction.getInstance( 2 );

        assertTrue( fraction1 == fraction2 );
        assertFalse( fraction1 == fraction3 );
    }

    @Test
    public void testSameReferenceIfInstantiatedWithParseFraction() {
        final Fraction fraction1 = Fraction.parseFraction( "1/2" );
        final Fraction fraction2 = Fraction.parseFraction( "2/4" );
        final Fraction fraction3 = Fraction.parseFraction( "-1/2" );

        assertTrue( fraction1 == fraction2 );
        assertFalse( fraction1 == fraction3 );
    }

    @Test
    public void testSameReferenceIfMultiply() {
        final Fraction fraction1 = Fraction.getInstance( 1, 2 );
        final Fraction fraction2 = Fraction.getInstance( 1, 2 );
        final Fraction actualResult = fraction1.multiply( fraction2 );
        final Fraction expectedResult = Fraction.getInstance( 1, 4 );

        assertTrue( expectedResult == actualResult );
        assertEquals( 1, actualResult.getNumerator() );
        assertEquals( 4, actualResult.getDenominator() );
    }

    @Test
    public void testSameReferenceIfAdded() {
        final Fraction fraction1 = Fraction.getInstance( 1, 4 );
        final Fraction fraction2 = Fraction.getInstance( 1, 4 );
        final Fraction actualResult = fraction1.add( fraction2 );
        final Fraction expectedResult = Fraction.getInstance( 1, 2 );

        assertTrue( expectedResult == actualResult );
        assertEquals( 1, actualResult.getNumerator() );
        assertEquals( 2, actualResult.getDenominator() );
    }
}
