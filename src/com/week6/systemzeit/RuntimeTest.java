package com.week6.systemzeit;


/**
 * Created by Julia on 26.05.2017.
 */
public class RuntimeTest {
    private static java.util.LinkedList linked = new java.util.LinkedList<Integer>();
    private static java.util.ArrayList array = new java.util.ArrayList<Integer>();
    private static java.util.HashSet hash = new java.util.HashSet<Integer>();

    public static void main (String args[]){
        System.out.println("Add()");
        System.out.print("| LinkedList: ");
        testAdd(linked);
        System.out.print("| ArrayList: ");
        testAdd(array);
        System.out.print("| HashSet: ");
        testAdd(hash);
        System.out.println();

        System.out.println("Contains()");
        System.out.print("| LinkedList: ");
        testContains(linked);
        System.out.print("| ArrayList: ");
        testContains(array);
        System.out.print("| HashSet: ");
        testContains(hash);
        System.out.println();

        System.out.println("Remove()");
        System.out.print("| LinkedList: ");
        testRemove(linked);
        System.out.print("| ArrayList: ");
        testRemove(array);
        System.out.print("| HashSet: ");
        testRemove(hash);
    }
    private static void testAdd(java.util.Collection a) {

        long medium = 0;
        for (int i = 0; i < 50000; i++) {
            long start = System.nanoTime();
            a.add(i);
            long end = System.nanoTime();
            medium += end - start;
        }

        System.out.print(medium / 50000 + " ");
    }
    private static void testContains(java.util.Collection c) {
        long medium = 0;
        for (int i = 0; i < 50000; i++) {
            long start = System.nanoTime();
            c.contains(i);
            long end = System.nanoTime();
            medium += end - start;
        }
        System.out.print(medium / 50000+ " ");
    }
    private static void testRemove(java.util.Collection r) {
        long medium = 0;
        for (int i = 0; i < 50000; i++) {
            long start = System.nanoTime();
            r.remove(i);
            long end = System.nanoTime();
            medium += end - start;
        }
        System.out.print(medium / 50000+ " ");
    }
}
