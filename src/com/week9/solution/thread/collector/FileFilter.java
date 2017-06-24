package com.week9.solution.thread.collector;

import java.io.File;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public interface FileFilter {
    public boolean check(File file);
}
