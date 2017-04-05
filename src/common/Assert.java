package common;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class Assert {

    public static <T> void assertArrayEqual(final T[] expected, final T[] actual) {
        if(expected.length != actual.length) {
            throwAssertionException(expected, actual);
        }

        for(int i = 0; i < expected.length; i++) {
            if(!expected[i].equals( actual[i] )) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    protected static void throwAssertionException(final Object expected, final Object actual) {

    }
}
