package com.week7.persistenz;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.FileNotFoundException;

/**
 * Created by Julia on 10.06.2017.
 */
public class PersistentArray {
    private RandomAccessFile file;
    private int length;

    public PersistentArray(Integer[] temporary, String name) throws IOException {
        length = temporary.length;
        try {
            file = new RandomAccessFile("array", "rw");
            for (Integer i : temporary) {
                file.writeInt(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer[] createArray() throws IOException {
        Integer[] active = new Integer[length];
        int pos = 0;
        for (int i = 0; i < length; i++) {
            file.seek(pos);
            active[i] = file.readInt();
            pos += 4;
        }
        return active;
    }
}
