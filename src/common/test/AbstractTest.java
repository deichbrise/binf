package common.test;

import common.logger.LogManager;
import common.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author pascalstammer
 * @version 05.04.17.
 */
public class AbstractTest {

    private static Logger log = LogManager.getLogger(AbstractTest.class);

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

    protected static void run(final Class<?> clazz) {
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
                        } catch ( IllegalAccessException e ) {
                            e.printStackTrace();
                        } catch ( InvocationTargetException e ) {
                            e.printStackTrace();
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
    }

}
