package com.week9.test.io;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week9.solution.io.PatternSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author pascalstammer
 * @version 27.06.17.
 */
@Test
public class PatternSearchTest extends AbstractTest {

    public static void main( String[] args ) {
        new PatternSearchTest().runAllTests();
    }

    @Test
    public void testPatternSearchExecution() {
        final File dir = buildTestDirectoryStructure();
        try {
            PatternSearch.main( new String[]{"-r", "Satz", dir.getAbsolutePath()} );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    protected File buildTestDirectoryStructure() {
        File tempDir = null;
        try {
            tempDir = Files.createTempDirectory( "testDir" ).toFile();
            tempDir.deleteOnExit();

            for(int i = 0; i < 10; i++) {
                final File subfolder = Files.createTempDirectory( tempDir.toPath(), "subTestDir" ).toFile();
                subfolder.deleteOnExit();
                for(int k = 0; k < 3; k++) {
                    final File subsubFile = Files.createTempFile( subfolder.toPath(), "testFile", ".txt" ).toFile();
                    subsubFile.deleteOnExit();

                    writeTestContent( subsubFile );
                }

                final File subFile = Files.createTempFile( tempDir.toPath(), "testFile", ".txt" ).toFile();
                subFile.deleteOnExit();

                writeTestContent( subFile );
            }

        } catch ( IOException e ) {
            throw new RuntimeException( e.getMessage(), e );
        }

        return tempDir;
    }

    protected void writeTestContent(final File file) {
        try (FileOutputStream ous = new FileOutputStream( file )) {
            ous.write( "Ich bin ein Satz. \n".getBytes() );
            ous.write( "Ich bin ein Satz. Mit zweimal Satz. \n".getBytes() );
            ous.write( "Ich bin ein Satz. \n".getBytes() );
            ous.write( "Ich bin ein Paragraph. \n".getBytes() );
            ous.write( "Ich bin ein Satz. \n".getBytes() );
            ous.write( "Ich bin ein Satz. \n".getBytes() );
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
