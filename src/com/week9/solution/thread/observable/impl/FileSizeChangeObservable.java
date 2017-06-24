package com.week9.solution.thread.observable.impl;

import com.week9.solution.thread.observable.Observable;
import com.week9.solution.thread.observable.Subscription;
import com.week9.solution.thread.task.impl.FileSizeChangeDetectionTask;

import java.io.File;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class FileSizeChangeObservable implements Observable<File> {
    private Map<Subscription<File>, Subscription<File>> subscriptions;
    private File file;
    private Timer timer;
    private TimerTask task;

    public FileSizeChangeObservable( final File file ) {
        this.file = file;
        this.subscriptions = new IdentityHashMap<>();
        init();
    }

    protected void init() {
        task = new FileSizeChangeDetectionTask( file, subscriptions );
        timer = new Timer();
        timer.schedule( task, 0, 1000 );
        registerShutdownHook();
    }

    @Override
    public void subscribe( final Subscription<File> subscription ) {
        subscriptions.put( subscription, subscription );
    }

    protected void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook( new Thread() {
            @Override
            public void run() {
                close();
            }
        } );
    }

    @Override
    public void close() {
        for ( final Subscription<File> fileSubscription : subscriptions.values() ) {
            fileSubscription.close();
        }
    }
}
