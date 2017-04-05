package week1.solution.fibonacci;

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
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return (cursor <= max);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    public Integer next() {
        if(!hasNext()) {
            throw new RuntimeException();
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
     * @param fibonacci
     * @return
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
