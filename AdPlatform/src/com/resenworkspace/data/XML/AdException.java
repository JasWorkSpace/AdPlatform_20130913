package com.resenworkspace.data.XML;

public class AdException extends Exception {
    private static final long serialVersionUID = -7323249827281485390L;

    /**
     * Creates a new AdException.
     */
    public AdException() {
        super();
    }

    /**
     * Creates a new AdException with the specified detail message.
     *
     * @param message the detail message.
     */
    public AdException(String message) {
        super(message);
    }

    /**
     * Creates a new AdException with the specified cause.
     *
     * @param cause the cause.
     */
    public AdException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new AdException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause.
     */
    public AdException(String message, Throwable cause) {
        super(message, cause);
    }
}

