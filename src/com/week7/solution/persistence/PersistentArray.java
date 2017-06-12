package com.week7.solution.persistence;

import java.io.IOException;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public interface PersistentArray {
    Integer get(int index) throws IOException;
    void set(int index, Integer value) throws IOException;
    int size() throws IOException;
}
