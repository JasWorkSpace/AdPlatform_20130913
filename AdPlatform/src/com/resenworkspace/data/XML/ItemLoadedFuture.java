package com.resenworkspace.data.XML;

/**
 * Interface for querying the state of a pending item loading request.
 *
 */
public interface ItemLoadedFuture {
    /**
     * Returns whether the associated task has invoked its callback. Note that
     * in some implementations this value only indicates whether the load
     * request was satisfied synchronously via a cache rather than
     * asynchronously.
     */
    boolean isDone();

    void cancel();
}


