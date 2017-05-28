package com.week6.fraction;

import com.common.test.AbstractTest;
import com.common.test.Assert;
import com.common.test.Test;

/**
 * Created by Julia on 26.05.2017.
 */
@Test
public class FractionTest extends AbstractTest{

    public static void main( String[] args ) {
        final FractionTest instance = new FractionTest();
        instance.runAllTests();
    }

    @Test
    public void testConstructor() {
        Fraction one = Fraction.getInstance(1);
        Fraction two = Fraction.getInstance(1, 1);
        Fraction three = Fraction.getInstance(9, 9);
        Fraction four = Fraction.getInstance(999999999, 1000000000);
        Fraction five = Fraction.getInstance(-1);
        Fraction six = Fraction.getInstance(-9, -9);
        Assert.assertTrue(one == two && two == three && one != four && one != five && three == six);
    }

    @Test
    public void calculationTest() {
        Fraction one = Fraction.getInstance(42, 2);
        Fraction two = Fraction.getInstance(1, 2);
        Fraction three = one.divide(two);
        Fraction four = Fraction.getInstance(-42, -1);
        Fraction five = two.substract(one);
        Fraction six = Fraction.getInstance(82,-4);
        Fraction seven = Fraction.getInstance(16);
        Fraction eight = Fraction.getInstance(2);
        Fraction nine = seven.multiply(two, two, two);
        Assert.assertTrue(five == six);
        Assert.assertTrue(three == four);
        Assert.assertFalse(one == two);
        Assert.assertFalse(one == null);
        Assert.assertTrue(nine == eight);
    }

    @Test
    public void parsingTest() {
        Fraction one = Fraction.parseFraction("5/6");
        Fraction two = Fraction.parseFraction("10/12");
        Fraction three = Fraction.parseFraction("-84/2");
        Fraction four = Fraction.parseFraction("-42/1");
        Fraction six = Fraction.getInstance(-42);
        Fraction seven = Fraction.getInstance(-10,-12);
        Assert.assertTrue(one == two);
        Assert.assertTrue(two == seven);
        Assert.assertTrue(three == four);
        Assert.assertTrue(three == six);
        Assert.assertFalse(one == four);
    }
}


