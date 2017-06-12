package com.week7.solution.io;

import java.io.BufferedReader;
import java.io.IOException;
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

    public LineReader( final InputStreamReader inputStreamReader, String regex ) {
        this.inputStreamReader = inputStreamReader;
        this.bufferedReader = new BufferedReader( inputStreamReader );
        this.pattern = Pattern.compile( regex );
        this.lineNumber = -1;
    }

    public String readLine() throws IOException {
        lastLine = bufferedReader.readLine();
        if(lineNumber == -1) {
            lineNumber = 1;
        } else {
            lineNumber++;
        }
        return lastLine;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public Integer getAmountOfMatches() {
        final Matcher matcher = pattern.matcher( lastLine );
        int count = 0;
        while(matcher.find()) {
            count++;
        }

        return count;
    }

    @Override
    public int read( final CharBuffer cb ) throws IOException {
        return bufferedReader.read( cb );
    }
}
