package com.week9.solution.thread.strategy.impl;

import com.week9.solution.thread.observable.Observable;
import com.week9.solution.thread.observable.impl.FileSizeChangeObservable;
import com.week9.solution.thread.strategy.FileObservableStrategy;

import java.io.File;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class FileSizeChangeObservableStrategyImpl implements FileObservableStrategy {

    @Override
    public Observable<File> observe( final File file ) {
        return new FileSizeChangeObservable(file);
    }
}
