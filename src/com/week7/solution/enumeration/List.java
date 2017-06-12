package com.week7.solution.enumeration;

import com.week7.solution.visitor.Visitor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class List {
    public static void main( String[] args ) {
        switch ( args.length ) {
            case 0:
                final FileEnumeration enumeration1 = new FileEnumeration();
                enumeration1.accept( new DirectoryStructurePrinter() );
                break;
            case 1:
                if(args[0].toLowerCase().equals( "-r" )) {
                    final FileEnumeration enumeration4 = new FileEnumeration();
                    enumeration4.accept( new DirectoryStructurePrinter(true) );
                    break;
                }
                    final FileEnumeration enumeration2 = new FileEnumeration(args[0]);
                enumeration2.accept( new DirectoryStructurePrinter() );
                break;
            case 2:
                if(args[0].toLowerCase().equals( "-r" )) {
                    final FileEnumeration enumeration3 = new FileEnumeration( args[1] );
                    enumeration3.accept( new DirectoryStructurePrinter(true) );
                } else {
                    throw new RuntimeException( "Cannot execute. Usage: java List [-r] [PathToFileOrDirectory]" );
                }
                break;
            default:
                throw new RuntimeException( "Cannot execute. Usage: java List [-r] [PathToFileOrDirectory]" );
        }
    }
}
