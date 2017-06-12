package com.week7.test.io;

import com.common.test.AbstractTest;
import com.common.test.Test;
import com.week7.solution.io.LineReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.common.test.Assert.*;

/**
 * @author pascalstammer
 * @version 12.06.17.
 */
@Test
public class LineReaderTest extends AbstractTest {
    public static void main( String[] args ) {
        new LineReaderTest().runAllTests();
    }

    @Test
    public void testLineReader() throws IOException {
        final ByteArrayInputStream stream = new ByteArrayInputStream( "Das ist eine Zeile \n Das ist noch eine Zeile \n und das ist auch noch eine Zeile \n und das ist eine Zeile und gleich kommt eine Zeile ohne das Wort Zeile \n hallo ih bin auch irgendwas".getBytes() );
        final InputStreamReader inputStreamReader = new InputStreamReader( stream );
        final LineReader lineReader = new LineReader( inputStreamReader, "Zeile" );

        int[] countsOfZeile = new int[]{0, 1, 1, 1, 3, 0};
        String line = lineReader.readLine();
        while(line != null) {
            assertEquals( countsOfZeile[lineReader.getLineNumber()], lineReader.getAmountOfMatches() );
            line = lineReader.readLine();
        }
    }
}
