package com.week7.solution.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pascalstammer
 * @version 12.06.17.
 */
public class LineReader implements Readable {

    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private Pattern pattern;

    private String lastLine;
    private Integer lineNumber;

    public LineReader( final InputStream inputStream, String regex ) {
        this(new InputStreamReader( inputStream), regex);
    }

    public LineReader( final InputStreamReader inputStreamReader, String regex ) {
        this.inputStreamReader = inputStreamReader;
        this.bufferedReader = new BufferedReader( inputStreamReader );
        this.pattern = Pattern.compile( regex );
        this.lineNumber = -1;
    }

    /**
     * Reads one line via BufferedReader
     * Increases count of read lines
     * @return String last line which was read
     * @throws IOException if an I/O error occurs
     */
    public String readLine() throws IOException {
        lastLine = bufferedReader.readLine();
        if(lineNumber == -1) {
            lineNumber = 1;
        } else {
            lineNumber++;
        }
        return lastLine;
    }

    /**
     * Gets number of read lines
     * @return lineNumber calculated throughout the process of reading
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Matcher interprets pattern, finds next match in last line
     * Count of found matches is increased and returned
     * @return Integer number of occurences
     */
    public Integer getAmountOfMatches() {
        final Matcher matcher = pattern.matcher( lastLine );
        int count = 0;
        while(matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * Attempts to read characters into CharBuffer
     * @param cb buffer to read characters into
     * @return number of characters added to the buffer, or -1 if this source of characters is at its end
     * @throws IOException  if an I/O error occurs
     */
    @Override
    public int read( final CharBuffer cb ) throws IOException {
        return bufferedReader.read( cb );
    }
}
