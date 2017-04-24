package com.common.test;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class Assert {

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     */
    public static void assertArrayEquals(final int[] expected, final int[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException( expected, actual );
        }

        for(int i = 0; i < expected.length; i++) {
            if(expected[i] !=  actual[i] ) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    public static void assertTrue(boolean assertion) {
        if(!assertion) {
            throw new AssertionError();
        }
    }

    public static void assertFalse(boolean assertiona) {
        assertTrue( !assertiona );
    }

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     */
    public static void assertArrayEquals(final float[] expected, final float[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException( expected, actual );
        }

        for(int i = 0; i < expected.length; i++) {
            if(expected[i] !=  actual[i] ) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     */
    public static void assertArrayEquals(final double[] expected, final double[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException( expected, actual );
        }

        for(int i = 0; i < expected.length; i++) {
            if(expected[i] !=  actual[i] ) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     */
    public static void assertArrayEquals(final boolean[] expected, final boolean[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException( expected, actual );
        }

        for(int i = 0; i < expected.length; i++) {
            if(expected[i] !=  actual[i] ) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     */
    public static void assertArrayEquals(final char[] expected, final char[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException( expected, actual );
        }

        for(int i = 0; i < expected.length; i++) {
            if(expected[i] !=  actual[i] ) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    /**
     * Prueft ob zwei Arrays die gleiche Laenge haben und ob die Elemente am gleichen Index gleich sind.
     *
     * @param expected the expected array
     * @param actual the actual array
     * @param <T> the array entry type
     */
    public static <T> void assertArrayEqual(final T[] expected, final T[] actual) {
        if(expected.length != actual.length) {
            throwArrayLengthNotEqualException(expected, actual);
        }

        for(int i = 0; i < expected.length; i++) {
            if(!expected[i].equals( actual[i] )) {
                throwAssertionException(expected[i], actual[i]);
            }
        }
    }

    /**
     * Prueft ob zwei Instanzen gleich sind.
     *
     * @param expected the expected value
     * @param actual the acual value
     * @param <T> the object type
     */
    public static <T> void assertEquals(final T expected, final T actual) {
        if(!expected.equals( actual )) {
            throwAssertionException( expected, actual );
        }
    }

    /**
     * Prueft ob uebergebene Instanz nicht null ist.
     * @param object that should be not null
     */
    public static void assertNotNull(final Object object) {
        if(object == null) {
            throwAssertionNotNullExpection();
        }
    }

    /**
     * Prueft ob uebergeben Instanz null ist.
     * @param object that should be null
     */
    public static void assertNull(final Object object) {
        if(object != null) {
            throwAssertionNullExpection();
        }
    }

    protected static void throwAssertionNullExpection() {
        throw new AssertionError( "Object should be null." );
    }

    protected static void throwAssertionNotNullExpection() {
        throw new AssertionError( "Object must be not null." );
    }

    protected static void throwAssertionException(final Object expected, final Object actual) {
        throw new AssertionError( "Expected: " + expected.toString() + " but was " + actual.toString() );
    }

    protected static void throwArrayLengthNotEqualException( final Object[] expected, final Object[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }

    protected static void throwArrayLengthNotEqualException( final double[] expected, final double[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }

    protected static void throwArrayLengthNotEqualException( final int[] expected, final int[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }

    protected static void throwArrayLengthNotEqualException( final float[] expected, final float[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }

    protected static void throwArrayLengthNotEqualException( final boolean[] expected, final boolean[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }
    protected static void throwArrayLengthNotEqualException( final char[] expected, final char[] actual) {
        throw new AssertionError( "Expected length " + expected.length + " but was " + actual.length );
    }
}
