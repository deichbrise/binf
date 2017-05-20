package com.common.util;

/**
 * @author pascalstammer
 * @version 20.05.17.
 */
public class ArrayUtil {

    public static void swap(Object[] arr, int from, int to) {
        final Object buffer1 = arr[from];
        final Object buffer2 = arr[to];

        arr[from] = buffer2;
        arr[to] = buffer1;
    }
}
