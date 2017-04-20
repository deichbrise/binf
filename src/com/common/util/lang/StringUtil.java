package com.common.util.lang;

/**
 * @author pascalstammer
 * @version 13.04.17.
 */
public class StringUtil {

    private static final String CLEAN_REGEX = System.lineSeparator();

    public static String clean(final String s) {
        return s.replaceAll( CLEAN_REGEX, "" );
    }

}
