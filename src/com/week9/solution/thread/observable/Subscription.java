package com.week9.solution.thread.observable;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public interface Subscription<T> {
    public void call(T o);
    public void close();
}
