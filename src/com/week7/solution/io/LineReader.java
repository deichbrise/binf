package com.week7.solution.io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pascalstammer
 * @version 12.06.17.
 */
public class LineReader extends LineNumberReader {

    private Pattern pattern;

    private String lastLine;

    public LineReader( final Reader reader, String regex ) {
        super( reader );
        this.pattern = Pattern.compile( regex );
    }

    /**
     * Reads one line
     * Increases count of read lines
     *
     * @return String last line which was read
     * @throws IOException if an I/O error occurs
     */
    public String readLine() throws IOException {
        lastLine = super.readLine();
        return lastLine;
    }

    public Integer getAmountOfMatches() {
        final Matcher matcher = pattern.matcher( lastLine );
        int count = 0;
        while ( matcher.find() ) {
            count++;
        }
        return count;
    }
}
