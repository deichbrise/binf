package week1.solution.fibonacci;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pascalstammer
 * @version 04.04.17.
 */
public class FibonacciIterable implements Iterable<Integer> {

    private Integer max;
    private Iterator<Integer> fibonacciIterator;

    public FibonacciIterable() {
        this(Integer.MAX_VALUE);
    }

    public FibonacciIterable( final Integer max ) {
        this.max = max;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Integer> iterator() {
        return new Fibonacci( max );
    }

}
