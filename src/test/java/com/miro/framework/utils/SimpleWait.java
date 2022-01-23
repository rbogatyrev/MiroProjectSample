package com.miro.framework.utils;

/**
 * Wrapper class of Thread.sleep
 */
public class SimpleWait {

    private SimpleWait() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Waiting for a specific time interval (sleep).
     *
     * @param timeInSeconds time interval in seconds.
     */
    public static void waitSec(final long timeInSeconds) {
        waitMillis(timeInSeconds * 1000);
    }

    /**
     * Waiting for a specific time interval (sleep).
     *
     * @param timeInMilliseconds time interval in milliseconds.
     */
    public static void waitMillis(final long timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

