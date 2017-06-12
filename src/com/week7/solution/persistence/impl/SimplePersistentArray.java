package com.week7.solution.persistence.impl;

import com.common.util.FileUtil;
import com.week7.solution.persistence.PersistentArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class SimplePersistentArray implements PersistentArray, AutoCloseable, Iterable<Integer> {

    private RandomAccessFile file;
    private Integer changesNumber;
    private static final Integer SIZE = 4;

    public SimplePersistentArray(final String path) throws IOException {
        if(!FileUtil.fileExists( path )) {
            throw new FileNotFoundException( "Cannot find file " + path );
        }
        file = new RandomAccessFile(path, "rws");
        this.changesNumber = 0;
    }

    public SimplePersistentArray(final Integer[] array, final String path) throws IOException {
        if(FileUtil.fileExists( path )) {
            new File(path).delete();
        }
        Files.createFile( Paths.get( path ) );
        file = new RandomAccessFile(path, "rws");
        for(int i = 0; i < array.length; i++) {
            file.writeInt( array[i] );
        }
        this.changesNumber = 0;
    }

    /**
     * Closes random access file stream and releases any system resources associated with the stream
     * @throws Exception if an I/O error occurs
     */
    @Override
    public void close() throws Exception {
        file.close();
    }

    /**
     * RandomAccessFile file will return the Integer at the position equivalent to index
     * @param index of an array
     * @return Integer at requested position
     * @throws IOException if an I/O error occurs
     */
    @Override
    public Integer get( final int index ) throws IOException {
        file.seek( index * SIZE );
        return file.readInt();
    }

    /**
     * Puts given Integer at the position equivalent to index and replaces former Integer
     * Increases number of changes
     * @param index of an array
     * @param value new Integer to set
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void set( final int index, final Integer value ) throws IOException {
        file.seek( index * SIZE );
        file.writeInt( value );
        changesNumber++;
    }

    /**
     * Calculates the length of the array hidden in this RandomAccessFile
     * @return int size of the array
     * @throws IOException if an I/O error occurs
     */
    @Override
    public int size() throws IOException {
        return (int) file.length() / SIZE;
    }

    /**
     * Creates and returns a BufferIterator which saves the current number of changes
     * @return
     */
    @Override
    public Iterator<Integer> iterator() {
        return new BufferIterator(this.changesNumber);
    }

    /**
     * Inner Class BufferIterator
     */
    protected class BufferIterator implements Iterator<Integer> {
        private Integer changesSnapshot;
        private RandomAccessFile file;
        private int position;

        public BufferIterator( final Integer changesSnapshot ) {
            this.changesSnapshot = changesSnapshot;
            this.file = file;
            position = 0;
        }

        /**
         * Provides information whether File has more elements
         * @return true if the current position is still below size of file
         */
        @Override
        public boolean hasNext() {
            try {
                return (file.getFilePointer() / SIZE) < size();
            } catch ( IOException e ) {
                throw new IndexOutOfBoundsException();
            }
        }

        /**
         * Returns the next Integer if file has not been changed
         * @return Integer at the next position
         */
        @Override
        public Integer next() {
            if(changesNumber != changesSnapshot) {
                throw new ConcurrentModificationException();
            }

            try {
                file.seek(SIZE * position++);
                return file.readInt();
            } catch ( IOException e ) {
                throw new IndexOutOfBoundsException();
            }
        }
    }
}
