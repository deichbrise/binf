package com.week9.solution.thread.observable;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public interface Observable<T> {
    public void subscribe(Subscription<T> subscription);
    public void close();
}
