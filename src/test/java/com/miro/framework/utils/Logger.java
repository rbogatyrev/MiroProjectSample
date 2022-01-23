package com.miro.framework.utils;

/**
 * Utility class for logging
 */
public class Logger {

    private static final org.apache.log4j.Logger LOG4J = org.apache.log4j.LogManager.getLogger(Logger.class);
    private static Logger instance = null;

    private Logger() {
    }

    /**
     * A method that returns a single instance of the class. If the instance is not created - creates a new one.
     *
     * @return Class instance.
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Information logging with [DEBUG] tag
     *
     * @param message logging message.
     */
    public void debug(final String message) {
        LOG4J.debug(message);
    }

    /**
     * Information logging with [INFO] tag
     *
     * @param message logging message.
     */
    public void info(final String message) {
        LOG4J.info(message);
    }

    /**
     * Information logging with [WARN] tag
     *
     * @param message logging message.
     */
    public void warn(final String message) {
        LOG4J.warn(message);
    }

    /**
     * Information loggin with [ERROR] tag
     *
     * @param message logging message.
     */
    public void error(final String message) {
        LOG4J.error(message);
    }

    /**
     * Логирование инфомарции с тегом [FATAL].
     *
     * @param message   logging message.
     * @param throwable exception that aborted the execution
     */
    public void fatal(final String message, Throwable throwable) {
        LOG4J.fatal(message + ": " + throwable.toString());
    }

}
