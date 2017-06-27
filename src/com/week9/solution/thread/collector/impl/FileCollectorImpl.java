package com.week9.solution.thread.collector.impl;

import com.week9.solution.thread.collector.FileCollector;
import com.week9.solution.thread.collector.FileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Collect files starting with a given root file object. It is possible to define specific file filter to exclude
 * files with specific rules.
 *
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public class FileCollectorImpl implements FileCollector {

    private List<FileFilter> fileFilters = new ArrayList<>();

    /**
     * Collect files within the given file. If file is a file only the file is returned. Else all files in folder
     * are recursively collected.
     *
     * @param root file
     * @return a list of files
     */
    @Override
    public List<File> collect( final File root ) {
        final List<File> result = new ArrayList<>();

        if(root.isFile()) {
            if(check( root )) {
                result.add( root );
            }
        } else {
            final File[] files = root.listFiles();
            if(files != null) {
                for(final File file : files) {
                    result.addAll( collect( file ) );
                }
            }
        }
        return result;
    }

    protected boolean check(final File file) {
        for(final FileFilter fileFilter : fileFilters) {
            if(!fileFilter.check( file )) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add a filter
     * @param fileFilter
     * @return
     */
    @Override
    public FileCollector addFilter( final FileFilter fileFilter ) {
        fileFilters.add( fileFilter );
        return this;
    }
}
