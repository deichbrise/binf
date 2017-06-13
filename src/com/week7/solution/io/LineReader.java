package com.week7.solution.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pascalstammer
 * @version 12.06.17.
 */
public class LineReader extends LineNumberReader {

    private Pattern pattern;

    private String lastLine;
    private Integer lineNumber;

    public LineReader( final Reader reader, String regex ) {
        super(reader);
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
        lastLine = super.readLine();
        if(lineNumber == -1) {
            lineNumber = 1;
        } else {
            lineNumber++;
        }
        return lastLine;
    }

    public Integer getAmountOfMatches() {
        final Matcher matcher = pattern.matcher( lastLine );
        int count = 0;
        while(matcher.find()) {
            count++;
        }
        return count;
    }
}
