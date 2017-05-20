package com.week5.solution.clone;

/**
 * @author pascalstammer
 * @version 19.05.17.
 */
public interface List<T> {
    boolean empty();
    boolean endpos();

    void reset();
    void advance();

    T elem();
    void add(T x);
    void delete();

}
