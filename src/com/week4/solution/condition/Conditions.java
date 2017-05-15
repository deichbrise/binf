package com.week4.solution.condition;

import com.week4.solution.fraction.Fraction;

/**
 * @author pascalstammer
 * @version 15.05.17.
 */
public class Conditions {
    public static boolean isFloatingPoint(final String s) {
        return s.contains( "." ) || !isFraction( s );
    }

    public static boolean isAnyFloatingPoint(final String... s) {
        for(final String c : s) {
            if(isFloatingPoint( c )) {
                return true;
            }
        }
        return false;
    }

    public static boolean allFloatingPoint(final String... s) {
        boolean result = true;
        for(final String c : s) {
            result = result && isFloatingPoint( c );
        }

        return result;
    }

    public static boolean allFractions(final String... s) {
        boolean result = true;
        for(final String c : s) {
            result = result && isFraction( c );
        }

        return result;
    }

    public static boolean isFraction(final String s) {
        return s.matches( Fraction.REGEX_PURE_FRACTION );
    }
}
