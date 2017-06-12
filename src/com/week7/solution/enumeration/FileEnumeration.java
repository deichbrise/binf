package com.week7.solution.enumeration;

import com.week7.solution.visitor.Visitable;
import com.week7.solution.visitor.Visitor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class FileEnumeration implements Visitable<File> {

    private File root;

    public FileEnumeration() {
        this(Paths.get( "" ).toAbsolutePath().toString());
    }

    public FileEnumeration(final String path) {
        this( new File( path ));
    }

    public FileEnumeration( final File root ) {
        this.root = root;
    }

    /**
     * Root file of this FileEnumeration accepts Visitor
     * If root is a directory, the method will be called recursively for all files in directory
     * @param v
     *           the Visitor which should be called for every element in this
     */
    @Override
    public void accept( final Visitor<File> v ) {
        if(v.visit( root )) {
            if(root.isDirectory()) {
                descendInDirectoryHook( v, root );
                for(final File file : root.listFiles()) {
                    final FileEnumeration fileEnumeration = new FileEnumeration( file );
                    fileEnumeration.accept( v );
                }
                completeDirectoryHook( v, root );
            }
        }
    }

    /**
     * If this visitor is a FileVisitor, its current level of descent will be increased
     * @param v visitor
     * @param file
     */
    protected void descendInDirectoryHook(Visitor<File> v, final File file) {
        if(FileVisitor.class.isAssignableFrom( v.getClass() )) {
            ((FileVisitor)v).descentInDirectory( file );
        }
    }

    /**
     * If this visitor is a FileVisitor, its current level of descent will be decreased
     *
     * @param v visitor
     * @param file
     */
    protected void completeDirectoryHook(Visitor<File> v, final File file) {
        if(FileVisitor.class.isAssignableFrom( v.getClass() )) {
            ((FileVisitor)v).completeDirectory( file );
        }
    }
}
