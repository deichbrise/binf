package common;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class Assert {

    public static <T> void assertArrayEqual(final T[] expected, final T[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualExpection(expected, actual);
        }

        for(int i = 0; i < expected.length; i++) {
            if(!expected[i].equals( actual[i] )) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    public static <T> void assertEquals(final T expected, final T actual) {
        if(!expected.equals( actual )) {
            throwAssertionException( expected, actual );
        }
    }

    protected static void throwAssertionException(final Object expected, final Object actual) {
        throw new AssertionError( "Expected: " + expected.toString() + " but was " + actual.toString() );
    }

    protected static void throwArrayLengthNotEqualExpection(final Object[] expected, final Object[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }
}
