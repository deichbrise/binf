package com.week9.solution.io.result;

import com.common.util.lang.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pascalstammer
 * @version 24.06.17.
 */
public class SearchLineMinionResult {
    private final File file;
    private List<Entry> lines;

    public SearchLineMinionResult( final File file ) {
        this.file = file;
        lines = new ArrayList<>();
    }

    public File getFile() {
        return file;
    }

    public boolean isFound() {
        return lines.size() > 0;
    }

    public void addLine(final int lineNumber, final int numberOfMatches, final String content) {
        lines.add( new Entry( lineNumber, numberOfMatches, content ) );
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append( "File: " );
        builder.append( file.getAbsolutePath() );
        builder.append( System.lineSeparator() );

        for(final Entry line : lines) {
            builder.append( "Line Number: " );
            builder.append( line.getLineNumber() );
            builder.append( " | Number of Matches: " );
            builder.append( line.getNumberOfMatches() );
            builder.append( " | Content: " );
            builder.append( StringUtil.removeTrailingWhitespaces( line.getContent() ) );
            builder.append( System.lineSeparator() );
        }

        builder.append( System.lineSeparator() );
        return builder.toString();
    }

    public class Entry {
        private final Integer lineNumber;
        private final Integer numberOfMatches;
        private final String content;

        public Entry( final Integer lineNumber, final Integer numberOfMatches, final String content ) {
            this.lineNumber = lineNumber;
            this.numberOfMatches = numberOfMatches;
            this.content = content;
        }

        public Integer getLineNumber() {
            return lineNumber;
        }

        public Integer getNumberOfMatches() {
            return numberOfMatches;
        }

        public String getContent() {
            return content;
        }
    }
}