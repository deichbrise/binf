package com.week9.solution.io.visitor;

import com.week9.solution.io.minion.SearchLineMinion;
import com.week9.solution.io.result.SearchLineMinionResult;

import java.io.File;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class SearchFileVisitor extends com.week9.solution.io.util.FileVisitorAdapter {

    /**
     * {@code Pattern} that should be searched for in every {@code File} that
     * is accepted by {@code filter} and will be replaced by {@code
     * replacement}
     */
    private final String search;

    /**
     * denotes, whether this visitor should search in the sub-directories.
     */
    private final boolean recursive;

    /**
     * the root of the underlying file system
     */
    private final File root;

    private final ExecutorService executorService;

    private final java.util.List<Future<SearchLineMinionResult>> futures;

    /**
     * @param recursive denotes, whether this visitor should search in the
     *                  sub-directories.
     * @param search    a regular expression that should be searched for in
     *                  every {@code File} that is accepted by {@code
     *                  filter} and will be replaced by {@code replacement}
     * @param root      the root of the searched file system. will always
     *                  run through all entries in the root, independently
     *                  from the recursive flag.
     */
    public SearchFileVisitor( boolean recursive, String search, File root, final ExecutorService executorService ) {
        this.search = search;
        this.recursive = recursive;
        this.root = root;
        this.executorService = executorService;
        this.futures = new Vector<>();
    }

    @Override
    public com.week9.solution.io.util.FileVisitResult preVisitDirectory( File dir ) {
        if ( recursive || dir == this.root ) {
            return com.week9.solution.io.util.FileVisitResult.CONTINUE;
        } else {
            return com.week9.solution.io.util.FileVisitResult.SKIP_SUBTREE;
        }
    }

    /**
     * @param file the File that will be searched for the specific regular
     *             expression of this {@code SandR}
     * @return {@link com.week9.solution.io.util.FileVisitResult#CONTINUE}
     */
    public com.week9.solution.io.util.FileVisitResult visitFile( File file ) {
        futures.add( executorService.submit( new SearchLineMinion( file, search ) ) );
        return com.week9.solution.io.util.FileVisitResult.CONTINUE;
    }

    public java.util.List<Future<SearchLineMinionResult>> getFutures() {
        return futures;
    }
}