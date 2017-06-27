package com.week9.solution.thread.collector;

import java.io.File;
import java.util.List;

/**
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public interface FileCollector {
    public List<File> collect(File root);

    public FileCollector addFilter(FileFilter fileFilter);
}
