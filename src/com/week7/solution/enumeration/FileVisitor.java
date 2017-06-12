package com.week7.solution.enumeration;

import com.week7.solution.visitor.Visitor;

import java.io.File;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public interface FileVisitor extends Visitor<File> {
    public void descentInDirectory(final File file);
    public void completeDirectory(final File file);
}
