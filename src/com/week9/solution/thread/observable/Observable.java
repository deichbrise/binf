package com.week9.solution.thread.observable;

/**
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public interface Observable<T> {

    /**
     * A Subscription will be subscribed
     * @param subscription
     */
    public void subscribe(Subscription<T> subscription);

    /**
     * Close the observable
     */
    public void close();
}
