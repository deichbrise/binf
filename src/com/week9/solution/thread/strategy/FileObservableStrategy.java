package com.week9.solution.thread.strategy;

import com.week9.solution.thread.observable.Observable;

import java.io.File;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public interface FileObservableStrategy {
    public Observable<File> observe( File file );
}
