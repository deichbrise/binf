package com.week9.solution.thread.strategy.impl;

import com.week9.solution.thread.observable.Observable;
import com.week9.solution.thread.observable.impl.FileSizeChangeObservable;
import com.week9.solution.thread.strategy.FileObservableStrategy;

import java.io.File;

/**
 * Creates a new File Observable
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public class FileSizeChangeObservableStrategyImpl implements FileObservableStrategy {

    @Override
    public Observable<File> observe( final File file ) {
        return new FileSizeChangeObservable(file);
    }
}
