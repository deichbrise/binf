package com.week9.test.thread;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week9.solution.thread.Application;
import com.week9.solution.thread.observable.Subscription;
import com.week9.solution.thread.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 26.06.17.
 */
@Test
public class ApplicationTest extends AbstractTest {

    public static void main( String[] args ) {
        new ApplicationTest().runAllTests();
    }

    @Test
    public void testWatch() {
        try {
            final Path tempRoot = Files.createTempDirectory( "testDir" );
            tempRoot.toFile().deleteOnExit();
            final List<File> files = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                final File file = Files.createTempFile( tempRoot, "test-file", "tt" ).toFile();
                files.add( file );
                file.deleteOnExit();
            }

            for(final File file : files) {
                writeDummyContent( file, 1024 );
            }

            final Thread thread = new Thread( new Runnable() {
                @Override
                public void run() {
                    final Application application = new Application();
                    application.execute( tempRoot.toFile().getAbsolutePath());
                }
            } );

            thread.start();

            for(final File file : files) {
                writeDummyContent( file, 2048 );
                sleep();
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    protected void writeDummyContent( final File file, final long length) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream( file );
        for(int i = 0; i < length; i++) {
            fileOutputStream.write( "hsudfhiushfuhsdiufhushf \n".getBytes() );
        }
    }

    protected void sleep() {
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            // Should never happen
            e.printStackTrace();
        }
    }
}
