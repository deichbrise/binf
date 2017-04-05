package week1.test.fraction;

import common.Assert;
import common.test.AbstractTest;
import common.test.Test;
import week1.solution.fraction.Fraction;
import common.Assert.*;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class FractionTest extends AbstractTest {

    public static void main( String[] args ) {
        run( FractionTest.class );
    }

    @Test
    public void testMultiplyWithInteger() {
        final Fraction fraction = new Fraction(2, 4);

        final Fraction actual = fraction.multiply( 2 );
        final Fraction expected = new Fraction( 1 );

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMultiplyWithFraction() {
        final Fraction fraction = new Fraction( 1, 2 );
        final Fraction factor = new Fraction( 1, 2 );

        final Fraction actual = fraction.multiply( fraction );
        final Fraction expected = new Fraction( 1, 4 );

        Assert.assertEquals( expected, actual );
    }

    @Test
    public void testMultiplyWithMultiplyFraction() {
        final Fraction fraction = new Fraction( 1, 2 );
        final Fraction factor1 = new Fraction( 1, 2 );
        final Fraction factor2 = new Fraction( 1, 2 );

        final Fraction actual = fraction.multiply( factor1, factor2 );
        final Fraction expected = new Fraction( 1, 8 );

        Assert.assertEquals( expected, actual );
    }

    @Test
    public void testSimpleConstructor() {
        final Fraction fraction = new Fraction( 2 );

        Assert.assertEquals( 1, fraction.getDenominator() );
        Assert.assertEquals( 2, fraction.getNumerator() );
    }

    @Test
    public void testMoreComplexConstructor() {
        final Fraction fraction = new Fraction( 2, 4 );

        Assert.assertEquals( 1, fraction.getNumerator() );
        Assert.assertEquals( 2, fraction.getDenominator() );
    }
}
