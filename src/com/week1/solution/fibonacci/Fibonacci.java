package com.week1.solution.fibonacci;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class Fibonacci implements Iterator<Integer> {
    private Integer max;
    private Integer current;
    private Integer previous;
    private int cursor;

    public Fibonacci( final Integer max) {
        if(max < 2) {
            throw new RuntimeException();
        }
        this.max = max;
        this.current = 1;
        this.previous = 0;
        this.cursor = 0;
    }

    public Fibonacci(final Fibonacci fibonacci) {
        this.max = fibonacci.max;
        this.current = 1;
        this.previous = 0;
        this.cursor = 0;
    }

    /**
     * Returns {@code true} if there is next fibonacci number.
     *
     * @return {@code true} if a next fibonacci number exists
     */
    @Override
    public boolean hasNext() {
        return (cursor <= max);
    }

    /**
     * Returns the next fibonacci number
     *
     * @return the next fibonacci number
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        Integer next;
        if(cursor < 2) {
            next = cursor;
        } else {
            next = current + previous;
            previous = current;
            current = next;
        }
        cursor++;

        return next;
    }

    /**
     * Convert a Fibonacci Iterator to an array. Note, that we create a copy of the actual fibonacci iterator to preserve
     * current state of the iterator
     * @param fibonacci the fibonacci iterator that should converted to an array
     * @return an array representing the fibonacci number range
     */
    public static Integer[] toArray( final Fibonacci fibonacci) {
        final Fibonacci copy = new Fibonacci( fibonacci );
        final Integer length = copy.max + 1;
        final Integer[] array = new Integer[length];

        int index = 0;
        while(copy.hasNext()) {
            array[index++] = copy.next();
        }
        return array;
    }
}
