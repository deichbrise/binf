package com.week9.solution.thread.task.impl;

import com.common.logger.LogManager;
import com.common.logger.Logger;
import com.week9.solution.thread.observable.Subscription;
import com.week9.solution.thread.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class FileSizeChangeDetectionTask extends TimerTask {

    final private File file;
    private Map<String, Object> state;
    private Map<Subscription<File>, Subscription<File>> subscriptions;
    private static final Logger log = LogManager.getLogger( FileSizeChangeDetectionTask.class );
    private static final Object MUTEX = new Object();

    public FileSizeChangeDetectionTask( final File file, final Map<Subscription<File>, Subscription<File>> subscriptions ) {
        this.file = file;
        this.state = new HashMap<>();
        this.subscriptions = subscriptions;
        if(log.isDebugEnabled()) {
            log.debug( "Register file change detection task for file with path " + file.getAbsolutePath() );
        }
    }

    @Override
    public void run() {
        synchronized ( MUTEX ) {
            checkFileSize();
        }
    }
    protected void checkFileSize() {
        final Long size = getFileSize( file );
        if(state.containsKey( ObservedFileStates.FILE_SIZE )) {
            if(!state.get( ObservedFileStates.FILE_SIZE ).equals( size )) {
                emit( file );
            }
        } else {
            emit( file );
        }
        state.put( ObservedFileStates.FILE_SIZE, size );
    }

    protected void emit(final File file) {
        for(final Subscription<File> subscription : subscriptions.values()) {
            subscription.call( file );
        }
    }

    protected Long getFileSize(final File file) {
        return FileUtil.getSize( file );
    }

    public interface ObservedFileStates {
        public static final String FILE_SIZE = "fileSize";
    }
}
