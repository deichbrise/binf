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

    @Override
    public void close() throws Exception {
        file.close();
    }

    @Override
    public Integer get( final int index ) throws IOException {
        file.seek( index * SIZE );
        return file.readInt();
    }

    @Override
    public void set( final int index, final Integer value ) throws IOException {
        file.seek( index * SIZE );
        file.writeInt( value );
        changesNumber++;
    }

    @Override
    public int size() throws IOException {
        return (int) file.length() / SIZE;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new BufferIterator(this.changesNumber);
    }

    protected class BufferIterator implements Iterator<Integer> {
        private Integer changesSnapshot;
        private RandomAccessFile file;
        private int position;

        public BufferIterator( final Integer changesSnapshot ) {
            this.changesSnapshot = changesSnapshot;
            this.file = file;
            position = 0;
        }

        @Override
        public boolean hasNext() {
            try {
                return (file.getFilePointer() / SIZE) < size();
            } catch ( IOException e ) {
                throw new IndexOutOfBoundsException();
            }
        }

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
