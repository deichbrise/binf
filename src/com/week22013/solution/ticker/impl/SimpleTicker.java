package com.week22013.solution.ticker.impl;

import com.common.util.condition.Conditions;
import com.common.util.lang.StringUtil;
import com.week22013.solution.ticker.Ticker;

/**
 * @author pascalstammer
 * @version 13.04.17.
 */
public class SimpleTicker implements Ticker {

    private static SimpleTicker instance;

    private SimpleTicker() {}

    public static SimpleTicker getInstance() {
        if( Conditions.isNull( instance )) {
            instance = new SimpleTicker();
        }

        return instance;
    }

    /**
     * Gibt den Wert text in der Konsole getrennt durch +++ aus. Dabei werden alle Umbrüche entfernt.
     *
     * @param text der auszugebene Text
     */
    @Override
    public void print( final String text ) {
        final String prepared = prepare( text );

        System.out.print( prepared );
    }

    protected String prepare( final String s ) {
        final StringBuilder stringBuilder = new StringBuilder();
        final String cleaned = clean( s );

        return stringBuilder
                .append( SEPERATOR )
                .append( cleaned )
                .append( SEPERATOR )
                .toString();
    }

    protected String clean( final String s ) {
        return StringUtil.clean( s );
    }
}
