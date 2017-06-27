package com.week9.solution.io.minion;

import com.week9.solution.io.result.SearchLineMinionResult;
import com.week9.solution.io.util.SearchLineReader;

import java.io.File;
import java.io.FileReader;
import java.util.concurrent.Callable;

/**
 * @author Pascal Stammer (stammer@deichbrise.de)
 */
public class SearchLineMinion implements Callable<SearchLineMinionResult> {
    private final File file;
    private final String search;

    public SearchLineMinion( final File file, String search ) {
        this.file = file;
        this.search = search;
    }

    @Override
    public SearchLineMinionResult call() throws Exception {
        SearchLineMinionResult result = new SearchLineMinionResult( file );
        try {
            SearchLineReader searchLineReader = new SearchLineReader( new FileReader( file ), search );
            String line = searchLineReader.readLine();
            while ( line != null ) {
                if(searchLineReader.getAmountOfMatches() > 0) {
                    result.addLine( searchLineReader.getLineNumber(), searchLineReader.getAmountOfMatches(), line );
                }
                line = searchLineReader.readLine();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return result;
    }
}