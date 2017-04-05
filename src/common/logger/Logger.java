package common.logger;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public interface Logger {

    void debug(final String message);
    void info(final String message);
    void trace(final String message);
    void error(final String message);

    boolean isDebugEnabled();
    boolean isInfoEnabled();
    boolean isTraceEnabled();
    boolean isErrorEnabled();

    public interface LogLevel {
        public final static String DEBUG = "[DEBUG]";
        public final static String INFO = "[INFO]";
        public final static String ERROR = "[ERROR]";
        public final static String TRACE = "[TRACE]";
    }
}
