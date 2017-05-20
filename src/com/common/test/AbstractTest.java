package com.common.test;

import com.common.logger.LogManager;
import com.common.logger.Logger;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class AbstractTest {

    private static Logger log = LogManager.getLogger( AbstractTest.class );

    public void runAllTests() {
        log.info( "Run " + getClass().getName() );
        log.info( "======================================================" );
        final Method[] methods = getClass().getMethods();

        int testCounter = 0;
        for ( Method method : methods ) {
            if ( method.isAnnotationPresent( Test.class ) ) {
                testCounter++;
                final Class<? extends Exception>[] expectedExceptions = method.getAnnotation( Test.class ).shouldThrow();
                runTest( method.getName(), () -> {
                    try {
                        method.invoke( this, new Object[]{} );
                        if(expectedExceptions.length > 0) {
                            throw new AssertionError( "Should throw" + Arrays.toString(expectedExceptions) );
                        }
                    } catch ( Exception e ) {

                        boolean isExpected = false;
                        for(Class<? extends Exception> expected : expectedExceptions) {
                            if(e.getCause().getClass().equals( expected )) {
                                isExpected = true;
                                break;
                            }
                        }
                        if(!isExpected) {
                            e.printStackTrace();
                        }
                    }
                } );
            }
        }
        log.info( "Testing successfully. Number of tests: " + testCounter );
        log.info( "======================================================" );
    }

    private static void runTest( final String testName, final TestRunner testRunner ) {
        try {
            testRunner.execute();
            onSuccess( testName );
        } catch ( final Exception e ) {
            onError( testName, e );
            throw e;
        }
    }

    private static void onSuccess( final String testName ) {
        log.info( "Test finished - " + testName );
    }

    private static void onError( final String testName, final Exception e ) {
        log.error( "Error on test " + testName + ". Message was " + e.getMessage() );
    }

    private interface TestRunner {
        void execute();
    }
}
