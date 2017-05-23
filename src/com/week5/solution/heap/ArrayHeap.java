package com.week5.solution.heap;

import com.common.util.ArrayUtil;

import java.util.Comparator;

/**
 * @author pascalstammer
 * @version 19.05.17.
 */
public class ArrayHeap<T> extends AbstractHeap<T> implements Heap<T> {

    private Element<T>[] entries;

    private Comparator<? super T> comparator;
    private boolean isValidState = true;

    /**
     * Default-Constructor. Impliziert, dass T Compareable implementieren muss.
     */
    public ArrayHeap() {
    }

    /**
     * Nutzt Comparator.
     *
     * @param comparator
     */
    public ArrayHeap( final Comparator<? super T> comparator ) {
        this.comparator = comparator;
    }

    /**
     * Fügt das Element x hinzu
     *
     * @param x das hinzuzufügende Element
     * @throws IllegalStateException wenn kein Comparator definiert ist, und Generic-Type kein Comparable-Interface implementiert
     */
    @Override
    public void insert( final T x ) {
        if ( comparator == null && !Comparable.class.isAssignableFrom( x.getClass() ) ) {
            throw new IllegalStateException( "No comparator found. Set a comparator or implement the comparable interface in argument." );
        }
        assertState(); // Check instance invariant
        isValidState = false; // instance invariant is invalid

        if ( entries == null ) {
            entries = arrayCreate( 1 );
            entries[0] = new Element<>(x);
        } else {
            final Element<T>[] buffer = arrayCreate( entries.length + 1 );
            System.arraycopy( entries, 0, buffer, 0, entries.length );
            buffer[buffer.length - 1] = new Element<>( x );

            int currentIndex = buffer.length - 1;
            while ( currentIndex > 0 && compare( buffer[currentIndex], buffer[parent( currentIndex )] ) < 0 ) {
                ArrayUtil.swap( buffer, currentIndex, parent( currentIndex ) );
                currentIndex = parent( currentIndex );
            }
            entries = buffer;
        }
        isValidState = true; // instance invariant is valid
    }

    /**
     * Entfernt das erste Element.
     *
     * @return das entfernte Element
     * @throws ArrayIndexOutOfBoundsException, wenn Heap ist leer
     */
    @Override
    @SuppressWarnings("unchecked")
    public T deleteFirst() {
        assertState();
        isValidState = false;

        if ( empty() ) {
            throw new ArrayIndexOutOfBoundsException( "Heap does not contain any elements" );
        }
        final T first = entries[0].element;
        final Element<T>[] buffer = arrayCreate( entries.length - 1 );
        System.arraycopy( entries, 1, buffer, 0, buffer.length );
        heapify( buffer, 0 );
        entries = buffer;

        isValidState = true;

        return first;
    }

    /**
     * Ermittelt das Minimum des Heaps
     *
     * @return das Minimum des Heaps
     */
    @Override
    @SuppressWarnings("unchecked")
    public T extractMin() {
        assertState();
        return entries[0].element;
    }

    /**
     * Ermittelt, ob Heap leer ist
     *
     * @return
     */
    @Override
    public boolean empty() {
        return entries == null || entries.length == 0;
    }

    protected void heapify( Element<T>[] arr, int index ) {
        int min = index;
        if ( left( index ) < arr.length && compare( arr[left( index )], arr[min] ) < 0 ) {
            min = left( index );
        } else if ( right( index ) < arr.length && compare( arr[right( index )], arr[min] ) < 0 ) {
            min = right( index );
        }

        if ( min != index ) {
            ArrayUtil.swap( arr, min, index );
            heapify( arr, min );
        }
    }

    @SuppressWarnings("unchecked")
    protected Element<T>[] arrayCreate( int length ) {
        return (Element<T>[]) new Element[length];
    }


    @SuppressWarnings("unchecked")
    protected int compare( final Element<T> x1, final Element<T> x2 ) {

        if ( comparator != null ) {
            return comparator.compare( x1.element, x2.element );
        } else {
            return ((Comparable<T>) x1.element).compareTo( x2.element );
        }
    }

    protected int left( int i ) {
        return i * 2 + 1;
    }

    protected int right( int i ) {
        return i * 2 + 2;
    }

    protected int parent( int i ) {
        boolean isRight = i % 2 == 0;
        if ( isRight ) {
            return (i - 2) / 2;
        } else {
            return (i - 1) / 2;
        }
    }

    protected void assertState() {
        if ( !isValidState ) {
            throw new IllegalStateException( "The heap is not in a valid state" );
        }
    }

    private static class Element<E> {

        public Element( final E element ) {
            this.element = element;
        }

        E element;
    }
}
