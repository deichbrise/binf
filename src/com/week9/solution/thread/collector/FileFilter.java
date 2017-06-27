package com.week9.solution.thread.collector;

import java.io.File;

/**
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public interface FileFilter {
    public boolean check(File file);
}
