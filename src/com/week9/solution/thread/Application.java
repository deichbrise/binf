package com.week9.solution.thread;

import com.common.logger.LogManager;
import com.common.logger.Logger;
import com.week9.solution.thread.observable.Observable;
import com.week9.solution.thread.observable.Subscription;
import com.week9.solution.thread.strategy.FileObservableStrategy;
import com.week9.solution.thread.strategy.impl.FileSizeChangeObservableStrategyImpl;
import com.week9.solution.thread.util.FileUtil;

import java.io.File;
import java.nio.file.Files;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class Application {

    public static final Logger log = LogManager.getLogger(Application.class);

    public static void main( String[] args ) {
        final Application application = new Application();
        application.execute( "./" );
    }

    public void execute(final String fileName) {
        final File file = new File( fileName );
        final FileObservableStrategy strategy = new FileSizeChangeObservableStrategyImpl();
        final Observable<File> observable = strategy.observe( file );

        observable.subscribe( new Subscription<File>() {
            @Override
            public void call( final File o ) {
                log.info( "File " + o.getAbsolutePath() + " changed. Current size: " + FileUtil.byteCountToDisplaySize( FileUtil.getSize( o ) ));
            }

            @Override
            public void close() {
                log.info( "Unsubscribe file " + file.getAbsolutePath() );
            }
        } );
    }
}
