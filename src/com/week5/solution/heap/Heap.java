package com.week5.solution.heap;

/**
 * @author pascalstammer
 * @version 19.05.17.
 */
public interface Heap<T> {
    void insert(T x);
    boolean empty();
    T deleteFirst();
    T extractMin();
}
