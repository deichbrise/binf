package com.week9.solution.thread.collector.impl;

import com.week9.solution.thread.collector.FileCollector;
import com.week9.solution.thread.collector.FileFilter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class FileCollectorImpl implements FileCollector {

    private List<FileFilter> fileFilters = new ArrayList<>();

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

    @Override
    public FileCollector addFilter( final FileFilter fileFilter ) {
        fileFilters.add( fileFilter );
        return this;
    }
}
