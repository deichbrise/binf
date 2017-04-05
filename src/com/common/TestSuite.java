package com.common;

import com.common.test.AbstractTest;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class TestSuite {

    public static void main( String[] args ) throws ClassNotFoundException {
        final String[] classesToRun = args;

        for(final String classToRun : classesToRun) {
            final Class<?> testClass = Class.forName( classToRun );
            AbstractTest.run( testClass );
        }
    }
}
