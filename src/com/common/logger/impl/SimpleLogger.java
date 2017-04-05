package com.common.logger.impl;

import com.common.logger.Logger;

import java.util.Date;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class SimpleLogger implements Logger {

    private Class<?> clazz;
    private boolean debugEnabled;
    private boolean infoEnabled;
    private boolean traceEnabled;
    private boolean errorEnabled;

    public SimpleLogger(final Class<?> clazz, boolean debugEnabled, boolean infoEnabled, boolean traceEnabled, boolean errorEnabled) {
        this.clazz = clazz;
        this.debugEnabled = debugEnabled;
        this.infoEnabled = infoEnabled;
        this.traceEnabled = traceEnabled;
        this.errorEnabled = errorEnabled;
    }

    public SimpleLogger( boolean debugEnabled, boolean infoEnabled, boolean traceEnabled, boolean errorEnabled) {
        this.debugEnabled = debugEnabled;
        this.infoEnabled = infoEnabled;
        this.traceEnabled = traceEnabled;
        this.errorEnabled = errorEnabled;
    }

    @Override
    public void debug( final String message ) {
        logToConsole( message, LogLevel.DEBUG );
    }

    @Override
    public void info( final String message ) {
        logToConsole( message, LogLevel.INFO );
    }

    @Override
    public void trace( final String message ) {
        logToConsole( message, LogLevel.TRACE );
    }

    @Override
    public void error( final String message ) {
        logToConsole( message, LogLevel.ERROR );
    }

    @Override
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    @Override
    public boolean isInfoEnabled() {
        return infoEnabled;
    }

    @Override
    public boolean isTraceEnabled() {
        return traceEnabled;
    }

    @Override
    public boolean isErrorEnabled() {
        return errorEnabled;
    }

    protected void logToConsole(final String message, final String level) {
        System.out.println(level + (clazz != null ? " - " + clazz.getSimpleName() + " - ": " " ) + new Date(System.currentTimeMillis()).toString() + " - " + message);
    }
}
