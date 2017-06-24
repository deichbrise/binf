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

    public static String padRight(String s, int n) {
        return String.format("%1$-" + (n + s.length()) + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + (n + s.length()) + "s", s);
    }

    public static String removeWhitespaces(final String s) {
        return s.replaceAll("\\s+","");
    }

    public static String removeTrailingWhitespaces(final String s) {
        return s.trim();
    }

}
