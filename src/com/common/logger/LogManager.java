package com.common.logger;

import com.common.logger.impl.SimpleLogger;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class LogManager {

    public static Logger getLogger() {
        return new SimpleLogger( true, true, true, true );
    }

    public static Logger getLogger(final Class<?> clazz) {
        return new SimpleLogger( clazz, true, true, true, true );
    }
}
