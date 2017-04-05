package common.util;

import java.math.BigInteger;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class NumberUtil {

    public static int greatestCommonDivisor(final Integer number1, final Integer number2) {
        return BigInteger.valueOf( number1 ).gcd( BigInteger.valueOf( number2 ) ).intValue();
    }
}
