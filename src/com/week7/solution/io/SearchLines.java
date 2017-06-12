package com.week7.solution.io;

import com.common.util.lang.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Example: java com.week7.solution.io.SearchLines "line" < ../src/com/week7/solution/io/SearchLines.java in folder bin/
 * @author pascalstammer
 * @version 12.06.17.
 */
public class SearchLines {
    public static void main( String[] args ) throws IOException {
        if (args.length < 1) throw new RuntimeException( "Cannot execute. Usage: java SearchLines String" );
        final String regex = args[0];
        final InputStream inputStream = System.in;
        final LineReader lineReader = new LineReader( new InputStreamReader( inputStream ), regex );
        String lastLine = lineReader.readLine();
        final String lineNumberCaption = "Line Number";
        final String numberOfOccurrencesCaption = "Number of Matches";
        final String lineCaption = "Line Content";
        System.out.print( StringUtil.padRight( lineNumberCaption, lineNumberCaption.length() + 4 ) );
        System.out.print( StringUtil.padRight( numberOfOccurrencesCaption, numberOfOccurrencesCaption.length() + 4 ) );
        System.out.print( StringUtil.padRight( lineCaption, lineCaption.length() + 4 ) );
        System.out.println();
        while(lastLine != null) {
            if(lineReader.getAmountOfMatches() > 0) {
                System.out.print( StringUtil.padRight( Integer.toString(lineReader.getLineNumber()), lineNumberCaption.length() * 2 + 4 ) );
                System.out.print( StringUtil.padRight( Integer.toString(lineReader.getAmountOfMatches()), numberOfOccurrencesCaption.length() * 2 + 4 ) );
                System.out.print( lastLine );
                System.out.println();
            }
            lastLine = lineReader.readLine();
        }
    }
}
