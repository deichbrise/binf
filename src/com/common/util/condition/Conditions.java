package com.common.util.condition;

/**
 * @author pascalstammer
 * @version 13.04.17.
 */
public class Conditions {

    public static boolean isNull(final Object o) {
        return o == null;
    }

    public static boolean isNotNull(final Object o) {
        return !isNull( o );
    }
}
