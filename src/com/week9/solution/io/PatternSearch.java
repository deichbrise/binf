package com.week9.solution.io;

import com.week9.solution.io.result.SearchLineMinionResult;
import com.week9.solution.io.util.FileSystem;
import com.week9.solution.io.visitor.SearchFileVisitor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * An {@code SandR} is a command-line program which is able to search and
 * replace Strings in several files.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class PatternSearch {

    private static java.util.List<String> filesWithPattern = Collections.synchronizedList( new ArrayList<>() );

    private static final int POOL_SIZE = 12;

    /**
     * Handles an error in the {@code SandR} program by printing the error
     * message and a description of the usage of the program on the standard
     * output. It then terminates the program with error code {@code 1}.
     *
     * @param message the message to be printed before the program terminates
     */
    private static void handleError( String message ) {
        System.out.println( message );
        printUsage();
        System.exit( 1 );
    }

    public static void main( String[] args ) throws IOException {
      /*
       * options and arguments
       */
        boolean recursive = false;
        String search = null;

      /*
       * determine whether all arguments are read or not
       */
        boolean argumentsRead = false;

        int i = 0;
        while ( !argumentsRead && i < args.length ) {

         /*
          * read out the arguments
          */
            switch ( args[i] ) {
                case "-r":
                    recursive = true;
                    i++;
                    break;
                default:
            /*
             * arguments must be set at the beginning. Every String which isn't
             * an argument must be a path
             */
                    argumentsRead = true;
                    break;
            }
        }
      /*
       * next, two arguments must be search string and the replacement
       */
        if ( i >= args.length ) {
            handleError( "No expression to search for found" );
        } else {
            search = args[i++];
        }

      /*
       * make a new array with the paths
       */
        String[] dirs = Arrays.copyOfRange( args, i, args.length );

      /*
       * if no paths where given, use the current directory
       */
        if ( dirs.length == 0 ) {
            dirs = new String[]{"."};
        }

        Pattern searchPattern = null;

        try {
            searchPattern = Pattern.compile( search );
        } catch ( PatternSyntaxException ex ) {
            handleError( search + " is not a valid regular expression: "
                    + ex.getMessage() );
        }

        SearchFileVisitor searchFileVisitor = null;
        final ExecutorService executorService = Executors.newFixedThreadPool( POOL_SIZE );
        final java.util.List<SearchLineMinionResult> filesWithMatches = new Vector<>();
        for ( String dir : dirs ) {

            File f = new File( dir );
            if ( !f.exists() ) {
                System.out.println( dir + " does not exist" );
            } else {
                final FileSystem fileSystem = new FileSystem( f );

                searchFileVisitor = new SearchFileVisitor( recursive, search, f, executorService );

                fileSystem.accept( searchFileVisitor );

                for ( final Future<SearchLineMinionResult> future : searchFileVisitor.getFutures() ) {
                    final SearchLineMinionResult result;
                    try {
                        result = future.get();
                        if ( result.isFound() ) {
                            filesWithMatches.add( result );
                        }
                    } catch ( final Exception e ) {
                        handleError( "An error occurred. Message was:  " + e.getMessage() );
                    }

                }
            }
        }
        executorService.shutdown();

        for ( final SearchLineMinionResult s : filesWithMatches ) {
            System.out.println( s.toString() );
        }
    }

    /**
     * Print a short description of the usage of the {@code SandR} program on the
     * standard output.
     */
    private static void printUsage() {
        System.out
                .println( "Usage: java io/PatternSearch [-r] Search {FilesAndDirectories}" );
    }


}
