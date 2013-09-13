package com.resenworkspace.data.XML;

/**
 * An exception that is thrown when Ad size exceeds limitation.
 */
public final class ExceedAdSizeException extends ContentRestrictionException {
    private static final long serialVersionUID = 6647713416796190850L;

    public ExceedAdSizeException() {
        super();
    }

    public ExceedAdSizeException(String msg) {
        super(msg);
    }
}

