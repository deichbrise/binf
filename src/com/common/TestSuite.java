package com.common;

import com.common.logger.LogManager;
import com.common.logger.Logger;
import com.common.test.AbstractTest;
import com.common.test.Test;
import org.atteo.classindex.ClassFilter;
import org.atteo.classindex.ClassIndex;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class TestSuite {

    public static void main( String[] args ) throws ClassNotFoundException {

        for(String s : args) {
            log.debug( s );
        }
        if(args.length == 0) {
            final Iterable<Class<?>> classes = ClassIndex.getAnnotated( Test.class);

            for(final Class<?> clazz : classes) {
                run( clazz );
            }
        } else if(args.length == 1) {
            final Iterable<Class<?>> classes = ClassFilter.only().topLevel().from( ClassIndex.getAnnotated( Test.class) );

            for(final Class<?> clazz : classes) {
                if(clazz.getName().matches( args[0] )) {
                    run( clazz );
                }
            }
        } else {
            System.exit( 1 );
        }

    }

    private static Logger log = LogManager.getLogger(TestSuite.class);

    private static void runTest(final String testName, final TestRunner testRunner) {
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

    private static void onError( final String testName, final Exception e) {
        log.error( "Error on test " + testName + ". Message was " + e.getMessage() );
    }

    private interface TestRunner {
        void execute();
    }

    public static void run(final Class<?> clazz) {
        log.info( "Run " + clazz.getName() );
        log.info( "======================================================" );
        try {
            final Object instance = clazz.newInstance();
            final Method[] methods = clazz.getMethods();

            int testCounter = 0;
            for(Method method : methods) {
                if(method.isAnnotationPresent( Test.class )) {
                    testCounter++;
                    runTest( method.getName(), () -> {
                        try {
                            method.invoke( instance, new Object[]{});
                        } catch ( Exception e ) {
                            e.printStackTrace();
                            System.exit( 1 );
                        }
                    } );
                }
            }
            log.info( "Testing successfully. Number of tests: " + testCounter );
        } catch ( InstantiationException e ) {
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            e.printStackTrace();
        }
        log.info( "======================================================" );
    }
}
