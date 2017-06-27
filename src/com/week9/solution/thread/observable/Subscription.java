package com.week9.solution.thread.observable;

/**
 * Subscribe to a observable.
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public interface Subscription<T> {

    /**
     * Called by an observable
     * @param o
     */
    public void call(T o);

    /**
     * Called by observable
     */
    public void close();
}
