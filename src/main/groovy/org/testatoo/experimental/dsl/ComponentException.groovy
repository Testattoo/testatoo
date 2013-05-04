package org.testatoo.experimental.dsl

public class ComponentException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the message that will be displayed in the exception
     */
    public ComponentException(String message) {
        super(message)
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the message that will be displayed in the exception
     * @param throwable the cause of the exception
     */
    public ComponentException(String message, Throwable throwable) {
        super(message, throwable)
    }
}
