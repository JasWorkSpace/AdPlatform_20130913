package com.resenworkspace.data.XML;

/**
 * An exception that is thrown when content type is not supported.
 */
public final class UnsupportContentTypeException extends ContentRestrictionException {
    private static final long serialVersionUID = 2684128059358484321L;

    public UnsupportContentTypeException() {
        super();
    }

    public UnsupportContentTypeException(String msg) {
        super(msg);
    }
}

