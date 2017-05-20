package com.week5.solution.heap;

import com.common.util.ArrayUtil;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.Comparator;

/**
 * @author pascalstammer
 * @version 19.05.17.
 */
public class ArrayHeap<T> extends AbstractHeap<T> implements Heap<T> {

    private Object[] entries;

    private Comparator<T> comparator;
    private boolean isValidState = true;
    private Class<T> clazz;

    public ArrayHeap() {

    }

    public ArrayHeap( final Comparator<T> comparator ) {
        this.comparator = comparator;
    }

    @Override
    public void insert( final T x ) {
        if(!Comparable.class.isAssignableFrom( x.getClass() )) {
            throw new IllegalStateException( "No comparator found. Set a comparator or implement the comparable interface in argument." );
        }
        assertState(); // Check instance invariant
        isValidState = false; // instance invariant is invalid

        if(entries == null) {
            entries = arrayCreate( 1 );
            entries[0] = x;
        } else {
            final Object[] buffer = arrayCreate( entries.length + 1 );
            System.arraycopy( entries, 0, buffer, 0, entries.length );
            buffer[buffer.length - 1] = x;

            int currentIndex = buffer.length - 1;
            while(currentIndex > 0 && compare(buffer[currentIndex], buffer[parent( currentIndex )]) < 0) {
                ArrayUtil.swap( buffer, currentIndex, parent( currentIndex ) );
                currentIndex = parent( currentIndex );
            }
            entries = buffer;
        }
        isValidState = true; // instance invariant is valid
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public T deleteFirst() {
        assertState();
        isValidState = false;
        if(entries == null || entries.length == 0) {
            throw new ArrayIndexOutOfBoundsException( "Heap does not contain any elements" );
        }
        final T first = (T)entries[0];
        final Object[] buffer = arrayCreate( entries.length - 1 );
        System.arraycopy( entries, 1, buffer, 0, buffer.length );
        heapify( buffer, 0 );
        entries = buffer;
        isValidState = true;

        return first;
    }

    @Override
    @SuppressWarnings( "unchecked" )
    public T extractMin() {
        assertState();
        return (T)entries[0];
    }

    @Override
    public boolean empty() {
        return entries == null || entries.length == 0;
    }

    protected void heapify( Object[] arr, int index) {
        int min = index;
        if(left(index) < arr.length && compare( arr[left( index )], arr[min] ) < 0) {
            min = left(index);
        } else if(right(index) < arr.length && compare( arr[right(index)], arr[min] ) < 0) {
            min = right( index );
        }

        if(min != index) {
            ArrayUtil.swap( arr, min, index );
            heapify( arr, min );
        }
    }

    @SuppressWarnings( "unchecked" )
    protected Object[] arrayCreate(int length) {
        return new Object[length];
    }


    @SuppressWarnings( "unchecked" )
    protected int compare(final Object x1, final Object x2) {

        if(comparator != null) {
            return comparator.compare( (T)x1, (T)x2 );
        } else {
            return ((Comparable<T>)x1).compareTo( (T)x2 );
        }
    }

    protected int left(int i) {
        return i * 2 + 1;
    }

    protected int right(int i) {
        return i * 2 + 2;
    }

    protected int parent(int i) {
        boolean isRight = i % 2 == 0;
        if(isRight) {
            return (i - 2) / 2;
        } else {
            return (i - 1) / 2;
        }
    }

    protected void assertState() {
        if(!isValidState) {
            throw new IllegalStateException( "The heap is not in a valid state" );
        }
    }
}
