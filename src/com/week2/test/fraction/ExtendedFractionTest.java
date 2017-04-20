package com.week2.test.fraction;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;
import com.week1.solution.fraction.Fraction;
import com.week2.solution.fraction.ExtendedFraction;

/**
 * @author pascalstammer
 * @version 20.04.17.
 */
@Test
public class ExtendedFractionTest extends AbstractTest {

    public static void main( String[] args ) {
        final ExtendedFractionTest test = new ExtendedFractionTest();

        test.testSubstract();
    }
    @Test
    public void testAdd() {
        final ExtendedFraction fraction1 = new ExtendedFraction( 1, 2 );
        final ExtendedFraction fraction2 = new ExtendedFraction( 1, 2 );

        final ExtendedFraction actual1 = fraction1.add( fraction2 );

        Assert.assertEquals( new Fraction(1), actual1 );

        final ExtendedFraction fraction3 = new ExtendedFraction( 1 );
        final ExtendedFraction fraction4 = new ExtendedFraction( -1, 2 );

        final ExtendedFraction actual2 = fraction3.add( fraction4 );

        Assert.assertEquals( new Fraction(1, 2), actual2 );
    }

    @Test
    public void testSubstract() {
        final ExtendedFraction fraction1 = new ExtendedFraction( 1 );
        final ExtendedFraction fraction2 = new ExtendedFraction( 1, 2 );

        final ExtendedFraction actual1 = fraction1.substract( fraction2 );

        Assert.assertEquals( new Fraction(1, 2), actual1 );

        final ExtendedFraction fraction3 = new ExtendedFraction( 1 );
        final ExtendedFraction fraction4 = new ExtendedFraction( -1, 2 );

        final ExtendedFraction actual2 = fraction3.substract( fraction4 );

        Assert.assertEquals( new Fraction(3, 2), actual2 );
    }
}
